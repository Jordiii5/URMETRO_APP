package com.example.urmetro.audiollibres.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urmetro.audiollibres.model.Book
import com.example.urmetro.audiollibres.utils.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel : ViewModel() {
    private val repository = Repository()
    var data = MutableLiveData<List<Book>?>()
    var currentBook = MutableLiveData<Book>()

    /**
     * @author Joel Garcia
     *
     * Recupera les dades del repositori en un fil d'execució d'entrada/sortida.
     * Les dades recuperades són els tags obtinguts del repositori.
     * Aquesta funció utilitza Coroutines per gestionar l'asincronia.
     * Les dades recuperades es publiquen al fil principal i, si la resposta és
     * exitosa, s'assignen a la propietat de dades [data].
     */
    fun fetchData(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getTags()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    println(response.body()?.books)
                    data.postValue(response.body()?.books)
                }
            }
        }

    }

    /**
     * @author Joel Garcia
     *
     * @param id valor que rebrá, el identificador de cada llibre
     *
     * Filtra per la id que rep  oer cercar el llibre que necesitem
     *
     * @return Ens retorna el llibre filtrat per la id amb valor de la clase Book
     */
    fun getBooks(id:String):Book?{
        var i = 0
        var book: Book? = null
        while (i in data.value!!.indices&&book==null){
            if(data.value!![i].id == id) book = data.value!![i]
            i++
        }
        return book
    }

}
