package dev.sbytmacke.firstapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sbytmacke.firstapp.databinding.FragmentNoteBinding
import dev.sbytmacke.firstapp.ui.notes.models.Note
import dev.sbytmacke.firstapp.ui.notes.models.Note.Companion.createNewNote

class NoteFragment : Fragment() {

    /* Se utiliza para acceder a las vistas en el archivo de diseño XML asociado a este fragmento,
    con el mismo nombre pero en snake_case */
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    /*
    Gestor de diseño de la lista de notas
        1. LinearLayoutManager
        2. GridLayoutManager
        3. StaggeredGridLayoutManager (Para las notas es god, es flexible)
     */
    private lateinit var notesLayoutManager: LinearLayoutManager
    private lateinit var notesAdapter: NotesAdapter

    // Cargamos la lista de elementos, por ahora generamos random
    val listNotes = generateNotes(10)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Por si queremos pasar los datos entre fragmentos
        val noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        // Inflar el diseño XML antes de acceder a las vistas a través de binding, si no PETA!!!
        /*
         * Inflar la vista utilizando el archivo de diseño personalizado
         * val view = inflater.inflate(R.layout.my_custom_layout, container, false)
         * return view ( AL FINAL)
        */
        // Se infla/crea el diseño XML asociado, con el mismo nombre pero en snake_case
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        setRecyclerView()

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

    // Cargamos el Adapter con los datos y la ubicación del Layout en el RecyclerView
    fun setRecyclerView() {
        /*
          * Configura el RecyclerView (contenedor de tipo scroll)
          * Se reciclan los elementos, actualiza datos de forma inmediata (no se carga en memoria)
          * 1. Tenemos que tener una clase de tipo Datos
          * 2. Diseño de como se visualiza UN DATO
          * 3. Diseño de como se visualiza la lista -> Reciclerview.LayoutManager
          * 4. Adaptador que se encarga de unir los datos con el diseño  y donde se visualiza -> Reciclerview.Adapter
          * 5. ViewHolder -> como se visualiza la lista, Reciclerview
          * MainActivyt -> Datos -> Reciclerview -> Adapter -> LayoutManager -> Item
         */
        notesAdapter = NotesAdapter(listNotes)
        notesLayoutManager = LinearLayoutManager(requireContext()) // o this? o requireContext()

        binding.recyclerView.apply {
            adapter = notesAdapter
            layoutManager = notesLayoutManager
        }
    }

    // Generar random las notas que queramos
    fun generateNotes(numerNotes: Int): MutableList<Note> {
        val listNotes = mutableListOf<Note>()
        for (i in 0 until numerNotes) {
            listNotes.add(createNewNote())
        }
        return listNotes
    }
}