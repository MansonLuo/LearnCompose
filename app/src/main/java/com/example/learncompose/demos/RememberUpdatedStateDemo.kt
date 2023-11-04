package com.example.learncompose.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Preview
@Composable
fun Example() {
    var count by remember {
        mutableStateOf(0)
    }
    var unit by remember {
        mutableStateOf("$")
    }
    var text by remember {
        mutableStateOf("$count$unit")
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    if (isRunning) {
        LaunchedEffect(Unit) {
            count = 0

            while (isActive) {
                count += 1
                text = "$count$unit"
                delay(1000)
            }
        }
    } else {
        count = 0
        text = "0$unit"
    }
    
    Column {
        Text(text = text)
        Button(onClick = { isRunning = !isRunning }) {
           Text(text = "start/stop")
        }
        Button(onClick = { unit = "*"}) {
            Text(text = "change Unit to *")
        }
    }
}