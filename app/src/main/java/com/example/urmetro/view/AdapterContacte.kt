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

/**
 * Adaptador per a la llista de contactes.
 * @property contactes Llista de contactes a mostrar.
 * @property listener Gestor del clic sobre els contactes.
 */
class AdapterContacte (
    private var contactes: List<Contacte>,
    private var listener: com.example.urmetro.viewModel.ContacteOnClick
): RecyclerView.Adapter<AdapterContacte.ViewHolder>() {
    private lateinit var context: Context
    lateinit var repository: ApiRepository

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemContactoBinding.bind(view)

        /**
         * Estableix el listener per al clic en un contacte.
         * @param post El contacte que es fa clic.
         */
        fun setListener(post: Contacte){
            binding.root.setOnClickListener{
                listener.onClickContacte(post)
            }
        }

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

    /**
     * Vincula les dades del contacte amb la vista.
     * @param holder Mantenedor de les vistes.
     * @param position Posició del contacte a la llista.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdapterContacte.ViewHolder, position: Int) {
        val contacte = contactes[position]

        with(holder){
            setListener(contacte)
            binding.idContacte.text = contacte.contacte_id.toString()
            binding.nomContacte.text = contacte.contacte_nom
            binding.numeroTelefon.text = contacte.contacte_telefon.toString()

        }
    }
}