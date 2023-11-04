package com.example.learncompose.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Preview
@Composable
fun NavigationDemo() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Home(navController)
        }
        composable(
            "login?user={user}&password={password}",
            arguments = listOf(
                navArgument("user") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("password") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { navBackStackEntry ->
            val user = navBackStackEntry.arguments?.getString("user") ?: ""
            val password = navBackStackEntry.arguments?.getString("password") ?: ""

            Login(
                navController,
                user,
                password
            )
        }

        composable("register") {
            Register(navController)
        }
    }
}

@Composable
fun Home(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text(text = "Login")
        }
        Button(
            onClick = {
                navController.navigate("register")
            }
        ) {
            Text(text = "Register")
        }
    }
}

@Composable
fun Login(
    navController: NavHostController,
    user: String,
    password: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user.isNotEmpty()) {
            Text(text = user)
        }
        if (password.isNotEmpty()) {
            Text(text = password)
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Don't have account? to register!")
            Button(
                onClick = {
                    navController.navigate("register")
                }
            ) {
                Text(text = "Register")
            }
        }
    }
}

@Composable
fun Register(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate("login?user=manson&password=1234")
            }
        ) {
            Text(text = "Back to Login")
        }
    }
}