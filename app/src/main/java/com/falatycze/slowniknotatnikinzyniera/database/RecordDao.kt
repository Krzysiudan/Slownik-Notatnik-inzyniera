package com.falatycze.slowniknotatnikinzyniera.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.falatycze.slowniknotatnikinzyniera.database.pojo.QuestionWithId

@Dao
interface RecordDao{

    @Query("SELECT * from records ORDER BY id ASC")
    fun getAlphabetizedRecords(): LiveData<List<Record>>

    @Query("SELECT question  from records ORDER BY question ASC")
    fun getAlphabetizedQuestions(): LiveData<List<String>>

    @Query("SELECT * from records WHERE learned = 0")
    fun getNotLearnedRecords(): LiveData<List<Record>>

    @Query("SELECT DISTINCT category from records")
    suspend fun getCategories(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)

    @Query("DELETE FROM records")
    suspend fun deleteAll()

    @Query("SELECT * from records ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecord(): Record

    @Query("UPDATE records SET learned = 1 WHERE id = :recordId")
    suspend fun updateAsLearned(recordId : Int)

    @Transaction
    @Query("SELECT records.* FROM records JOIN recordsFts ON (records.id = recordsFts.docid) WHERE recordsFts MATCH:tag")
    suspend fun searchByTags(tag :String): List<Record>

    @Query("SELECT * FROM records WHERE id = :recordId")
    suspend fun getSingleRecord(recordId :Int): Record

    @Update
    suspend fun updateSingleRecord(updatedRecord: Record)

    @Query("SELECT id,question FROM records WHERE category = :category")
    suspend fun getQuestionsFromCategory(category: String): List<QuestionWithId>

    @Query("Select id FROM records WHERE question =:question")
    suspend fun getIdWhereQuestion(question: String): Int
}