package com.douglastaquary.busaoshare.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout()
        }
    }
}

sealed class Screen(val title: String) {
    object TripListScreen : Screen("Meus viagens")
    object TripDetailScreen : Screen("Detalhes da viagem")
}

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    BusShareTheme {
        NavHost(navController, startDestination = Screen.TripListScreen.title) {
            composable(Screen.TripListScreen.title) {
                TripListScreen(searchText = "paulista", popBack = null)
            }
        }
    }
}
