package dev.sbytmacke.firstapp.ui.boobaTyping

import android.content.Context
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStreamReader


class BoobaTypingViewModel : ViewModel() {

    fun escribirEnAlmacenamientoInterno(
        context: Context,
        nombreArchivo: String,
        contenido: String
    ) {
        try {
            val outputStream = context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
            outputStream.write(contenido.toByteArray())
            outputStream.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun leerDesdeAlmacenamientoInterno(context: Context, nombreArchivo: String): String {
        val texto = StringBuilder()

        try {
            val inputStream = context.openFileInput(nombreArchivo)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var linea: String?
            while (bufferedReader.readLine().also { linea = it } != null) {
                texto.append(linea).append("\n")
            }

            bufferedReader.close()
            inputStreamReader.close()
            inputStream.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return texto.toString()
    }
}