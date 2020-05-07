package com.falatycze.slowniknotatnikinzyniera.database

import androidx.lifecycle.LiveData
import androidx.room.*

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
    @Query("SELECT * FROM records JOIN recordsFts ON (records.id = recordsFts.docid) WHERE recordsFts MATCH :tag")
    suspend fun searchByTags(tag :String): List<Record>
}