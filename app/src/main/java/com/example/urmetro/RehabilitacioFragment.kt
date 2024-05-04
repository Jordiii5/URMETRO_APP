package com.example.urmetro

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentRehabilitacioBinding

class RehabilitacioFragment : Fragment() {
    lateinit var binding: FragmentRehabilitacioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRehabilitacioBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_rehabilitacioFragment_to_modulSanitatFragment)
        }
        binding.programRobot.setOnClickListener {
            showChangePasswordDialog()
        }
    }
    private fun showChangePasswordDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_programacio)

        val codiSeg="qwe"

        val botonNo = dialog.findViewById<Button>(R.id.boto_no)
        val botonSi = dialog.findViewById<Button>(R.id.boto_si)
        val codi = dialog.findViewById<EditText>(R.id.codi)
        botonNo.setOnClickListener {
            dialog.hide()
        }
        botonSi.setOnClickListener {
            if(codi.text.toString() == codiSeg) {
                findNavController().navigate(R.id.action_rehabilitacioFragment_to_programarRobotFragment)
                dialog.hide()
            } else if (codi.text.toString() == "")  {
                Toast.makeText(context, "Introdueix el codi de seguretat", Toast.LENGTH_SHORT).show()
            }
            else   Toast.makeText(context, "Codi de seguretat incorrecte", Toast.LENGTH_SHORT).show()


        }
        dialog.show()
    }
}