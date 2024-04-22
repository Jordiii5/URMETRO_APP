package com.example.urmetro

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentJocMenuTrivialBinding


class JocMenuTrivialFragment : Fragment() {

    lateinit var binding: FragmentJocMenuTrivialBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJocMenuTrivialBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playTrivial.setOnClickListener {
            findNavController().navigate(R.id.action_jocMenuTrivialFragment_to_tematicaTrivialFragment)

        }
        binding.backMenu.setOnClickListener{
            findNavController().navigate(R.id.action_jocMenuTrivialFragment_to_jocsFragment)

        }

        binding.helpTrivial.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this.context).create()
            alertDialog.setTitle("Instrucciones")
            alertDialog.setMessage("El juego consiste en responder las diferentes preguntas que te proponemos con un total de 10 segundos por pregunta. Una vez pasado el tiempo, saltarás a la siguiente pregunta."
                    + "Puedes escoger la temática que más te interese, te ves capaz para hacerlas todas?" +
                    "Al finalizar la partida conseguiras unos puntos totales por cada pregunta acertada en su tiempo, estos puntos quedaran registrados en el ranking con el resto de jugadores.")
            alertDialog.setButton(
                AlertDialog.BUTTON_NEUTRAL, "ACEPTAR"
            ) { dialog, which -> dialog.dismiss() }
            alertDialog.show()
        }

    }


}