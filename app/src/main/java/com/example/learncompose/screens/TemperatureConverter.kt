package com.example.learncompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.learncompose.viewmodels.TemperatureViewModel

@Composable
fun TemperatureConverter(
    viewModel: TemperatureViewModel
) {
    Text(text = "Temperature")
}