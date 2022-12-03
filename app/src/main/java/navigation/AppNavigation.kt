package navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import home.ui.HomeViewModel
import login.ui.LoginViewModel
import newfilm.FavScreen
import newfilm.AddFilmScreen
import newfilm.HomeScreen
import newfilm.LoginScreen
import newfilm.RegisterScreen

@ExperimentalMaterialApi
@Composable
fun AppNavigation(){

    val loginViewModel = LoginViewModel()
    val homeViewModel = HomeViewModel()
    homeViewModel.returnList()

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(loginViewModel, navController)
        }
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController,homeViewModel)
        }
        composable(route = AppScreens.FavScreen.route){
            FavScreen(navController,homeViewModel)
        }
        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(route = AppScreens.AddFilmScreen.route){
            AddFilmScreen(navController)
        }
    }
}