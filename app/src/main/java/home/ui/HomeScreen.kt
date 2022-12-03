package newfilm

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.u4_monkeyfilmapp2.R
import home.ui.HomeViewModel
import model.MediaModel
import navigation.AppScreens


@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel){
    var bottomState by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MonkeyFilm App") },backgroundColor = colorResource(id = R.color.AppOrange),contentColor = Color.White, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, "Menu")
                }
            },
                actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Person , contentDescription = "User" )
                }
            })
        },floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(route = AppScreens.AddFilmScreen.route)},
                backgroundColor = colorResource(id = R.color.AppOrange),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add,"")
            }
        },
        content = {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                ShowBillboard(homeViewModel)
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor =  colorResource(id = R.color.AppOrange), contentColor = Color.White) {

                BottomNavigationItem(
                    selected = bottomState == "Home",
                    onClick = { },
                    icon = { Icon(imageVector = Icons.Default.Home , contentDescription = null) }
                )

                BottomNavigationItem(
                    selected = bottomState == "Favorite",
                    onClick = { navController.navigate(route = AppScreens.FavScreen.route) },
                    icon = { Icon(imageVector = Icons.Default.Star , contentDescription = null) }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowBillboard(homeViewModel: HomeViewModel) {
    LazyColumn(
    ){
        items(homeViewModel.filmList.value!!.size){ index ->
            val film= homeViewModel.filmList.value!![index]
            expandableCard(film)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowFavs(homeViewModel: HomeViewModel) {
    LazyColumn(
    ){
        items(homeViewModel.filmList.value!!.size){ index ->
            val film= homeViewModel.filmList.value!![index]
            if(film.favorite == true)
            expandableCard(film)
        }
    }
}



@ExperimentalMaterialApi
@Composable
fun expandableCard(film: MediaModel){
    var expandedState by remember{ mutableStateOf(false) }
    var expandedCard by remember{ mutableStateOf(false) }
    var bigCard by remember{ mutableStateOf(false) }
    var fav by remember{ mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }

    var poster= when(film.catel){
        1 -> R.drawable.c1
        2 -> R.drawable.c2
        3 -> R.drawable.c3
        4 -> R.drawable.c4
        5 -> R.drawable.c5
        6 -> R.drawable.c6
        7 -> R.drawable.c7
        8 -> R.drawable.c8
        9 -> R.drawable.c9
        10 -> R.drawable.c10

        else -> R.drawable.pball
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 200,
                easing = LinearOutSlowInEasing
            )
        ),
    onClick = {
        expandedState = !expandedState
    }) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {

            /* Card cerrada */
            if(!expandedCard){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(poster),
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)
                            .weight(2f),
                        contentScale = ContentScale.Fit)

                    Column(modifier = Modifier.weight(6f)) {
                        Text(text = film.title, fontSize = MaterialTheme.typography.h5.fontSize, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = film.description, fontSize = MaterialTheme.typography.h6.fontSize, fontStyle = FontStyle.Italic, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {

                        IconButton(
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            onClick = {
                                selected = !selected
                                fav = !fav
                                film.favorite = fav
                            }) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = if (selected) Color.Blue else Color.Gray)
                        }

                        IconButton(
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            onClick = {
                                expandedState = !expandedState
                            }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "",modifier = Modifier.size(26.dp))
                        }
                    }
                }
            }

            /* Card abierta */
            if(expandedState){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row{
                        Image(
                            painter = painterResource(poster),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp),
                            contentScale = ContentScale.Fit)
                    }
                    Row{
                        Column( modifier = Modifier.weight(6f)) {

                            val genres = film.genre
                            val separator = ", "
                            val genero = genres.joinToString(separator)


                            Row{
                                Text(text = genero)
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Row{
                                Text(text = film.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }

                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            IconButton(
                                modifier = Modifier.alpha(ContentAlpha.medium),
                                onClick = {
                                    selected = !selected
                                    fav = !fav
                                    film.favorite = fav
                                }) {
                                Icon(imageVector = Icons.Default.Star, contentDescription = "",tint = if (selected) Color.Blue else Color.Gray)
                            }

                            IconButton(
                                modifier = Modifier.alpha(ContentAlpha.medium),
                                onClick = {
                                    expandedState = !expandedState
                                    bigCard = !bigCard
                                }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "",modifier = Modifier.size(26.dp))
                            }
                        } //fin columna iconos expandido
                    }//fin fila detalles expandido
                }//fin columna expandida
            }//fin card abierta


            /* Card Expandida */
            if(bigCard){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row{
                        Image(
                            painter = painterResource(poster),
                            contentDescription = "",
                            contentScale = ContentScale.Fit)
                    }
                    Row{
                        Column( modifier = Modifier.weight(6f)) {

                            val genres = film.genre
                            val separator = ", "
                            val genero = genres.joinToString(separator)


                            Row{
                                Text(text = film.title, fontSize = MaterialTheme.typography.h5.fontSize, fontWeight = FontWeight.Bold)
                            }
                            Row{
                                Text(text = genero)
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Text(text = film.description, fontSize = MaterialTheme.typography.h6.fontSize)
                            }
                            Row{
                                IconButton(
                                    modifier = Modifier.alpha(ContentAlpha.medium),
                                    onClick = {
                                        selected = !selected
                                        fav = !fav
                                        film.favorite = fav
                                    }) {
                                    Icon(imageVector = Icons.Default.Star, contentDescription = "",tint = if (selected) Color.Blue else Color.Gray,modifier = Modifier.size(48.dp))
                                }
                                IconButton(
                                    modifier = Modifier.alpha(ContentAlpha.medium),
                                    onClick = {
                                        bigCard = !bigCard
                                        expandedCard = !expandedCard
                                    }) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "",modifier = Modifier.size(48.dp))
                                }
                            }

                        }
                    }//fin fila detalles expandido
                }//fin columna expandida
            }//fin card abierta
        } // fin column principal
    } // fin card
} // fin expandableCard()

