package com.example.learncompose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.learncompose.viewmodels.DistancesViewModel

@Composable
fun DistancesConverter(
    viewModel: DistancesViewModel
) {
    Text(text = "Distances")
}