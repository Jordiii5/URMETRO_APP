package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.urmetro.databinding.FragmentLesMevesImatgesBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.view.AdapterPublicacio
import com.example.urmetro.viewModel.MyViewModel

class LesMevesImatgesFragment : Fragment() {
    lateinit var binding: FragmentLesMevesImatgesBinding
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var userAdapterPost: AdapterPublicacio

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLesMevesImatgesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDataPublicacions()

        viewModel.dataPub.observe(viewLifecycleOwner) { listOfPost ->
            val userPosts = listOfPost.filter { it.usuari_id == viewModel.currentUsuari.value?.usuari_id }
            setUpRecyclerView(userPosts.toMutableList())
        }

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_lesMevesImatgesFragment_to_galeriaImatgesFragment)
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