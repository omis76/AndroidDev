package co.joebirch.minimise.authentication

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import co.joebirch.minimise.android.core.di.Preferences
import co.joebirch.minimise.android.core.di.default
import co.joebirch.minimise.authentication.interactor.Authenticate
import co.joebirch.minimise.authentication.model.AuthenticationModel
import co.joebirch.minimise.navigation.AuthenticationDirections
import co.joebirch.minimise.navigation.NavigationController
import kotlinx.coroutines.launch

class AuthenticationViewModel @ViewModelInject constructor(
    private val authenticate: Authenticate,
    private val sharedPrefs: Preferences,
    private val navigationController: NavigationController,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel(), AuthenticateView {

    private var _uiState =
        MutableLiveData<AuthenticationState>().default(
            AuthenticationState()
        )

    val uiState = _uiState as LiveData<AuthenticationState>

    fun handleAuthenticationEvent(event: AuthenticationEvent) {
        if (event is AuthenticationEvent.AuthenticateClicked) {
            _uiState.value = uiState.value!!.build {
                loading = true
            }
            authenticate()
        } else {
            _uiState.value = uiState.value!!.build {
                when (event) {
                    AuthenticationEvent.AuthenticationModeToggled -> {
                        if (this.mode == AuthenticateMode.SignIn) {
                            this.mode = AuthenticateMode.SignUp
                        } else {
                            this.mode = AuthenticateMode.SignIn
                        }
                    }
                    AuthenticationEvent.DismissErrorDialog -> {
                        this.error = null
                    }
                    AuthenticationEvent.AuthenticateClicked -> {
                        this.loading = true
                    }
                    AuthenticationEvent.ForgotPasswordClicked -> {
                        navigationController.navigate(
                            AuthenticationDirections.ForgotPassword
                        )
                    }
                    is AuthenticationEvent.EmailChanged -> {
                        this.userEmail = event.email
                    }
                    is AuthenticationEvent.PasswordChanged -> {
                        this.userPassword = event.password
                    }
                }
            }
        }
    }

    override fun toggleAuthenticationMode() {
        if (uiState.value!!.authenticationMode == AuthenticateMode.SignIn) {
            _uiState.postValue(
                uiState.value!!.build {
                    mode = AuthenticateMode.SignUp
                }
            )
        } else {
            _uiState.postValue(
                uiState.value!!.build {
                    mode = AuthenticateMode.SignIn
                }
            )
        }
    }

    override fun setEmailAddress(emailAddress: String) {
        _uiState.postValue(
            uiState.value!!.build {
                this.userEmail = emailAddress
            }
        )
    }

    override fun setPassword(password: String) {
        _uiState.postValue(
            uiState.value!!.build {
                this.userPassword = password
            }
        )
    }

    override fun authenticate() {
        if (uiState.value!!.authenticationMode == AuthenticateMode.SignIn) {
            signIn()
        } else {
            signUp()
        }
    }

    override fun signUp() {
        _uiState.postValue(
            uiState.value!!.build {
                loading = true
                error = null
            }
        )
        viewModelScope.launch {
            authenticate.run(
                Authenticate.Params.forSignUp(
                    BuildConfig.API_KEY,
                    uiState.value!!.emailAddress, uiState.value!!.password
                )
            ) { result ->
                handleResult(result)
            }
        }
    }

    override fun signIn() {
        _uiState.postValue(
            uiState.value!!.build {
                loading = true
                error = null
            }
        )
        viewModelScope.launch {
            authenticate.run(
                Authenticate.Params.forSignIn(
                    BuildConfig.API_KEY,
                    uiState.value!!.emailAddress, uiState.value!!.password
                )
            ) { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: AuthenticationModel) {
        if (result.token != null) {
            viewModelScope.launch {
                sharedPrefs.setAuthToken(result.token!!)
                navigationController.navigate(AuthenticationDirections.Dashboard)
            }
        } else {
            _uiState.postValue(
                uiState.value!!.build {
                    loading = false
                    error = result.message
                }
            )
        }
    }
}