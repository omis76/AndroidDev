package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.theme.typography

@ExperimentalMaterialApi
@Composable
fun PuppyDetail(name: String, navController: NavController) {
    //val model =
    Box {
        BackdropScaffold(
            modifier = Modifier.background(color = Color.Gray),
            frontLayerScrimColor = Color.Transparent,
            scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
            appBar = { TopBars(name = "test", navController) },
            backLayerContent = { PuppyDetails() },
            frontLayerContent = {
                DetailCard()
            })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp), verticalAlignment = Alignment.Bottom
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_public_24),
                    contentDescription = ""
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(text = "Petted by", style = typography.caption)
                    Text(text = "Owner's Full Name", style = typography.button)
                }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "CONTACT ME", style = typography.button)
                    }
                }
            }
        }
    }
}

@Composable
fun PuppyDetails() {
    Column() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_radio_button_checked_24),
                contentDescription = "Puppy Image",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.LightGray, shape = CircleShape),
                contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(R.drawable.ic_baseline_public_24),
                contentDescription = "Puppy Image",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun DetailCard() {
    val detail =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    Card(elevation = 0.dp, backgroundColor = Color.White, modifier = Modifier.fillMaxHeight()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Name", style = typography.h6)
            Text(text = "Locality, City Name (2.5 Km)", style = typography.caption)
            Spacer(Modifier.height(16.dp))
            ThreeCards()
            Spacer(Modifier.height(16.dp))
            Text(text = "Pet Story", style = typography.h6)
            Spacer(Modifier.height(8.dp))
            Text(
                text = detail,
                style = typography.caption,
                modifier = Modifier
                    .height(100.dp)
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical,
                        enabled = true
                    )
            )
            Row(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {

            }
        }
    }
}

@Composable
fun ThreeCards(modifier: Modifier = Modifier) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Column(
            Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "4 Months", style = typography.h6, color = Color.Black)
            Text(text = "age", style = typography.caption, color = Color.Gray)
        }
        Spacer(Modifier.width(4.dp))
        Column(
            Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Gray", style = typography.h6, color = Color.Black)
            Text(text = "color", style = typography.caption, color = Color.Gray)
        }
        Spacer(Modifier.width(4.dp))
        Column(
            Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "11.4 Kg", style = typography.h6, color = Color.Black)
            Text(text = "weight", style = typography.caption, color = Color.Gray)
        }
    }
}


@Composable
fun TopBars(name: String, navController: NavController) {
    TopAppBar(
        backgroundColor = Color.Gray,
        elevation = 0.dp,
        title = { Text(text = "") },
        navigationIcon = {IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "")//, contentDescription = "test")
        }},
    )
}