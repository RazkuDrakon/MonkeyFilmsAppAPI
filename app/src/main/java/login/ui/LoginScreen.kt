package newfilm

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.u4_monkeyfilmapp2.R
import login.ui.LoginViewModel
import navigation.AppScreens

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val isLoading: Boolean by loginViewModel
            .isLoading.observeAsState(initial = false)

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginViewModel, navController)
            Footer(Modifier.align(Alignment.BottomCenter), navController = navController)
        }
    }
}

@Composable
fun Footer(modifier: Modifier, navController: NavHostController) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp(navController)
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp(navController: NavHostController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.sign_up_question),
            fontSize = 12.sp,
            modifier = Modifier.clickable(onClick = {}),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            color = Color(0xFFB5B5B5)
        )
        Text(
            text = stringResource(id = R.string.sign_up),
            Modifier.padding(horizontal = 8.dp).clickable(onClick = {
                /* TODO */
                navController.navigate(route = AppScreens.RegisterScreen.route)
            }),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF47978E9),
        )

    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel, navController: NavHostController) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isButtonLoginEnable.observeAsState(initial = false)

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = modifier) {
        Text(
            text = stringResource(id = R.string.app_title),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            LoginButton(isLoginEnable, loginViewModel, navController)
        }
    }
}


@Composable
fun LoginButton(loginEnable: Boolean, loginViewModel: LoginViewModel, navController : NavHostController) {
    Button(
        onClick = { loginViewModel.onButtonLoginPress(navController) },
        enabled = loginEnable,
        modifier = Modifier
            .height(40.dp)
            .width(150.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.AppOrange),
            disabledBackgroundColor = colorResource(id = R.color.AppDarkerOrange),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = stringResource(id = R.string.log_in))
    }
}


@Composable
fun ForgotPassword(modifier: Modifier) {
    var dialogState by remember{ mutableStateOf(false) }

    Text(
        text = stringResource(id = R.string.forgot_password),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF47978E9),
        modifier = Modifier.clickable(
            onClick = {
                dialogState = !dialogState
            }),
        style = TextStyle(textDecoration = TextDecoration.Underline)
    )

    if(dialogState){
        dialog(dialogState= true, onDismiss= {dialogState= false})
    }
}

@Composable
fun dialog(dialogState:Boolean, onDismiss:() -> Unit){
    var email by remember { mutableStateOf("") }
    if (dialogState) {
        Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = false)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Write your email to receive a password reset email", textAlign = TextAlign.Center)
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        var text by remember { mutableStateOf("") }
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text(text = "Email") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        val context = LocalContext.current
                        Button(
                            onClick = {onDismiss()},
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.AppOrange)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {

                            Text(
                                text = "Enter",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(stringResource(id = R.string.password)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = imagen,
                    contentDescription = stringResource(id = R.string.show_password)
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.email)) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(id = R.string.close_app),
        modifier = modifier.clickable { activity.finish() })
}
