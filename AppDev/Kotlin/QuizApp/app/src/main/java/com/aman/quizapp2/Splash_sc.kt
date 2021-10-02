package com.aman.quizapp2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import androidx.navigation.Navigation

class Splash_sc : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_splash_sc, container, false)
        Handler().postDelayed(
            {

                Navigation.findNavController(view)
                    .navigate(R.id.action_splash_sc_to_questionsFragment)

            }, 5000)
        return view

    }


}