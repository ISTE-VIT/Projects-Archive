package com.aman.quizapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aman.quizapp2.QuestionsFragment.*
import android.widget.TextView



class ResultsFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_results, container, false)
        var res:TextView=view.findViewById<TextView>(R.id.viewScore)
        res.text = arguments?.getString("reward")
        return view
    }

}