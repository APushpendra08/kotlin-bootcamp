package com.example.todoapp.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo (
    val date: String,
    val time: String,
    val todo: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)