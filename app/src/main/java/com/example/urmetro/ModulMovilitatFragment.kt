package com.example.urmetro

import UR3XmlRpcClient
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.urmetro.databinding.FragmentModulMovilitatBinding

class ModulMovilitatFragment : Fragment() {
    lateinit var binding: FragmentModulMovilitatBinding
    private lateinit var xmlRpcClient: UR3XmlRpcClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModulMovilitatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        xmlRpcClient = UR3XmlRpcClient()

        // Por ejemplo, mueve el robot a la posición original al iniciar la actividad
        val originalPosition = listOf(0.0, -1.5707963267948966, 0.0, -1.5707963267948966, 0.0, 0.0)
        xmlRpcClient.moveRobotToPosition(originalPosition)

        binding.formaLayout.setOnClickListener {
            val newPosition = listOf(0.0, -1.5707963267948966, 0.0, -2.5707963267948966, 0.0, 0.0)// Define la nueva posición aquí
            xmlRpcClient.moveRobotToPosition(newPosition)
        }

        binding.menu.setOnClickListener {
            findNavController().navigate(R.id.action_modulMovilitatFragment_to_menuFragment)
        }
        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_modulMovilitatFragment_to_ajudaMobilitatFragment)
        }
    }

}