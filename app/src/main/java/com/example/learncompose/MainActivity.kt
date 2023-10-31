package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
                }
            }
        }
    }
}

@Preview
@Composable
fun MyCustomButton() {
    MaterialTheme(
        typography = Typography(
            headlineLarge = TextStyle(color = Color.Red)
        )
    ) {
        Row {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.headlineLarge
            )

            MaterialTheme(
                typography = Typography(
                    headlineLarge = TextStyle(color = Color.Blue)
                )
            ) {
                Text(
                    text = " Word",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}
