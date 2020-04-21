package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.database.RecordRepository
import com.falatycze.slowniknotatnikinzyniera.database.RecordRoomDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class LearingModeViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: RecordRepository
    private val TAG = "LearningModeViewModel"
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
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(record: Record) = viewModelScope.launch {
        repository.insert(record)
    }

    suspend fun getRandomRecord(): Record {
       return repository.getRandomRecord()
    }

    private suspend fun getRecords():LiveData<List<Record>>{
       return viewModelScope.async { repository.getRecords() }.await()
    }
}
