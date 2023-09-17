package dev.sbytmacke.firstapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
  Intercambiamos información entre vistas si es necesario, y almacenamos la información en
  caso de no querer perderla si cambiamos de vistas
*/
class HomeViewModel : ViewModel() {

    // Datos en memoria de IMÁGENES, entre vistas
    private val _imageResource = MutableLiveData<Int>()
    val getImageResource: LiveData<Int>
        get() = _imageResource

    fun updateImageResource(resourceId: Int) {
        _imageResource.value = resourceId
    }

    // Datos en memoria de TEXTO, entre vistas
    private val _text = MutableLiveData<String>().apply {
        value = "Note-Home"
    }
    val text: LiveData<String> = _text
}