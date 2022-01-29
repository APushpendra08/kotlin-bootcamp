package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Database.Todo
import com.example.todoapp.databinding.TodoRowBinding

class TodoRecyclerView(context : Context ,private val todos: List<Todo>) : RecyclerView.Adapter<TodoRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curTodo = todos.get(position)
        with(holder.binding){
            with(curTodo){
                tvTodo.text = curTodo.todo
                tvDate.text = curTodo.date
                tvTime.text = curTodo.time
                
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class ViewHolder(val binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}