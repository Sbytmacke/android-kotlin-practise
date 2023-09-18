package dev.sbytmacke.firstapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.sbytmacke.firstapp.models.Note

/*
  Intercambiamos información entre vistas si es necesario, y almacenamos la información en
  caso de no querer perderla si cambiamos de vistas
*/
class HomeViewModel : ViewModel() {

    // NOTES
    private val _notes = MutableLiveData<List<String>>()
    val notes: LiveData<List<String>> get() = _notes

    init {
        _notes.value = mutableListOf()
    }

    fun addNote(note: Note) {
        val currentNotes = _notes.value?.toMutableList() ?: mutableListOf()
        currentNotes.add(note.content)
        _notes.value = currentNotes
    }
    // NOTES

    // Datos en memoria de IMÁGENES, entre vistas
    private val _imageResource = MutableLiveData<Int>()
    val getImageResource: LiveData<Int>
        get() = _imageResource

    fun updateImageResource(resourceId: Int) {
        _imageResource.value = resourceId
    }

    fun deleteNote(note: Note) {
        val currentNotes = _notes.value.orEmpty().toMutableList()
        currentNotes.remove(note.content)
        _notes.value = currentNotes
    }

    // Datos en memoria de TEXTO, entre vistas
    /*    private val _text = MutableLiveData<String>().apply {
            value = "Note-Home"
        }*/
    // val text: LiveData<String> = _text
}