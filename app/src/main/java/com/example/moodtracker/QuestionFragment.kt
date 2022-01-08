package com.example.moodtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var yesBtn : Button
    private lateinit var nooBtn : Button
    private var questionNo = 0
    private val answersMap : MutableMap<Int, Int> by lazy {
        mutableMapOf()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_question, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_horizontal)
        imageView = view.findViewById(R.id.iv_question)
        textView = view.findViewById(R.id.tv_question)
        yesBtn = view.findViewById(R.id.btn_yes)
        yesBtn.setOnClickListener {
//            progressBar.progress = progressBar.progress + 10
            answersMap[questionNo] = 0
            questionNo++
            updateQuestion(questionNo)
        }
        nooBtn = view.findViewById(R.id.btn_no)
        nooBtn.setOnClickListener {
//            progressBar.progress -= 10
            answersMap[questionNo] = 1
            questionNo++
            updateQuestion(questionNo)
        }
        updateQuestion(questionNo)
    }

    private fun updateQuestion(questionNo: Int) {
        when(questionNo){
            0 -> changeMessage(R.string.question_one, R.drawable.anxious)
            1 -> changeMessage(R.string.question_two, R.drawable.aleeping)
            2 -> changeMessage(R.string.question_three, R.drawable.appetite)
            3 -> changeMessage(R.string.question_four, R.drawable.loss_interest)
            4 -> changeMessage(R.string.question_five, R.drawable.restless)
            5 -> moveToResult()
        }
    }

    private fun moveToResult() {
//        TODO("Not yet implemented")
        var countPositive = 0
        answersMap.forEach { key, value ->
            if(value == 1)
                countPositive++;
        }
        var bundle = bundleOf("positiveCount" to countPositive)
        findNavController().navigate(R.id.action_questionFragment_to_resultFragment, bundle)
    }

    private fun changeMessage(newMsgId: Int, ImageId : Int) {
        textView.setText(newMsgId)
        imageView.setImageResource(ImageId)
        progressBar.progress = questionNo * 20
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}