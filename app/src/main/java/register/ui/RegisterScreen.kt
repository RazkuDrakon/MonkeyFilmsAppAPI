package newfilm

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.u4_monkeyfilmapp2.R
import navigation.AppScreens

@Composable
fun RegisterScreen(navController: NavController){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "MonkeyFilm App", fontSize = 36.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(top=12.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(modifier = Modifier.padding(8.dp)) {
                        Column() {
                            var userText by remember { mutableStateOf("") }
                            Text(text = "User")
                            TextField(
                                value = userText,
                                onValueChange = { userText = it },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        Column() {
                            var text by remember { mutableStateOf("") }
                            Text(text = "Email")
                            TextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        Column() {
                            var passwordText by remember { mutableStateOf("") }
                            Text(text = "Password")
                            TextField(
                                value = passwordText,
                                onValueChange = { passwordText = it },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        Column() {
                            var passwordText by remember { mutableStateOf("") }
                            Text(text = "Confirm password")
                            TextField(
                                value = passwordText,
                                onValueChange = { passwordText = it },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Prefered genres")
                    }

                    Row() {
                        val checked1 = remember { mutableStateOf(false) }
                        val checked2 = remember { mutableStateOf(false) }
                        val checked3 = remember { mutableStateOf(false) }
                        LabelledCheckbox(
                            checked = checked1.value,
                            onCheckedChange = { checked1.value = it },
                            label = "Option 1"
                        )
                        LabelledCheckbox(
                            checked = checked2.value,
                            onCheckedChange = { checked2.value = it },
                            label = "Option 2"
                        )
                        LabelledCheckbox(
                            checked = checked3.value,
                            onCheckedChange = { checked3.value = it },
                            label = "Option 3"
                        )
                    }

                    Row() {
                        val checked1 = remember { mutableStateOf(false) }
                        val checked2 = remember { mutableStateOf(false) }
                        val checked3 = remember { mutableStateOf(false) }
                        LabelledCheckbox(
                            checked = checked1.value,
                            onCheckedChange = { checked1.value = it },
                            label = "Option 1"
                        )
                        LabelledCheckbox(
                            checked = checked2.value,
                            onCheckedChange = { checked2.value = it },
                            label = "Option 2"
                        )
                        LabelledCheckbox(
                            checked = checked3.value,
                            onCheckedChange = { checked3.value = it },
                            label = "Option 3"
                        )
                    }

                    Row() {
                        val checked1 = remember { mutableStateOf(false) }
                        val checked2 = remember { mutableStateOf(false) }
                        val checked3 = remember { mutableStateOf(false) }
                        LabelledCheckbox(
                            checked = checked1.value,
                            onCheckedChange = { checked1.value = it },
                            label = "Option 1"
                        )
                        LabelledCheckbox(
                            checked = checked2.value,
                            onCheckedChange = { checked2.value = it },
                            label = "Option 2"
                        )
                        LabelledCheckbox(
                            checked = checked3.value,
                            onCheckedChange = { checked3.value = it },
                            label = "Option 3"
                        )
                    }

                    Row(modifier = Modifier.padding(8.dp)) {
                        val context = LocalContext.current
                        Button(
                            onClick = {
                                Toast.makeText(context, "Register completed", Toast.LENGTH_LONG).show()
                                navController.navigate(route = AppScreens.LoginScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.AppOrange)),
                            modifier = Modifier
                                .height(40.dp)
                                .width(150.dp)
                        ) {

                            Text(
                                text = "Register",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Text(label)
    }
}
