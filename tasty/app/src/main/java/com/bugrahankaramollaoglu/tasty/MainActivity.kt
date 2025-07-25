package com.bugrahankaramollaoglu.tasty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bugrahankaramollaoglu.tasty.data.AuthRepository
import com.bugrahankaramollaoglu.tasty.navigation.AppNavigation
import com.bugrahankaramollaoglu.tasty.retrofit.RetrofitInstance
import com.bugrahankaramollaoglu.tasty.ui.theme.TastyTheme
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModelFactory
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModelFactory
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

class MainActivity : ComponentActivity() {

    val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val prefManager = PreferencesManager(applicationContext)

        val authRepository = AuthRepository(RetrofitInstance.authService)

        val authViewModel: AuthViewModel by viewModels {
            AuthViewModelFactory(authRepository, prefManager)
        }

        val foodViewModel = FoodViewModel()

        val basketViewModel: BasketViewModel by viewModels {
            BasketViewModelFactory(application)
        }

        setContent {
            TastyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // navigation mekanizmasi buradan oluyor
                    AppNavigation(authViewModel, favouriteViewModel, basketViewModel, foodViewModel)
                }
            }
        }
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               

