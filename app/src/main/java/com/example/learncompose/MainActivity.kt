package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    RadioButtonGroup()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperaturePanel(
    temperature: Float,
    onTemperatureChange: (String) -> Unit,
    unit: TemperatureUnit,
    onUnitChange: () -> Unit
) {
    Column {
        TextField(
            value = temperature.toString(),
            onValueChange = onTemperatureChange
        )
    }
}


@Composable
fun RadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
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
        selectedOption = selectedOption
    ) { selectedLabel ->
        selectedOption = selectedLabel
    }
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

sealed class TemperatureUnit {
    object HuaShi: TemperatureUnit() {
        override fun toString(): String {
            return "华氏"
        }
    }

    object SheShi: TemperatureUnit() {
        override fun toString(): String {
            return "摄氏"
        }
    }
}