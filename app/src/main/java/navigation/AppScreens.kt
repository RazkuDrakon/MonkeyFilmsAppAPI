package navigation

sealed class AppScreens(val route: String){
    object LoginScreen: AppScreens("login_screen")
    object HomeScreen: AppScreens("home_screen")
    object FavScreen: AppScreens("fav_screen")
    object AddFilmScreen: AppScreens("addfilm_screen")
    object RegisterScreen: AppScreens("register_screen")
}