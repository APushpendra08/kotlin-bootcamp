package com.example.todoapp.Database

interface DatabaseHelper {
    suspend fun getTodos() : List<Todo>
    suspend fun insert(todo: Todo)
}

class DatabaseHelperImplementation(private val todoDatabase: TodoDatabase) : DatabaseHelper {
    override suspend fun getTodos(): List<Todo> {
        return todoDatabase.todoDao().getAllTodos()
    }

    override suspend fun insert(todo: Todo) {
        return todoDatabase.todoDao().insert(todo)
    }
}