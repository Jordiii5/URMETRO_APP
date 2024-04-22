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
            alertDialog.setTitle("Instruccions")
            alertDialog.setMessage("El joc consisteix a respondre les diferents preguntes que us proposem amb un total de 10 segons per pregunta. Un cop passat el temps, saltaràs a la pregunta següent.\n" +
                    "\"Pots escollir la temàtica que més t'interessi, et veus capaç per fer-les totes?\n" +
                    "\"En finalitzar la partida aconseguiràs uns punts totals per cada pregunta encertada al seu temps.")
            alertDialog.setButton(
                AlertDialog.BUTTON_NEUTRAL, "ACCEPTAR"
            ) { dialog, which -> dialog.dismiss() }
            alertDialog.show()
        }

    }


}