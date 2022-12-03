package newfilm

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.u4_monkeyfilmapp2.R
import navigation.AppScreens

@Composable
fun AddFilmScreen(navController : NavController){
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
                addFilmForm(navController)
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

@Composable
fun addFilmForm(navController : NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(color = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(modifier = Modifier.padding(8.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = "",
                        contentScale = ContentScale.Fit)
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    Column() {
                        var titleText by remember { mutableStateOf("") }
                        Text(text = "Title")
                        TextField(
                            value = titleText,
                            onValueChange = { titleText = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                    }
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    Column() {
                        var genreText by remember { mutableStateOf("") }
                        Text(text = "Genres")
                        TextField(
                            value = genreText,
                            onValueChange = { genreText = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                    }
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    Column() {
                        var descriptionText by remember { mutableStateOf("") }
                        Text(text = "Description")
                        TextField(
                            value = descriptionText,
                            onValueChange = { descriptionText = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                    }
                }
                Row(modifier = Modifier.padding(8.dp)) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            Toast.makeText(context, "Film uploaded!", Toast.LENGTH_LONG).show()
                            navController.navigate(route = AppScreens.HomeScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.AppOrange)),
                        modifier = Modifier
                            .height(40.dp)
                            .width(150.dp)
                    ) {

                        Text(
                            text = "Add",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}