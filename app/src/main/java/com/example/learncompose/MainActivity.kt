package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

@Composable
fun CheckBoxWithLabel(
    checked: Boolean,
    label: String,
    onClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.clickable {
            onClicked(!checked)
        }
    ) {
        val (checkbox, text) = createRefs()

        Checkbox(
            checked = checked,
            onCheckedChange = onClicked,
            modifier = Modifier.constrainAs(checkbox) {}
        )

        Text(
            text = label,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(checkbox.end, margin = 8.dp)
                bottom.linkTo(checkbox.bottom)
                top.linkTo(checkbox.top)
            }
        )
    }
}

@Preview
@Composable
fun Preview_CheckBoxWithLabel() {
    CheckBoxWithLabel(checked = true, label = "Red", onClicked = {})
}

@Composable
fun ConstrainedLayoutDemo() {
    var red by remember {
        mutableStateOf(true)
    }
    var green by remember {
        mutableStateOf(true)
    }
    var blue by remember {
        mutableStateOf(true)
    }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (cbRed, cbGreen, cbBlue, boxRed, boxGreen, boxBlue) = createRefs()

        CheckBoxWithLabel(
            checked = red,
            label = "Red",
            onClicked = { red = it },
            modifier = Modifier.constrainAs(cbRed) {
                top.linkTo(parent.top)
            }
        )

        CheckBoxWithLabel(
            checked = green,
            label = "Green",
            onClicked = { green = it },
            modifier = Modifier.constrainAs(cbGreen) {
                top.linkTo(cbRed.bottom)
            }
        )

        CheckBoxWithLabel(
            checked = blue,
            label = "Blue",
            onClicked = { blue = it },
            modifier = Modifier.constrainAs(cbBlue) {
                top.linkTo(cbGreen.bottom)
            }
        )

        if (red) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .constrainAs(boxRed) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(cbBlue.bottom, margin = 16.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }

        if (green) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .constrainAs(boxGreen) {
                        start.linkTo(parent.start, margin = 32.dp)
                        end.linkTo(parent.end, margin = 32.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 32).dp)
                        bottom.linkTo(parent.bottom, margin = 32.dp)

                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }

        if (blue) {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .constrainAs(boxBlue) {
                        start.linkTo(parent.start, margin = 64.dp)
                        end.linkTo(parent.end, margin = 64.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 64).dp)
                        bottom.linkTo(parent.bottom, margin = 64.dp)

                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Preview
@Composable
fun Preview_ConstraintLayoutDemo() {
    ConstrainedLayoutDemo()
}