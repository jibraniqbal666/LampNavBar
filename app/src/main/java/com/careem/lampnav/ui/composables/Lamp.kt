package com.careem.lampnav.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.careem.lampnav.ui.theme.LampNavTheme

@Composable
fun Lamp(size: Size = Size(64f, 54f), navRoute: String? = null) {
    val animationProgress by remember(navRoute) { mutableStateOf(Animatable(0f)) }
    LaunchedEffect(navRoute) {
        animationProgress.animateTo(1f, tween(1500))
    }

    val density = LocalDensity.current.density
    val w = density * size.width
    val h = density * size.height
    val path = Path().apply {
        lineTo((w * 0.75).toFloat(), 0f)
        lineTo((w * 0.9).toFloat(), h)
        lineTo((w * 0.1).toFloat(), h)
        lineTo((w * 0.25).toFloat(), 0f)
        close()
    }

    val lampGradient = Brush.verticalGradient(
        colors = listOf(Color(0x55FFFFFF), Color(0x00FFFFFF))
    )

    Canvas(
        modifier = Modifier
            .size(size.width.dp, size.height.dp)
            .graphicsLayer {
                alpha = if (((animationProgress.value * 100) % 10).toInt() == 0) 1f else 0f
            },
        onDraw = {
            drawPath(path, lampGradient)
        })
}

@Preview(showBackground = true)
@Composable
private fun LampPreview() {
    LampNavTheme {
        Lamp()
    }
}
