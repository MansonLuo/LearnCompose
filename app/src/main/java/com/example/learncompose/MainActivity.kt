package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    Content()
}

@Composable
fun ListOfChoices(
    onNumberSelect: (number: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        (0 until 10).forEach { number ->
            Button(
                onClick = {
                    onNumberSelect(number)
                }
            ) {
                Text(
                    text = stringResource(R.string.number_select_pattern, number),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(
    name = "number list"
)
@Composable
fun Preview_ListOfChoices() {
    ListOfChoices(
        onNumberSelect = {}
    )
}

@Composable
fun ComposeResult(
    numberSelected: Int,
    res: Int
) {
    Text(
        text = stringResource(R.string.number_result_pattern, numberSelected, res),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(
    name = "compose result"
)
@Composable
fun Preview_ComposeResult() {
    ComposeResult(
        numberSelected = 1,
        res = 1
    )
}

@Preview
@Composable
fun Content() {
    val selectedNumber = remember {
        mutableStateOf<Int>(0)
    }
    val composeResult = remember {
        mutableStateOf<Int>(0)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ListOfChoices { number ->
            selectedNumber.value = number
            composeResult.value = getFactorialResult(selectedNumber.value)
        }

        ComposeResult(
            numberSelected = selectedNumber.value,
            res = composeResult.value
        )
    }
}

fun getFactorialResult(number: Int): Int {
    if (number == 0) return 1

    var res = 1

    (1 until number + 1).forEach {
        res *= it
    }

    return res
}