package com.example.learncompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Preview
@Composable
fun LaunchEffectDemo() {
    var clickCount by rememberSaveable {
        mutableStateOf(0)
    }
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    clickCount += 1
                }
            ) {
                if (clickCount > 0)
                    Text(text = "Restart")
                else
                    Text(text = "Start")
            }

            Button(
                enabled = clickCount > 0,
                onClick = {
                    clickCount = 0
                    count = 0
                }
            ) {
                Text(text = "Stop")
            }
        }

        if (clickCount > 0) {
            LaunchedEffect(key1 = clickCount) {
                count = 0

                while (isActive) {
                    count += 1

                    delay(1000)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun StopWatchWithoutLaunchEffect() {
    var clickCount by rememberSaveable {
        mutableStateOf(0)
    }
    val vm = viewModel<StopWatchViewModel>()

    Column {
        Row {
            Button(
                onClick = {
                    clickCount += 1
                    vm.startTiming()
                }
            ) {
                if (clickCount > 0) {
                    Text(text = "Restart")
                } else {
                    Text(text = "Start")
                }
            }

            Button(
                enabled = clickCount > 0,
                onClick = {
                    clickCount = 0
                    vm.stopTiming()
                }
            ) {
                Text(text = "Stop")
            }
        }
        
        Row {
            Text(text = vm.count.toString())
        }
    }
}

class StopWatchViewModel: ViewModel() {
    private val _count = mutableStateOf(0)
    val count: Int
        get() = _count.value
    var job: Job? = null

    fun startTiming() {
        job?.cancel()
        _count.value = 0

        job = viewModelScope.launch {
            while (isActive) {
                _count.value += 1

                delay(1000)
            }
        }
    }

    fun stopTiming() {
        job?.cancel()

        _count.value = 0
    }
}