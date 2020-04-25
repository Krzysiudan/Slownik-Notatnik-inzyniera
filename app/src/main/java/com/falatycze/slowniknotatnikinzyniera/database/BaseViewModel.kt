package com.falatycze.slowniknotatnikinzyniera.database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class BaseViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: RecordRepository
    // LiveData gives us updated words when they change.
    val allRecords: LiveData<List<Record>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val recordDao = RecordRoomDatabase.getDatabase(application,viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
        allRecords = repository.allRecords
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the learining_mode_menu thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(record: Record) = viewModelScope.launch {
        repository.insert(record)
    }
}

