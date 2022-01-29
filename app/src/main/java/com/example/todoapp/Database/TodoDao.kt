package com.example.todoapp.Database

import androidx.room.*

@Dao
interface TodoDao {

    @Insert
    suspend fun insert(todo : Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * from todos")
    suspend fun getAllTodos(): List<Todo>
}