package com.example.clothloop.navigation

import LogInViewModelFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clothloop.screens.history
import com.example.clothloop.screens.home
import com.example.clothloop.screens.logIn
import com.example.clothloop.screens.pickUpTextile
import com.example.clothloop.screens.profile
import com.example.clothloop.screens.signUp
import com.example.clothloop.screens.typesTextile
import com.example.clothloop.viewModel.logInViewModel
import com.example.clothloop.viewModel.signUpViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val logInViewModel: logInViewModel = viewModel(factory = LogInViewModelFactory(context))
    val signUpViewModel: signUpViewModel = viewModel()

    val isLoggedIn by logInViewModel.isLoggedin.collectAsState()

    NavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "logIn") {
        composable("logIn") {
            logIn(
                navController = navController,
                logInViewModel = logInViewModel,
                signUpViewModel = signUpViewModel
            )
        }
        composable("signUp") {
            signUp(
                navController = navController,
                logInViewModel = logInViewModel,
                signUpViewModel = signUpViewModel
            )
        }
        composable("home") {
            home(
                navController = navController,
                logInViewModel = logInViewModel
            )
        }
        composable("profile") {
            profile(
                navController = navController,
                logInViewModel = logInViewModel
            )
        }
        composable("pickUp") {
            pickUpTextile(
                navController = navController,
                logInViewModel = logInViewModel
            )
        }
        composable("types") { typesTextile(navController = navController) }
        composable("history") {
            history(
                navController = navController,
                logInViewModel = logInViewModel
            )
        }
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("home") {
                popUpTo("logIn") { inclusive = true }
            }
        }
    }
}