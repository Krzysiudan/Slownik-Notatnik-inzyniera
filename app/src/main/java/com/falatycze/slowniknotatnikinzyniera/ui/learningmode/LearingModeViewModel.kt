package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.database.RecordRepository
import com.falatycze.slowniknotatnikinzyniera.database.RecordRoomDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LearingModeViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: RecordRepository
    private val TAG = "LearningModeViewModel"
    // LiveData gives us updated words when they change.

    private val _showKnownQuestions = MutableLiveData<Event<Boolean>>().apply { value = Event(true) }

    val showKnownQuestions : LiveData<Event<Boolean>>
        get() = _showKnownQuestions

    val allRecords: LiveData<List<Record>>
    val allUnknownRecords: LiveData<List<Record>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val recordDao = RecordRoomDatabase.getDatabase(application,viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
        allRecords = repository.allRecords
        allUnknownRecords = repository.unknownRecords
    }

   fun userClickOnSwitch(isOn : Boolean){
       _showKnownQuestions.postValue(Event(isOn ))
   }

    suspend fun updateQuestionAsKnown(recordId: Int){
        repository.updateAsLearned(recordId)
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

    suspend fun getRandomRecord(): Record {
       return repository.getRandomRecord()
    }

    private suspend fun getRecords():LiveData<List<Record>>{
       return viewModelScope.async { repository.getRecords() }.await()
    }
}
