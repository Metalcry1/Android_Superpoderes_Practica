package com.example.android_superpoderes_practica.ui.Login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_superpoderes_practica.R


@SuppressLint("ResourceType")
@Composable
fun LoginViewScreen(loginViewModel: LoginViewModel, token: String) {
    //var username by remember { mutableStateOf("") }
    //var password by remember { mutableStateOf("") }
    var username = "metalcry1@gmail.com"
    var password = "4312"



    Box(
        Modifier.fillMaxSize(), Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.marvel_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                Modifier
                    .size(100.dp, 100.dp)
            )
            Spacer(modifier = Modifier.size(300.dp))
            Box {
                CustomTextField(
                    placeholder = "Email",
                    isPassword = false,
                    icon = Icons.Filled.AccountBox,
                    value = username,
                    onValueChange = {
                        username = it
                    }
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Box {
                CustomTextField(
                    placeholder = "Contraseña",
                    isPassword = true, // Cambiado a true
                    icon = Icons.Filled.AccountBox,
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Box {

                Button(
                    onClick = {
                        if (username.contains("@") && password.length >= 4) {
                            loginViewModel.launchLogin(username, password)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Cyan
                    )
                ) {
                    Text(text = "Iniciar sesión")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    placeholder: String,
    value: String,
    isPassword: Boolean,
    icon: ImageVector,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = placeholder)
        },
        leadingIcon = { Icon(imageVector = icon, contentDescription = "Custom icon") },
        trailingIcon = {
            if (isPassword) Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Custom icon"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
            capitalization = KeyboardCapitalization.None
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}



@Composable
fun BotonLogin() {
    Box {

        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Cyan
            )
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}


@Preview
@Composable
private fun Favorite_items_Preview() {

    BotonLogin()
}

@Preview
@Composable
private fun MyLogin_Preview() {

}


