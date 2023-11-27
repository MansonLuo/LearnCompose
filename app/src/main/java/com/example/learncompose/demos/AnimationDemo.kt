package com.example.learncompose.demos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun StateChangeDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color = if (toggled) Color.White
    else Color.Red

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(text = "Change color")
        }

        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Preview
@Composable
fun SingleValueAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (toggled)
            Color.White
        else
            Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(text = "Change color")
        }

        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Preview
@Composable
fun MultipleValuesAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(
        targetState = toggled,
        label = "toggledTransition"
    )
    val borderWidth by transition.animateDp(
        label = "borderWidthTransition"
    ) { state ->
        if (state)
            10.dp
        else
            1.dp
    }
    val degrees by transition.animateFloat(
        label = "degreesTransition"
    ) { state ->
        if (state)
            -90F
        else
            0F
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { toggled = !toggled}
        ) {
            Text(text = "start animation")
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .border(borderWidth, Color.Black)
                .size(128.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "text",
                modifier = Modifier.rotate(degrees)
            )
        }
    }
}

@Preview
@Composable
fun AnimatedVisibilityDemo() {
    var show by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { show = !show}
        ) {
            Text(text = "show or hide")
        }

        AnimatedVisibility(
            visible = show,
            enter = slideInHorizontally() + fadeIn() + expandHorizontally(),
            exit = slideOutVertically() + fadeOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .background(Color.Black)
                    .size(128.dp)
            )
        }
    }
}

@Preview
@Composable
fun SizeChangeAnimationDemo() {
    var size by remember {
        mutableStateOf(1F)
    }

    Column {
        Slider(
            value = size,
            valueRange = (1F..4F),
            steps = 3,
            onValueChange = {
                size = it
            },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "#1\n#2\n#3\n#4\n#5\n",
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .animateContentSize(animationSpec = snap(1000)),
            maxLines = size.toInt(),
            color = Color.Blue
        )
    }
}