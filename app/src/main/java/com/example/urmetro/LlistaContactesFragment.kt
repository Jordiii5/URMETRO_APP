package com.example.urmetro

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.urmetro.databinding.FragmentLlistaContactesBinding
import com.example.urmetro.databinding.ItemContactoBinding
import com.example.urmetro.model.Contacte
import com.example.urmetro.view.AdapterContacte
import com.example.urmetro.viewModel.ContacteOnClick
import com.example.urmetro.viewModel.MyViewModel

class LlistaContactesFragment : Fragment(), ContacteOnClick {
    lateinit var binding: FragmentLlistaContactesBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var userAdapterContacte: AdapterContacte
    private lateinit var bindingMeusContactes: ItemContactoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLlistaContactesBinding.inflate(layoutInflater)
        bindingMeusContactes = ItemContactoBinding.inflate(layoutInflater)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDataContactes()

        viewModel.dataContacte.observe(viewLifecycleOwner) { listOfContacte ->
            val userContacte = listOfContacte.filter { it.usuari_id == viewModel.currentUsuari.value?.usuari_id }
            setUpRecyclerView(userContacte.toMutableList())
        }

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_llistaContactesFragment_to_modulSocialitzacioFragment)
        }
        binding.afegirContacteButton.setOnClickListener{
            showAddContactDialog()
        }

        binding.sos.setOnClickListener{
            call("112")
        }

        binding.contacteEmergencia.setOnClickListener {
            viewModel.currentUsuari.value?.let { it1 -> call(it1.usuari_contacte_emergencia.toString()) }
        }
    }

    fun setUpRecyclerView(listOfContacte: MutableList<Contacte>) {
        userAdapterContacte = AdapterContacte(listOfContacte, this)
        binding.recyclerContactes.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerContactes.apply {
            setHasFixedSize(true)
            adapter = userAdapterContacte
        }
    }

    private fun callContact(numTelefon: Int, nom: String) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_contactes)
        val telefon = numTelefon.toString()


        val botonNo = dialog.findViewById<Button>(R.id.boto_no)
        val botonSi = dialog.findViewById<Button>(R.id.boto_si)
        val titol = dialog.findViewById<TextView>(R.id.pregunta_text)
        titol.text="ESTAS SEGUR DE QUE VOLS TRUCAR A ${nom.uppercase()}?"
        botonNo.setOnClickListener {
            dialog.hide()
        }
        botonSi.setOnClickListener {
            call(telefon)
            dialog.hide()
        }
        dialog.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showAddContactDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_afegir_contacte)


        val afegir = dialog.findViewById<Button>(R.id.afegir_cita_button)
        val nom = dialog.findViewById<EditText>(R.id.nom_contacte)
        val telefon = dialog.findViewById<EditText>(R.id.telefon_contacte)

        afegir.setOnClickListener {
            if (nom.text.toString().isNotBlank() && telefon.text.toString().isNotBlank()){
                val contacte = Contacte(
                    0,
                    nom.text.toString(),
                    telefon.text.toString().toInt(),
                    viewModel.currentUsuari.value!!.usuari_id
                )

                viewModel.postContacte(contacte)
                Toast.makeText(context, "Contacte afegit correctament", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Error al afegir contacte", Toast.LENGTH_LONG).show()
            }

            dialog.hide()
        }
        dialog.show()
    }

    private fun call(tel:String) {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$tel")))
    }


    override fun onClickContacte(contacte: Contacte) {
        val viewModel= ViewModelProvider(requireActivity())[MyViewModel::class.java]
        viewModel.contacte.postValue(contacte)
        val num=contacte.contacte_telefon
        val nom=contacte.contacte_nom
       callContact(num,nom)
    }

}