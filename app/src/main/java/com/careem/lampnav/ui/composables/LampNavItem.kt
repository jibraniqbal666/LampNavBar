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
    selected: Boolean,
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
        if (selected) Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size((0.7 * size.width).dp, (0.07 * size.height).dp)
                        .clip(RoundedCornerShape((0.07 * size.height).dp))
                        .background(color = Color.White)
                )
                Lamp()
            }
        }
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
}

@Preview(showBackground = true)
@Composable
fun LampNavItemPreview() {
    LampNavTheme {
        LampNavItem(Icons.Default.Home, true) {}
    }
}