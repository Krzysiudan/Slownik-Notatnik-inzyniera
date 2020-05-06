package com.falatycze.slowniknotatnikinzyniera.database


import androidx.lifecycle.LiveData

class RecordRepository (private val recordDao: RecordDao){

        // Room executes all queries on a separate thread.
        // Observed LiveData will notify the observer when the data has changed.
        val allRecords: LiveData<List<Record>> = recordDao.getAlphabetizedRecords()
        val unknownRecords: LiveData<List<Record>> = recordDao.getNotLearnedRecords()

        suspend fun insert(record: Record) {
            recordDao.insert(record)
        }

        suspend fun updateAsLearned(recordId: Int){
            recordDao.updateAsLearned(recordId)
        }

        suspend fun getRandomRecord():Record{
            return recordDao.getRandomRecord()
        }

        suspend fun getRecords():LiveData<List<Record>>{
            return recordDao.getAlphabetizedRecords()
        }

        suspend fun getCategories(): List<String>{
            return  recordDao.getCategories()
        }
}