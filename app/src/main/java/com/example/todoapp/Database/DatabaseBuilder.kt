package com.example.todoapp.Database

import android.content.Context
import androidx.room.Room

object TodoDatabaseBuilder{
    private var INSTANCE: TodoDatabase? = null

    fun getInstance(context: Context): TodoDatabase{
        if(INSTANCE == null){
            synchronized(TodoDatabase::class){
                INSTANCE = buildTodoDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildTodoDB(context: Context): TodoDatabase?
        = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
        "todo-db"
        ).build()

}