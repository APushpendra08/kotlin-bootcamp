package com.example.todoapp.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Database.DatabaseHelperImplementation
import com.example.todoapp.Database.Todo
import com.example.todoapp.Database.TodoDatabaseBuilder
import com.example.todoapp.R
import com.example.todoapp.TodoRecyclerView
import com.example.todoapp.databinding.FragmentTodoListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class TodoListFragment : Fragment() {

    lateinit var binding : FragmentTodoListBinding
    var todos = mutableListOf<Todo>()
    lateinit var dbHelper : DatabaseHelperImplementation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddTodo.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }

        with(binding.rvTodoListRecyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = TodoRecyclerView(context, todos)
        }

        dbHelper = DatabaseHelperImplementation(TodoDatabaseBuilder.getInstance(view.context))
        CoroutineScope(IO).launch {
            todos = dbHelper.getTodos() as MutableList<Todo>
            CoroutineScope(Main).launch {
                Toast.makeText(context, todos.toString(), Toast.LENGTH_LONG).show()
                binding.rvTodoListRecyclerView.adapter!!.notifyDataSetChanged()
                binding.rvTodoListRecyclerView.adapter =
                    context?.let { TodoRecyclerView(it, todos) };
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodoListFragment().apply {
                }
        }
}