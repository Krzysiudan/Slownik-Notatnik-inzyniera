package com.falatycze.slowniknotatnikinzyniera.ui.search
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.database.RecordRepository
import com.falatycze.slowniknotatnikinzyniera.database.RecordRoomDatabase
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG= "Search"
    private val repository: RecordRepository

    private val _results = MutableLiveData<List<Record>>()
    val results: LiveData<List<Record>> = _results

    init {
        val recordDao = RecordRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
    }

    fun loadResults(searchTag: String){
        viewModelScope.launch {
            _results.value = repository.searchInRecords(searchTag)
        }
        Log.d(TAG,"method: loadResults, searchTag: $searchTag, _results:  ${_results.toString()}")
    }
}