package com.example.todoapp.Screens

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.Database.DatabaseHelper
import com.example.todoapp.Database.DatabaseHelperImplementation
import com.example.todoapp.Database.Todo
import com.example.todoapp.Database.TodoDatabaseBuilder
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTodoBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

class AddTodoFragment : Fragment() {

    lateinit var binding : FragmentAddTodoBinding
    var dateStr = ""
    var timeStr = ""
    var todoStr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddTodoTrue.setOnClickListener {
            with(binding){
                if(!tvSelectDate.text.toString().contains(":") || !tvSelectTime.text.toString().contains(":") || etTodoText.text.toString().isEmpty())
                {
                    Toast.makeText(context, "All Fields are not Set. Set the Fields and Try Again", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    // DB Insert
                    todoStr = etTodoText.text.toString()
                    val dbHelper = DatabaseHelperImplementation(TodoDatabaseBuilder.getInstance(view.context))
                    CoroutineScope(IO).launch {
                        dbHelper.insert(Todo(dateStr, timeStr, todoStr))
                        var list = dbHelper.getTodos()
                        CoroutineScope(Main).launch {
                            Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_addTodoFragment_to_todoListFragment)
//                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
        binding.fabTodoCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            dateStr = year.toString()  + "/" + (monthOfYear+1).toString() + "/" + dayOfMonth.toString()
            binding.tvSelectDate.text = "Selected Date: " + dateStr

        }

        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            timeStr = hourOfDay.toString() + ":" + minute.toString()
            binding.tvSelectTime.text = "Selected Time: " + timeStr
        }

        with(binding.datePicker){
            dateStr = year.toString()  + "/" + month.toString() + "/" + dayOfMonth.toString()
            binding.tvSelectDate.text = "Selected Date: " + dateStr
        }

        with(binding.timePicker){
            timeStr = hour.toString() + ":" + minute.toString()
            binding.tvSelectTime.text = "Selected Time: " + timeStr
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTodoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}