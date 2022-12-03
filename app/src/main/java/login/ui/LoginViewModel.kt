package login.ui

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import login.domain.LoginUseCase
import navigation.AppNavigation
import navigation.AppScreens

class LoginViewModel : ViewModel() {
    private val loginUseCase= LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isButtonLoginEnable = MutableLiveData<Boolean>()
    val isButtonLoginEnable: LiveData<Boolean> = _isButtonLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isButtonLoginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length > 5

    fun onButtonLoginPress(navController: NavHostController) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)

            if(result) {
                Log.i("MonkeyFilm","Login success")
                navController.navigate(route = AppScreens.HomeScreen.route)
            }
            _isLoading.value = false
        }
    }
}