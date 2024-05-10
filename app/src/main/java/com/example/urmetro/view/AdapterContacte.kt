package com.example.urmetro.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.urmetro.R
import com.example.urmetro.databinding.ItemContactoBinding
import com.example.urmetro.databinding.ItemPublicacioBinding
import com.example.urmetro.model.Contacte
import com.example.urmetro.model.Publicacions
import com.example.urmetro.model.Usuari
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdapterContacte (
    private var contactes: List<Contacte>,
    //private var listener: com.example.urmetro.viewModel.OnClickListener
): RecyclerView.Adapter<AdapterContacte.ViewHolder>() {
    private lateinit var context: Context
    lateinit var repository: ApiRepository

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemContactoBinding.bind(view)

        /*
        fun setListener(post: Publicacions){
            binding.root.setOnClickListener{
                listener.onClick(post)
            }
        }
         */
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        repository= ApiRepository("dni", "password")
        val view = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactes.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterContacte.ViewHolder, position: Int) {
        val contacte = contactes[position]

        with(holder){
            //setListener(post)
            binding.idContacte.text = contacte.contacte_id.toString()
            binding.nomContacte.text = contacte.contacte_nom
            binding.numeroTelefon.text = contacte.contacte_telefon.toString()

        }
    }
}