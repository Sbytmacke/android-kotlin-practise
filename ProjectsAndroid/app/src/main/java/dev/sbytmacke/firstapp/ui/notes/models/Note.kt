package dev.sbytmacke.firstapp.ui.notes.models

import java.util.Date
import java.util.UUID

data class Note(
    val uuid: UUID,
    val title: String,
    val content: String,
    val creationDate: Date,
    val image: String?
) {
    companion object {
        var randomImage = 0
        fun createNewNote(): Note {
            val uuid = UUID.randomUUID()
            val title = "Título de la nota" // Obtener el título de alguna entrada del usuario
            val content = "Contenido de la nota"  // Obtener la nota de alguna entrada del usuario
            val currentDate = Date()
            val image = "https://loremflickr.com/320/240?lock=${randomImage++}"
            return Note(uuid, title, content, currentDate, image)
        }
    }
}




