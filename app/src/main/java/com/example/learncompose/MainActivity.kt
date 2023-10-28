package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
    TemperatureDemo()
}

@Preview
@Composable
fun TemperatureDemo() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var temperature by remember {
            mutableStateOf("0")
        }
        val options = listOf("华氏", "摄氏")
        var selectedOption by remember {
            mutableStateOf(options[0])
        }
        var convertedTemperature by remember {
            mutableStateOf("0.00")
        }


        TemperaturePanel(
            temperature = temperature,
            onTemperatureChange = {
                temperature = it
            },
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = {
                selectedOption = it
            }
        )

        Button(
            onClick = {
                convertedTemperature = convertTemperature(
                    temperature.toFloat() ?: 0f,
                    selectedOption
                )
            }
        ) {
            Text(
                text = "convert"
            )
        }

        Text(text = convertedTemperature)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperaturePanel(
    temperature: String,
    onTemperatureChange: (String) -> Unit,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        TextField(
            value = temperature.toString(),
            onValueChange = onTemperatureChange,
            modifier = Modifier.fillMaxWidth()
        )

        RadioButtonGroup(
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun TemperaturePanel() {
    var temparature by remember {
        mutableStateOf("36.5")
    }
    val options = listOf("华氏", "摄氏")
    var selectedOption by remember {
        mutableStateOf(options[0])
    }

    TemperaturePanel(
        temperature = temparature,
        onTemperatureChange = {
            temparature = it
        },
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = {
            selectedOption = it
        }
    )
}

@Composable
fun RadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        options.forEach { label ->
            RadioButtonWithLabel(
                option = label,
                selected = selectedOption == label
            ) {
                onOptionSelected(label)
            }
        }
    }
}

@Preview
@Composable
fun RadioButtonGroup() {
    val options = listOf<String>("华氏", "摄氏")
    var selectedOption by remember {
        mutableStateOf(options[0])
    }

    RadioButtonGroup(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { selectedLabel ->
            selectedOption = selectedLabel
        }
    )
}

@Composable
fun RadioButtonWithLabel(
    option: String,
    selected: Boolean,
    onOptionSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onOptionSelected)
        Text(
            text = option,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                end = 16.dp
            )
        )
    }
}

private fun convertTemperature(
    temperature: Float,
    currentUnit: String,
): String {
    lateinit var res: String

    if (currentUnit == "华氏") {
        val tmp = (temperature - 32) / 1.8

        res = "${tmp}摄氏度"
    } else {
        val tmp = (temperature * 1.8) + 32

        res = "${tmp}华氏度"
    }

    return res
}