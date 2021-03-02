package co.joebirch.minimise.authentication

import co.joebirch.minimise.authentication.mapper.ResetPasswordResponseMapper
import co.joebirch.minimise.authentication.model.AuthenticationModel
import co.joebirch.minimise.authentication.model.ResetPasswordResponse
import co.joebirch.minimise.authentication.remote.AuthenticationRemoteStore

interface AuthenticationRepository {

    suspend fun signUp(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel

    suspend fun signIn(
        apiKey: String,
        emailAddress: String,
        password: String
    ): AuthenticationModel

    suspend fun resetPassword(
        apiKey: String,
        emailAddress: String
    ): ResetPasswordResponse

    companion object {
        fun create(): AuthenticationRepository =
            RepositoriesProvider.getAuthenticationDataRepository(
                AuthenticationRemoteStore(),
                ResetPasswordResponseMapper()
            )
    }
}