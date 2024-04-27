package com.ahmrh.todoapp

import android.app.Application
import android.content.Context
import com.ahmrh.todoapp.data.TodoRepository
import com.ahmrh.todoapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@HiltAndroidApp
class TodoApplication: Application()

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideTodoRepository(
        appDatabase: AppDatabase
    ): TodoRepository = TodoRepository(
        appDatabase.todoDao()
    )

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getDatabase(context)

}