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
    fun getBooks(id:String):Book?{
        var i = 0
        var agent: Book? = null
        while (i in data.value!!.indices&&agent==null){
            if(data.value!![i].id == id) agent = data.value!![i]
            i++
        }
        return agent
    }

}
