package com.example.androiddevchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.theme.typography


fun getPuppies(): List<PuppyModel> {
    val list = mutableListOf<PuppyModel>()
    list.add(PuppyModel("Puppy 91"))
    list.add(PuppyModel("Puppy 81"))
    list.add(PuppyModel("Puppy 71"))
    list.add(PuppyModel("Puppy 61"))
    list.add(PuppyModel("Puppy 51"))
    list.add(PuppyModel("Puppy 41"))
    list.add(PuppyModel("Puppy 31"))
    list.add(PuppyModel("Puppy 21"))
    list.add(PuppyModel("Puppy 10"))
    list.add(PuppyModel("Puppy 19"))
    list.add(PuppyModel("Puppy 18"))
    list.add(PuppyModel("Puppy 17"))
    list.add(PuppyModel("Puppy 16"))
    list.add(PuppyModel("Puppy 15"))
    list.add(PuppyModel("Puppy 14"))
    list.add(PuppyModel("Puppy 13"))
    list.add(PuppyModel("Puppy 12"))
    list.add(PuppyModel("Puppy 11"))
    return list
}

fun getCategories(): List<String> {
    val list = mutableListOf<String>()
    list.add("Category 1")
    list.add("Category 2")
    list.add("Category 3")
    list.add("Category 4")
    return list
}

@ExperimentalFoundationApi
@Composable
fun Home(navController: NavController) {
    Scaffold(
        Modifier.background(color = Color.Gray),
        //topBar = { topBar() },
        content = {
            //LazyColumn(content = { MainView(navController = navController) })
            Column {
                MainView(navController)
//            }

            }
            //)
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun MainView(navController: NavController) {
    TopBar()
    Column(
        modifier = Modifier
            .padding(32.dp, 0.dp, 0.dp, 0.dp)
            .background(
                Color.LightGray, shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp)
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Search",
        )
    }
    CategoryView()
    ListView(navController = navController)
}

@ExperimentalFoundationApi
@Composable
fun ListView(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Puppies", style = typography.subtitle2)
        GridViews(getPuppies(), navController)
    }
}

@ExperimentalFoundationApi
@Composable
fun CategoryView() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Puppy Categories", style = typography.subtitle2)
        Spacer(modifier = Modifier.height(8.dp))
        CategoryGrid(getCategories())
    }
}

@Composable
fun TopBar() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Find Your", style = typography.subtitle2)
        Text(text = "Lovely puppies in anywhere", style = typography.subtitle1)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@ExperimentalFoundationApi
@Composable
fun CategoryGrid(categories: List<String>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(items = categories) { name ->
            CategoryCard(name)
        }
    }
}

@Composable
fun CategoryCard(name: String) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                .padding(8.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_empty),
                contentDescription = "Puppy Image",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .background(Color(137, 149, 139), RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = name, style = typography.subtitle1, maxLines = 1)
        }
    }

}

@ExperimentalFoundationApi
@Composable
fun GridViews(puppies: List<PuppyModel>, navController: NavController) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(items = puppies) { name ->
            PuppyCard(puppy = name, navController)
        }
    }
}

@Composable
fun PuppyCard(puppy: PuppyModel, navController: NavController) {
    Box(modifier = Modifier.padding(8.dp)) {
        Card {
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate("detail/" + puppy.name)
                    }
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_empty),
                    contentDescription = "Puppy Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(color = Color(137, 149, 139)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(8.dp, 0.dp)) {
                    Text(
                        text = "Albanian",
                        style = typography.overline,
                        maxLines = 1,
                        modifier = Modifier
                            .background(
                                Color.Yellow, shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = puppy.name, style = typography.subtitle1, maxLines = 1)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "location (2.3 Km)",
                        style = typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

