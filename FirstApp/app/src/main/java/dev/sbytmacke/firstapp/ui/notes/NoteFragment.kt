package dev.sbytmacke.firstapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sbytmacke.firstapp.databinding.FragmentNoteBinding
import dev.sbytmacke.firstapp.models.createNewNote

class NoteFragment : Fragment() {

    /* Se utiliza para acceder a las vistas en el archivo de diseño XML asociado a este fragmento,
    con el mismo nombre pero en snake_case */
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el diseño XML antes de acceder a las vistas a través de binding, si no PETA!!!
        /*
         * Inflar la vista utilizando el archivo de diseño personalizado
         * val view = inflater.inflate(R.layout.my_custom_layout, container, false)
         * return view ( AL FINAL)
        */
        // Se infla/crea el diseño XML asociado, con el mismo nombre pero en snake_case
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        val noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        // Configura el RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializa el adaptador
        val notesAdapter = NotesAdapter()
        /*           object : NotesAdapter.OnDeleteClickListener {
               override fun onDeleteClick(note: Note) {
                   // Maneja la eliminación de la nota aquí (por ejemplo, desde el ViewModel)
                   homeViewModel.deleteNote(note)
               }
           })*/

        // Establece el adaptador en el RecyclerView
        recyclerView.adapter = notesAdapter

        // Patrón observer de las notas y actualiza el adaptador cuando cambian
        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.submitList(notes)
        }

        binding.fab.setOnClickListener {
            // Podemos abrir una pantalla  para que el usuario ingrese datos en la nota
            val newNote = createNewNote()
            noteViewModel.addNote(newNote) // Añade la nota al ViewModel
        }

        // Devolverá la vista principal del fragmento
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}