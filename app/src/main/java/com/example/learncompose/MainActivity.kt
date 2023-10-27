package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learncompose.ui.theme.LearnComposeTheme

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

@Preview
@Composable
fun App() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OverlappingRow(
            overlapFactor = 0.7f
        ) {
            val imageIds = listOf<Int>(
                R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.four,
                R.drawable.five,
                R.drawable.six
            )

            repeat(imageIds.size) {
                Image(
                    painter = painterResource(id = imageIds[it]),
                    contentDescription = "",
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .border(width = 1.dp, color = Color.White, shape = CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(
                    text = "10+",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    @FloatRange(0.1, 1.0) overlapFactor: Float = 0.5f,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        measurePolicy = overlappingRowMeasurePolicy(overlapFactor),
        content = content
    )
}

private fun overlappingRowMeasurePolicy(overlapingFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map {
        it.measure(constraints)
    }

    val width = (placeables.subList(1, placeables.size).sumOf { it.width } * overlapingFactor + placeables[0].width).toInt()
    val height = placeables.maxOf { it.height }

    layout(width, height) {
        var xPos = 0

        placeables.forEach {  placeable ->
            placeable.placeRelative(xPos, 0, 0f)

            xPos += (placeable.width * overlapingFactor).toInt()
        }
    }
}