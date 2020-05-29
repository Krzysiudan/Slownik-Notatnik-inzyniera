package com.falatycze.slowniknotatnikinzyniera.ui.settings

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.falatycze.slowniknotatnikinzyniera.database.RecordRepository
import com.falatycze.slowniknotatnikinzyniera.database.RecordRoomDatabase
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "Settings"
    private val repository: RecordRepository

    init {
        val recordDao = RecordRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
    }

    fun resetLearningProgress(){
        viewModelScope.launch {
            repository.resetLearningProgress()
        }
        Log.d(TAG,"method: resetLearningProgress")
    }
}