package com.example.learncompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncompose.R
import com.example.learncompose.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemperatureViewModel(private val repository: Repository) : ViewModel() {
    private val _scale: MutableStateFlow<Int> = MutableStateFlow(
        repository.getInt("scale", R.string.celsius)
    )
    val scale: StateFlow<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value

        viewModelScope.launch {
            repository.putInt("scale", value)
        }
    }

    private val _temperature: MutableStateFlow<String> = MutableStateFlow(
        repository.getString("temperature", "")
    )
    val temperature: StateFlow<String>
        get() = _temperature

    fun getTemperatureAsFloat(): Float =
        temperature.value.let {
            return try {
                it.toFloat()
            } catch (e: NumberFormatException) {
                Float.NaN
            }
        }

    fun setTemperature(value: String) {
        _temperature.value = value
        viewModelScope.launch {
            repository.putString("temperature", value)
        }
    }

    private val _convertedTemperature: MutableStateFlow<Float> = MutableStateFlow(
        getTemperatureAsFloat()
    )

    val convertedTemperature: MutableStateFlow<Float>
        get() = _convertedTemperature

    fun convert() {
        viewModelScope.launch {
            getTemperatureAsFloat().let {
                _convertedTemperature.value =
                    if (!it.isNaN())
                        if (_scale.value == R.string.celsius)
                            (it * 1.8F) + 32F
                        else
                            (it - 32F) / 1.8F
                    else
                        Float.NaN
            }
        }
    }
}