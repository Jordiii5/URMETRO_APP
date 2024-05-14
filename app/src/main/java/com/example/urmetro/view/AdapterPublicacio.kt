package com.example.urmetro.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.urmetro.R
import com.example.urmetro.databinding.ItemPublicacioBinding
import com.example.urmetro.model.Publicacions
import com.example.urmetro.model.Usuari
import com.example.urmetro.viewModel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Adaptador per a la llista de publicacions.
 * @property publicacions Llista de publicacions a mostrar.
 * @property viewModel ViewModel associat a l'activitat o fragment.
 */
class AdapterPublicacio (
    private var publicacions: List<Publicacions>,
    private val viewModel: MyViewModel
): RecyclerView.Adapter<AdapterPublicacio.ViewHolder>() {
    private lateinit var context: Context
    lateinit var repository: ApiRepository

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemPublicacioBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        repository= ApiRepository("dni", "password")
        val view = LayoutInflater.from(context).inflate(R.layout.item_publicacio, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return publicacions.size
    }

    /**
     * Vincula les dades de la publicació amb la vista.
     * @param holder Mantenedor de les vistes.
     * @param position Posició de la publicació a la llista.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = publicacions[position]

        with(holder){
            binding.nomUsuari.text = post.usuari_id.toString()
            binding.descripcioFoto.text = post.publicacio_peu_foto

            Glide.with(context)
                .load(post.publicacio_foto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imatge)
            CoroutineScope(Dispatchers.IO).launch {
                val response = repository.getImage("posts/imagenespost/${post.publicacio_foto}")
                withContext(Dispatchers.Main){
                    if(response.isSuccessful && response.body() != null){
                        val foto = response.body()!!.bytes()
                        Glide.with(context)
                            .load(foto)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(binding.imatge)
                    }
                }
            }
        }
    }
}