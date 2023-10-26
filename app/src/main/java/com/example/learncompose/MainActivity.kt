package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun App() {
}

@Preview(
    widthDp = 400,
    heightDp = 300
)
@Composable
fun MyCustomText() {
    Text(
        text = "Hello Compose",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxSize()
            .drawYellowCross()
    )
}

fun Modifier.drawYellowCross() = then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                Color.Yellow,
                start = Offset(0f, 0f),
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10f
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(0f, size.height - 1),
                end = Offset(size.width - 1, 0f),
                strokeWidth = 10f
            )

            drawContent()
        }
    }
)