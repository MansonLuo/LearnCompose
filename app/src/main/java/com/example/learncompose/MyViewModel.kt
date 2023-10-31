package com.example.learncompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _text = MutableLiveData<String>("state 3")
    val text: LiveData<String>
        get() = _text

    fun setText(text: String) {
        _text.value = text
    }
}