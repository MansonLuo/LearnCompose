package com.example.learncompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learncompose.Repository

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TemperatureViewModel::class.java))
            return TemperatureViewModel(repository) as T
        else
            return DistancesViewModel(repository) as T
    }
}