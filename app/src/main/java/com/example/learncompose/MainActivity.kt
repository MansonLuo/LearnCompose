package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.theme.LearnComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
}

@Preview
@Composable
fun CustomLayoutDemo() {
    CustomFlexLayout() {
        repeat(49) {
            ColorBox()
        }
    }
}

@Composable
fun ColorBox() {
    Box(
        modifier = Modifier
            .background(randomColor())
            .width((40 * random123()).dp)
            .height((30 * random123()).dp)
    )
}

private fun random123(): Int {
    return Random.nextInt(1, 4)
}

private fun randomColor() =
    when (random123()) {
        1 -> Color.Red
        2 -> Color.Green
        else -> Color.Blue
    }


@Composable
fun CustomFlexLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        measurePolicy = simpleFlexLayoutMeasurePolicy(),
        content = content
    )
}

private fun simpleFlexLayoutMeasurePolicy(): MeasurePolicy = MeasurePolicy { measurables, constraints ->  
    val placeables = measurables.map {
        it.measure(constraints)
    }

    layout(
        constraints.maxWidth,
        constraints.maxHeight
    ) {
        var xPos = 0
        var yPos = 0
        var maxY = 0

        placeables.forEach {  placeable ->
            if (xPos + placeable.width > constraints.maxWidth) {
                xPos = 0
                yPos += maxY
                maxY = 0
            }

            placeable.placeRelative(
                x = xPos,
                y = yPos
            )

            xPos += placeable.width

            if (maxY < placeable.height) {
                maxY = placeable.height
            }
        }
    }

}