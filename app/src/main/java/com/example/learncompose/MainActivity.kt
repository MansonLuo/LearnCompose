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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureTextField(
    temperatrue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    callback: () -> Unit,
) {
    TextField(
        value = temperatrue,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(text = "输入温度")
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onAny = {
            callback()
        }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TemperatureTextField() {
    var temperatrue by remember {
        mutableStateOf("")
    }

    TemperatureTextField(
        temperatrue = temperatrue,
        onValueChange = { temperatrue = it },
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Composable
fun TemperatureRadioButton(
    selected: Boolean,
    resId: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                onClick(resId)
            }
        )

        Text(
            text = stringResource(id = resId),
            modifier = Modifier.padding(
                start = 8.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureRadioButton() {
    var selected by remember {
        mutableStateOf(true)
    }

    TemperatureRadioButton(
        selected = selected,
        resId = R.string.huashi,
        onClick = {}
    )
}

@Composable
fun TemperatureScaleButtonGroup(
    selected: Int,
    modifier: Modifier = Modifier,
    radioButtonClick: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TemperatureRadioButton(
            selected = selected == R.string.huashi,
            resId = R.string.huashi,
            onClick = { tagId ->
                radioButtonClick(tagId)
            }
        )

        TemperatureRadioButton(
            selected = selected == R.string.sheshi,
            resId = R.string.sheshi,
            onClick = { tagId ->
                radioButtonClick(tagId)
            }
        )
    }
}

@Preview
@Composable
fun TemperatureScaleButtonGroup() {
    var selectedId by remember {
        mutableStateOf(R.string.huashi)
    }

    TemperatureScaleButtonGroup(selected = selectedId) {
        selectedId = it
    }
}

@Preview
@Composable
fun FlowOfEventDemo() {
    var temperature by remember {
        mutableStateOf("")
    }
    var convertedTemperature by remember {
        mutableStateOf(0F)
    }
    var scale by remember {
        mutableStateOf(R.string.huashi)
    }
    val huaShiStr = "华氏"
    val sheShiStr = "摄氏"

    val calc = {
        val temp = temperature.toFloat()
        convertedTemperature = if (scale == R.string.sheshi)
            (temp * 1.8F) + 32F
        else
            (temp - 32F) / 1.8F
    }
    var result = remember(convertedTemperature) {
        if (convertedTemperature.isNaN())
            ""
        else
            "${convertedTemperature}${
                if (scale == R.string.huashi)
                    sheShiStr
                else
                    huaShiStr
            }"
    }
    var enabled = temperature.isNotBlank()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TemperatureTextField(
            temperatrue = temperature,
            onValueChange = { temperature = it }
        ) {
            calc()
        }

        TemperatureScaleButtonGroup(
            selected = scale,
            radioButtonClick = {
                scale = it
            }
        )

        Button(
            onClick = {
                calc()
            },
            enabled = enabled
        ) {
            Text(text = "Convert")
        }

        if (result.isNotBlank()) {
            Text(text = result)
        }
    }
}