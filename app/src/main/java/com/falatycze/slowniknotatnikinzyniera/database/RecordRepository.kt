package com.falatycze.slowniknotatnikinzyniera.database


import androidx.lifecycle.LiveData
import com.falatycze.slowniknotatnikinzyniera.database.pojo.QuestionWithId
import kotlin.math.sin

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

        suspend fun searchInRecords(tag: String):List<Record>{
            return recordDao.searchByTags(tag)
        }

        suspend fun getSingleRecord(singleRecordId: Int): Record{
            return recordDao.getSingleRecord(singleRecordId)
        }

        suspend fun updateSingleRecord(singleRecord: Record) {
            return recordDao.updateSingleRecord(singleRecord)
        }

        suspend fun getQuestionsFromCategory(category: String):List<QuestionWithId>{
            return recordDao.getQuestionsFromCategory(category)
        }

        suspend fun getIdWhereQuestion(question: String):Int{
            return recordDao.getIdWhereQuestion(question)
        }

        suspend fun resetLearningProgress(){
            return recordDao.resetLearningProgress()
        }


}