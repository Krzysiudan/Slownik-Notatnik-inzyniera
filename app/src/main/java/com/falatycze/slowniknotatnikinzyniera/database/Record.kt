package com.falatycze.slowniknotatnikinzyniera.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "records")
data class Record(

    @NotNull var question: String,

    @NotNull var answer: String,

    @NotNull var category: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @NotNull
    var learned: Boolean = false


}