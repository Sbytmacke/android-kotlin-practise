package dev.sbytmacke.firstapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Login Fragment"
    }

    val text: LiveData<String> = _text
}