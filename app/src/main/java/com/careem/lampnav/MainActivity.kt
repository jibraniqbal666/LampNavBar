package com.careem.lampnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.careem.lampnav.ui.composables.LampNavBar
import com.careem.lampnav.ui.model.NavRoute
import com.careem.lampnav.ui.theme.LampNavTheme

class MainActivity : ComponentActivity() {
    private val navRoutes = listOf(
        NavRoute("profile", Icons.Default.Person),
        NavRoute("search", Icons.Default.Search),
        NavRoute("home", Icons.Default.Home),
        NavRoute("notification", Icons.Default.Notifications),
        NavRoute("favorites", Icons.Default.Favorite),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LampNavTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            LampNavBar(navRoutes, navController, currentDestination)
                        }
                    }
                ) {
                    AppNavHost(navRoutes, navController)
                }

            }
        }
    }
}

@Composable
fun AppNavHost(navRoutes: List<NavRoute>, navController: NavHostController) {
    NavHost(navController = navController, startDestination = navRoutes[2].destination) {
        navRoutes.forEach { route ->
            composable(route.destination) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = route.destination, fontSize = 36f.sp)
                }
            }
        }
    }
}

