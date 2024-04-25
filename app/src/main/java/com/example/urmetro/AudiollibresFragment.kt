package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urmetro.audiollibres.model.Book
import com.example.urmetro.audiollibres.utils.MyOnClickListener
import com.example.urmetro.audiollibres.viewmodel.BookAdapter
import com.example.urmetro.audiollibres.viewmodel.BooksViewModel
import com.example.urmetro.databinding.FragmentAudiollibresBinding

class AudiollibresFragment : Fragment() , MyOnClickListener {
    lateinit var binding: FragmentAudiollibresBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAudiollibresBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel= ViewModelProvider(requireActivity())[BooksViewModel::class.java]

        viewModel.fetchData()

        viewModel.data.observe(viewLifecycleOwner){
            setUpRecyclerView(it as MutableList<Book>?)
        }

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_audiollibresFragment_to_modulEntretenimentFragment)
        }
    }

    override fun onClick(book: Book) {
        val viewModel= ViewModelProvider(requireActivity())[BooksViewModel::class.java]
        viewModel.currentBook.postValue(book)
        val action = AudiollibresFragmentDirections.actionAudiollibresFragmentToAudiollibreDetallFragment(book.id)
        findNavController().navigate(action)
    }
    private fun setUpRecyclerView(lista: MutableList<Book>?){

        val myAdapter= lista.let { BookAdapter(it, this) }
        binding.recyclerView.apply {
            adapter=myAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}