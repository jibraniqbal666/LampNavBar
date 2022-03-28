package com.careem.lampnav.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.careem.lampnav.ui.model.NavRoute

@Composable
fun LampNavBar(
    navRoutes: List<NavRoute>,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    Card(elevation = 8.dp, modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(8.dp)) {
        NavBarLayout(navRoutes.indexOfFirst { it.destination == currentDestination?.route }) {
            navRoutes.forEach { route ->
                LampNavItem(icon = route.iconVector) {
                    navController.navigate(route.destination)
                }
            }
            Box(modifier = Modifier.layoutId("indicator")) {
                NavIndicatorItem(size = Size(64f, 54f), currentDestination?.route)
            }
        }
    }
}

