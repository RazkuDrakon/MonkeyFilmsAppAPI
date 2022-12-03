package newfilm

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.u4_monkeyfilmapp2.R
import home.ui.HomeViewModel
import navigation.AppScreens

@Composable
fun FavScreen(navController : NavController,homeViewModel: HomeViewModel){
    var bottomState by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MonkeyFilm App") },backgroundColor = colorResource(id = R.color.AppOrange),contentColor = Color.White, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Menu, "Menu")
                }
            }, actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Person , contentDescription = "User" )
                }
            })
        },
        floatingActionButton = {
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
                ShowFavs(homeViewModel)
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor =  colorResource(id = R.color.AppOrange), contentColor = Color.White) {

                BottomNavigationItem(
                    selected = bottomState == "Home",
                    onClick = { navController.navigate(route = AppScreens.HomeScreen.route) },
                    icon = { Icon(imageVector = Icons.Default.Home , contentDescription = null) }
                )

                BottomNavigationItem(
                    selected = bottomState == "Favorite",
                    onClick = { },
                    icon = { Icon(imageVector = Icons.Default.Star , contentDescription = null) }
                )
            }
        }
    )
}

