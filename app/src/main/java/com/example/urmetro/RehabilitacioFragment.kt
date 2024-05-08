package com.example.urmetro

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
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
        reprodicirVideos()



    }

    /**
     *@author Joel Garcia
     *
     * Aquesta funció agafa els tres webViews de la pantalla y els asigna un video de youtube
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun reprodicirVideos() {
        val video1 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7IlAEVcAOlA?si=pS2TUcZoy1dplJ7X\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        binding.video1.loadData(video1,"text/html","utf-8")
        binding.video1.settings.javaScriptEnabled=true
        binding.video1.webChromeClient= WebChromeClient()

        val video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OP9w1b1-B4g?si=BNUp2zNb_GGUaBv8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        binding.video2.loadData(video2,"text/html","utf-8")
        binding.video2.settings.javaScriptEnabled=true
        binding.video2.webChromeClient= WebChromeClient()

        val video3= "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-QYqhmQj_ZI?si=On24NnKABttRxfiC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        binding.video3.loadData(video3,"text/html","utf-8")
        binding.video3.settings.javaScriptEnabled=true
        binding.video3.webChromeClient= WebChromeClient()
    }


    /**
     * @author Joel Garcia
     *
     * Aquesta funció mostra un dialeg que ofereix la opció de anar a la personalització de programes
     */
    private fun showChangePasswordDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_programacio)

        val codiSeg="1234"

        //Fixem els botons a variables
        val botonNo = dialog.findViewById<Button>(R.id.boto_no)
        val botonSi = dialog.findViewById<Button>(R.id.boto_si)
        val codi = dialog.findViewById<EditText>(R.id.codi)
        //Si pulsem aquest botó es tancatá el dialeg
        botonNo.setOnClickListener {
            dialog.hide()
        }
        //Aques botó comproba si el cosi de seguretat es correcte, si ho es, fa la navegació
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