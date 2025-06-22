package com.bugrahankaramollaoglu.tasty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bugrahankaramollaoglu.tasty.ui.theme.TastyTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = AuthRepository(RetrofitClient.apiService)
        val authViewModel: AuthViewModel by viewModels {
            AuthViewModelFactory(repository)
        }

        setContent {
            TastyTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Red) {
                    AppNavigation(authViewModel)
                }
            }
        }
    }
}

