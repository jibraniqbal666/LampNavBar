package com.careem.lampnav.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.careem.lampnav.ui.theme.LampNavTheme

@Composable
fun LampNavItem(
    icon: ImageVector,
    size: Size = Size(64f, 54f),
    onTap: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size.width.dp, size.height.dp)
            .background(Color.Black)
            .clickable(
                onClick = { onTap() }, indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        NavIcon(icon)
    }
}

@Composable
fun NavIndicatorItem(size: Size, navRoute: String?) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        NavTopIndicator(size)
        Lamp(size, navRoute = navRoute)
    }
}

@Composable
private fun NavIcon(icon: ImageVector) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White)
    }
}

@Composable
private fun NavTopIndicator(size: Size) {
    Box(
        modifier = Modifier
            .size((0.7 * size.width).dp, (0.07 * size.height).dp)
            .clip(RoundedCornerShape((0.07 * size.height).dp))
            .background(color = Color.White)
    )
}

@Preview(showBackground = true)
@Composable
private fun LampNavItemSelectedPreview() {
    LampNavTheme {
        Box(Modifier.size(64.dp, 54.dp)) {
            LampNavItem(Icons.Default.Home) {}
            NavIndicatorItem(size = Size(64f, 54f), null)
        }
    }
}