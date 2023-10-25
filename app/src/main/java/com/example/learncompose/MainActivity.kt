package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
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
    ColorPickerDemo()
}


@Composable
fun ColorPickerDemo() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(min(400.dp, maxWidth)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var color by remember {
                mutableStateOf(Color.Magenta)
            }

            ColorPicker(
                color = color
            ) {
                color = it
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color),
                textAlign = TextAlign.Center,
                text = "#0x${color.toArgb().toUInt().toString(16)}",
                style = MaterialTheme.typography.displayLarge.merge(
                    TextStyle(
                        color = color.complementary()
                    )
                )
            )
        }
    }
}

private fun Color.complementary(): Color {
    return Color(
        red = 1f - red,
        green = 1f - green,
        blue = 1f - blue
    )
}

@Composable
fun ColorPicker(
    color: Color,
    onColorChange: (Color) -> Unit
) {
    val red = color.red
    val green = color.green
    val blue = color.blue

    Column {
        Slider(
            value = red,
            onValueChange = {
                onColorChange(
                    Color(it, green, blue)
                )
            }
        )
        Slider(
            value = green,
            onValueChange = {
                onColorChange(
                    Color(red, it, blue)
                )
            }
        )
        Slider(
            value = blue,
            onValueChange = {
                onColorChange(
                    Color(red, green, it)
                )
            }
        )
    }
}

@Preview(
    device = Devices.PHONE
)
@Composable
fun showOnPhone() {
    ColorPickerDemo()
}

@Preview(
    device = Devices.DESKTOP
)
@Composable
fun showOnDesktop() {
    ColorPickerDemo()
}

@Preview(
    device = Devices.PIXEL_4_XL
)
@Composable
fun showOnPIXEL() {
    ColorPickerDemo()
}
