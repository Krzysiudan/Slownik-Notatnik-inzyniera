package com.falatycze.slowniknotatnikinzyniera.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao{

    @Query("SELECT * from records_table ORDER BY question ASC")
    fun getAlphabetizedRecords(): LiveData<List<Record>>

    @Query("SELECT question  from records_table ORDER BY question ASC")
    fun getAlphabetizedQuestions(): LiveData<List<String>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)

    @Query("DELETE FROM records_table")
    suspend fun deleteAll()

    @Query("SELECT * from records_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecord(): Record

}