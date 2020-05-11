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

class SingleRecordViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG= "SingleRecord"
    private val repository: RecordRepository

    private val _singleRecord = MutableLiveData<Record>()
    val singleRecord: LiveData<Record> = _singleRecord

    init {
        val recordDao = RecordRoomDatabase.getDatabase(application, viewModelScope).recordDao()
        repository = RecordRepository(recordDao)
    }

    fun loadSingleRecord(singleRecordId: Int){
        viewModelScope.launch {
            _singleRecord.value = repository.getSingleRecord(singleRecordId)
        }
        Log.d(TAG,"method: loadResults, searchTag: $singleRecordId, _results:  ${_singleRecord.toString()}")
    }
}