package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
}

@Preview
@Composable
fun PredefinedLayoutDemo() {
    var isRedChecked by remember {
        mutableStateOf(true)
    }
    var isGreenChecked by remember {
        mutableStateOf(true)
    }
    var isBlueChecked by remember {
        mutableStateOf(true)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CheckBoxWithTag(checked = isRedChecked, text = "Red") {
            isRedChecked = it
        }
        CheckBoxWithTag(checked = isGreenChecked, text = "Green") {
            isGreenChecked = it
        }
        CheckBoxWithTag(checked = isBlueChecked, text = "Blue") {
            isBlueChecked = it
        }


        StackedBoxes(
            isOuterBoxShow = isRedChecked,
            isMiddleBoxShow = isGreenChecked,
            isInnerBoxShow = isBlueChecked
        )
    }
}

@Composable
fun CheckBoxWithTag(
    checked: Boolean,
    text: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onCheckedChange(!checked)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.alignByBaseline()
        )

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .alignByBaseline()
                .padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun Preview_CheckBoxWithTag() {
    CheckBoxWithTag(
        checked = true,
        text = "Red",
        onCheckedChange = {}
    )
}

@Composable
fun StackedBoxes(
    isOuterBoxShow: Boolean,
    isMiddleBoxShow: Boolean,
    isInnerBoxShow: Boolean
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        if (isOuterBoxShow) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        }

        if (isMiddleBoxShow) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(32.dp)
                    .background(Color.Green)
            )
        }

        if (isInnerBoxShow) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(64.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Preview
@Composable
fun Preview_StackedBoxes() {
    StackedBoxes(isOuterBoxShow = true, isMiddleBoxShow = true, isInnerBoxShow = true)
}