package com.falatycze.slowniknotatnikinzyniera.database

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Record::class,RecordFts::class], version = 8,exportSchema = true)
public abstract class RecordRoomDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    private class RecordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.recordDao())
                }
            }
        }

        suspend fun populateDatabase(recordDao: RecordDao) {
            // Delete all content here.

            // Add sample words.
            // TODO: Add your own words!
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RecordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RecordRoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordRoomDatabase  ::class.java,
                    "word_database"
                )
                    .addCallback(RecordDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .createFromAsset("databases/word_database.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

