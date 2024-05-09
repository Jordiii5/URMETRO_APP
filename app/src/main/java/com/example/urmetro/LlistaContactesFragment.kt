package com.example.urmetro

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentLlistaContactesBinding
import com.example.urmetro.viewModel.MyViewModel

class LlistaContactesFragment : Fragment() {
    lateinit var binding: FragmentLlistaContactesBinding
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLlistaContactesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_llistaContactesFragment_to_modulSocialitzacioFragment)
        }
        binding.afegirContacteButton.setOnClickListener{

            showAddContactDialog()
        }

        binding.sos.setOnClickListener{
            callEmergencyContact(112)
        }

        binding.contacteEmergencia.setOnClickListener {
            viewModel.currentUsuari.value?.let { it1 -> callEmergencyContact(it1.usuari_contacte_emergencia) }
        }
    }
    private fun callEmergencyContact(numTelefon: Int) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_contactes)
        val telefon = numTelefon.toString()

        val botonNo = dialog.findViewById<Button>(R.id.boto_no)
        val botonSi = dialog.findViewById<Button>(R.id.boto_si)
        botonNo.setOnClickListener {
            dialog.hide()
        }
        botonSi.setOnClickListener {
            call(telefon)
            dialog.hide()
        }
        dialog.show()
    }
    private fun showAddContactDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_afegir_contacte)


        val afegir = dialog.findViewById<Button>(R.id.afegir_cita_button)
        val nom = dialog.findViewById<EditText>(R.id.nom_contacte)
        val telefon = dialog.findViewById<EditText>(R.id.telefon_contacte)

        afegir.setOnClickListener {

            dialog.hide()
        }
        dialog.show()
    }

    private fun call(tel:String) {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$tel")))
    }


}