package com.example.learncompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
    group = "group-2"
)
@Composable
fun Welcome() {
    Text(
        text = stringResource(id = R.string.wellcome_message),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(
    group = "group-1"
)
@Composable
fun Greeting(
    @PreviewParameter(HelloProvider::class)
    name: String
) {
    Text(
        text = stringResource(id = R.string.hello, name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextAndButton(
    name: MutableState<String>,
    nameEntered: MutableState<Boolean>
) {
    Row(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(text = stringResource(R.string.hint))
            },
            modifier = Modifier
                .alignByBaseline()
                .weight(1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions( onAny = {
                nameEntered.value = true
            })
        )
        Button(
            modifier = Modifier
                .alignByBaseline()
                .padding(8.dp),
            onClick = {
                nameEntered.value = true
            }
        ) {
            Text(text = stringResource(R.string.done))
        }
    }
}

@Composable
fun Hello() {
    val name = remember {
        mutableStateOf("")
    }
    val nameEntered = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (nameEntered.value) {
            Greeting(name = name.value)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Welcome()
                TextAndButton(name = name, nameEntered = nameEntered)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PHONE
)
@Composable
fun Preview_Hello() {
    Hello()
}

class HelloProvider() : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() =
            listOf("PreviewParameterProvider").asSequence()
}