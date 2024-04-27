package com.ahmrh.todoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmrh.todoapp.data.database.dao.TodoDao
import com.ahmrh.todoapp.data.database.entity.TodoEntity

@Database(
    entities = [
        TodoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return Instance ?: synchronized(
                this
            ) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "todo_database"
                ).build().also { Instance = it }
            }
        }


    }
}