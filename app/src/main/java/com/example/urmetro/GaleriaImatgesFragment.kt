package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urmetro.databinding.FragmentGaleriaImatgesBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.view.AdapterPublicacio
import com.example.urmetro.viewModel.MyViewModel
import com.example.urmetro.viewModel.OnClickListener

class GaleriaImatgesFragment : Fragment() {
    lateinit var binding: FragmentGaleriaImatgesBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var userAdapterPost: AdapterPublicacio

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriaImatgesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.carrega.visibility=View.VISIBLE
        viewModel.fetchDataPublicacions()

        viewModel.dataPub.observe(viewLifecycleOwner) { listOfPost ->
            setUpRecyclerView(listOfPost as MutableList<Publicacions>)
            binding.carrega.visibility=View.GONE
        }

        viewModel.success.observe(viewLifecycleOwner) { success ->
            if (success != null) {
                binding.carrega.isVisible = !success
                binding.recyclerView.isVisible = success

            }
        }


        binding.recyclerView.layoutManager=GridLayoutManager(requireContext(), 3)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_modulSocialitzacioFragment)
        }
        binding.containerMeves.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_lesMevesImatgesFragment)
        }
        binding.containerPujar.setOnClickListener {
            findNavController().navigate(R.id.action_galeriaImatgesFragment_to_afegirImatgeFragment)
        }
    }

    fun setUpRecyclerView(listOfPost: MutableList<Publicacions>) {
        userAdapterPost = AdapterPublicacio(listOfPost, viewModel)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = userAdapterPost
        }
    }
}