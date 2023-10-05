package dev.sbytmacke.firstapp.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.sbytmacke.firstapp.R
import dev.sbytmacke.firstapp.databinding.ItemNoteBinding
import dev.sbytmacke.firstapp.ui.notes.models.Note

// Si es clickable o no, se hace aquí
class NotesAdapter(private val listNotes: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Inflamos el layout de un item, se genera el ViewHolder con la vista de un item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }


    // Pinta un item en la posición elegida
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = listNotes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return listNotes.size - 1
    }

    // Aquí decimos como visualiza
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Unimos layout, item_note con la clase Note
        val binding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            binding.contentTextView.text = note.uuid.toString()

            Glide.with(binding.imageView)
                .load(note.image)
                .centerCrop()
                .into(binding.imageView)
        }
    }

}
