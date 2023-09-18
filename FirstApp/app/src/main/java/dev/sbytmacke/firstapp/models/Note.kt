package dev.sbytmacke.firstapp.models

import java.util.Date
import java.util.UUID

data class Note(
    val uuid: UUID,
    val title: String,
    val content: String,
    val creationDate: Date
)

fun createNewNote(): Note {
    val uuid = UUID.randomUUID()
    val title = "Título de la nota" // Obtener el título de alguna entrada del usuario
    val content = "Contenido de la nota"  // Obtener la nota de alguna entrada del usuario
    val currentDate = Date()
    return Note(uuid, title, content, currentDate)
}




