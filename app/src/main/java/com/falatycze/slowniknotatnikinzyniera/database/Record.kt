package com.falatycze.slowniknotatnikinzyniera.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "records_table")
data class Record(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @NotNull @ColumnInfo(name = "Question") val question: String,

    @NotNull val answer: String,

    @NotNull val category: String

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Record

        if (id != other.id) return false
        if (question != other.question) return false
        if (answer != other.answer) return false
        if (category != other.category) return false


        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + question.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + category.hashCode()

        return result
    }
}