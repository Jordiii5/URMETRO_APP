package com.example.urmetro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

        // Observa els canvis a les dades del ViewModel i actualitza el RecyclerView
        // amb les noves dades rebudes.
        viewModel.data.observe(viewLifecycleOwner){
            // Configura el RecyclerView amb les dades rebudes
            setUpRecyclerView(it as MutableList<Book>?)
            binding.carrega.visibility=View.GONE
        }
        viewModel.success.observe(viewLifecycleOwner) { success ->
            if (success != null) {
                binding.carrega.isVisible = !success
                binding.recyclerView.isVisible = success

            }
        }

        //Fem una navegació a al módul d'entreteniment al polsar en aquest botó
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_audiollibresFragment_to_modulEntretenimentFragment)
        }
    }

    /**
     *@author Joel Garcia
     *
     * @params [book] rep totes les dades del llibre que hem polsat a la llista
     *
     * Agafa el llibre al que hem polsat y fa una navegació al detall enviant la id del llibre com a valor
     */
    override fun onClick(book: Book) {
        val viewModel= ViewModelProvider(requireActivity())[BooksViewModel::class.java]
        viewModel.currentBook.postValue(book)
        val action = AudiollibresFragmentDirections.actionAudiollibresFragmentToAudiollibreDetallFragment(book.id)
        findNavController().navigate(action)
    }

    /**
     * @author Joel Garcia
     *
     * Configura el RecyclerView amb la llista de llibres proporcionada.
     *
     * @param lista La llista de llibres per mostrar en el RecyclerView.
     * Si és nul·la, el RecyclerView es deixarà buit.
     */
    private fun setUpRecyclerView(lista: MutableList<Book>?){

        val myAdapter= lista.let { BookAdapter(it, this) }
        binding.recyclerView.apply {
            adapter=myAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}