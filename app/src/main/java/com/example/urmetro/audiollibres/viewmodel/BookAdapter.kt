package com.example.urmetro.audiollibres.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.urmetro.R
import com.example.urmetro.audiollibres.model.Book
import com.example.urmetro.audiollibres.utils.MyOnClickListener
import com.example.urmetro.databinding.ItemBookBinding

class BookAdapter(private val books: MutableList<Book>?, private val listener: MyOnClickListener):
    RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemBookBinding.bind(view)
        fun setListener(book: Book){
            binding.root.setOnClickListener {
                listener.onClick(book)
            }
        }

    }
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books?.get(position)
        with(holder) {
            setListener(book!!)
            binding.titol.text = book.title
            binding.nom.text = book.authors[0].first_name
            binding.cognom.text = book.authors[0].last_name
        }

    }


    override fun getItemCount(): Int {
        return books!!.size
    }

}
