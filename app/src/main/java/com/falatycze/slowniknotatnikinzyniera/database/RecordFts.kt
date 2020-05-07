package com.falatycze.slowniknotatnikinzyniera.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


    @Fts4(contentEntity = Record::class)
    @Entity(tableName = "recordsFts")
    data class RecordFts(
        @NotNull val question: String,

        @NotNull val answer: String
    ) {
    }