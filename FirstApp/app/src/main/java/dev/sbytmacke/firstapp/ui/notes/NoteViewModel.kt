package dev.sbytmacke.firstapp.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.sbytmacke.firstapp.models.Note

/*
  Intercambiamos información entre vistas si es necesario, y almacenamos la información en
  caso de no querer perderla si cambiamos de vistas
*/
class NoteViewModel : ViewModel() {

    // Esto sería notas en memoria, aquí iría recoger las notas de la BB.DD
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    init {
        _notes.value = mutableListOf()
    }

    fun addNote(note: Note) {
        val currentNotes = _notes.value?.toMutableList() ?: mutableListOf()
        currentNotes.add(note)
        _notes.value = currentNotes
    }

    fun deleteNote(note: Note) {
        val currentNotes = _notes.value.orEmpty().toMutableList()
        currentNotes.remove(note)
        _notes.value = currentNotes
    }

    // Datos en memoria de TEXTO, entre vistas
    /*    private val _text = MutableLiveData<String>().apply {
            value = "Note-Home"
        }*/
    // val text: LiveData<String> = _text
}