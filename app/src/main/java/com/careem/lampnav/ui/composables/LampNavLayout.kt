package com.careem.lampnav.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId

@Composable
fun NavBarLayout(selectedIndex: Int, content: @Composable () -> Unit) {
    val indicatorIndex = remember { Animatable(0f) }
    val targetIndicatorIndex = selectedIndex.toFloat()
    LaunchedEffect(targetIndicatorIndex) {
        indicatorIndex.animateTo(
            targetIndicatorIndex, SpringSpec(
                stiffness = 800f,
                dampingRatio = 0.8f
            )
        )
    }

    Layout(content = content) { measurables, constraints ->

        val indicatorMeasurable = measurables.first { it.layoutId == "indicator" }
        val navItemWidth = constraints.maxWidth / measurables.size


        val itemPlaceables = measurables
            .filterNot { it == indicatorMeasurable }
            .mapIndexed { _, measurable ->
                measurable.measure(
                    constraints.copy(
                        minWidth = navItemWidth,
                        maxWidth = navItemWidth
                    )
                )
            }

        val indicatorPlaceable = indicatorMeasurable.measure(
            constraints.copy(
                minWidth = navItemWidth,
                maxWidth = navItemWidth
            )
        )

        layout(
            width = constraints.maxWidth - navItemWidth,
            height = itemPlaceables.maxByOrNull { it.height }?.height ?: 0
        ) {
            val indicatorLeft = indicatorIndex.value * navItemWidth
            indicatorPlaceable.place(x = indicatorLeft.toInt(), y = 0, zIndex = 1f)
            var x = 0
            itemPlaceables.forEach { placeable ->
                placeable.placeRelative(x = x, y = 0)
                x += placeable.width
            }
        }
    }
}


