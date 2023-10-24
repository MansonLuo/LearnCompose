package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Factorial()
}

@Preview
@Composable
fun Factorial() {
    val expanded = remember {
        mutableStateOf(false)
    }
    val text = remember {
        mutableStateOf(
            factorialAsString(0)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.value,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.clickable {
                expanded.value = true
            }
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            (0 until 10).forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = "$it!")
                    },
                    onClick = {
                        text.value = factorialAsString(it)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

fun factorialAsString(number: Int): String {
    if (0 == number) return "0! = 0"

    var res = 1
    (1 until number + 1).forEach { n ->
        res *= n
    }

    return "$number! = $res"
}
