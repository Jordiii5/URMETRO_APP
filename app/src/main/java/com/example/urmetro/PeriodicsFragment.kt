package com.example.urmetro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentPeriodicsBinding

class PeriodicsFragment : Fragment() {
    private var _binding: FragmentPeriodicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeriodicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Per a cada botó truquem a la funció per a obrir links amb la url corresponent
        binding.marca.setOnClickListener {
            openLink("https://www.marca.com/" )
        }
        binding.vMinutis.setOnClickListener {
            openLink("https://www.20minutos.es/" )
        }
        binding.hola.setOnClickListener {
            openLink("https://www.hola.com/" )
        }
        binding.elPais.setOnClickListener {
            openLink("https://elpais.com/" )
        }


        binding.arrowBack.setOnClickListener {
           findNavController().navigate(R.id.action_periodicsFragment_to_modulEntretenimentFragment)
        }
    }

    /**
     * @author
     *
     * @param url Url del diari corresponent
     *
     * Aquesta funció rep una url i obre el navegador predeterminat amb aquesta pagina web
     */
    private fun openLink(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
