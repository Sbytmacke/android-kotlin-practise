package dev.sbytmacke.firstapp.ui.practiseActivities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.sbytmacke.firstapp.FirstActivity
import dev.sbytmacke.firstapp.SecondActivity
import dev.sbytmacke.firstapp.ThirthActivity
import dev.sbytmacke.firstapp.databinding.FragmentActivitiesBinding

// Mejor hacer un ViewModel con las constantes
const val EDAD_EXTRA = "claveEdad"

class ActivitiesFragment : Fragment() {

    private var _binding: FragmentActivitiesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)

        binding.buttonActivity1.setOnClickListener {
            val intent = Intent(context, FirstActivity::class.java)
            startActivity(intent) // Recogemos en la otra actividad la CLAVE
        }

        binding.buttonActivity2.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.buttonActivity3.setOnClickListener {
            val intent = Intent(context, ThirthActivity::class.java)
            startActivity(intent)
        }

        // Tenemos que dar permisos a la App para acceder a internet -> Android.Manifest.xml
        binding.buttonWeb.setOnClickListener {
            val urlWeb = binding.editUrlWeb.text.toString()
            val webPage = Uri.parse(urlWeb)
            val intentWeb = Intent(Intent.ACTION_VIEW, webPage)
            startActivity(intentWeb)
        }

        binding.buttonToCall.setOnClickListener {
            val phoneToCall = binding.editPhoneToCall.text.toString()
            val tel = Uri.parse("tel:$phoneToCall")
            val intentPhone = Intent(Intent.ACTION_DIAL, tel)
            startActivity(intentPhone)
        }

        binding.buttonToShare.setOnClickListener {
            val dataToShare = binding.editShare.text.toString()

            val intentToShare = Intent(Intent.ACTION_SEND)
            intentToShare.type = "text/plain"
            intentToShare.putExtra(Intent.EXTRA_TEXT, dataToShare)

            val election = Intent.createChooser(intentToShare, "Compartir mediante...")
            startActivity(election)
        }

        binding.buttonGeo.setOnClickListener {
            val location = "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
            val locationUri = Uri.parse(location)
            val intentGeo = Intent(Intent.ACTION_VIEW, locationUri)
            startActivity(intentGeo)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}