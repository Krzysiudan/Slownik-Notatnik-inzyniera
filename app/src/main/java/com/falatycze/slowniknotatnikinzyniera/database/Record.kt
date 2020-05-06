package com.falatycze.slowniknotatnikinzyniera.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "records_table")
data class Record(

    @NotNull  val question: String,

    @NotNull val answer: String,

    @NotNull val category: String

) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    @NotNull
    var learned: Boolean = false


}