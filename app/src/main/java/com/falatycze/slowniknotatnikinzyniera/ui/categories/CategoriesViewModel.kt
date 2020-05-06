package com.falatycze.slowniknotatnikinzyniera.ui.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.database.RecordRepository
import com.falatycze.slowniknotatnikinzyniera.database.RecordRoomDatabase
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecordRepository

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    init {
        val recordDao = RecordRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
    }

    fun loadCategories(){
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }
}