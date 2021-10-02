package com.aman.quizapp2

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_questions.*
import android.view.animation.AnimationUtils
import androidx.compose.ui.graphics.Color.Companion.Green


class QuestionsFragment : Fragment() {
    lateinit var database: DatabaseReference
    var index = 0
    var reward = 0

    val rewardBool = arrayOf(false,false,false,false,false,false,false,false,false,false,false)

    lateinit var desc: TextView
    lateinit var op1: Button
    lateinit var op2: Button
    lateinit var op3: Button
    lateinit var op4: Button

    lateinit var d: String
    lateinit var o1: String
    lateinit var o2: String
    lateinit var o3: String
    lateinit var o4: String
    lateinit var ans: String
    lateinit var nextButton: Button
    lateinit var prevButton: Button
    lateinit var cnt: TextView
    var usrAns:String="abc"
//    var cnt

    companion object {
        lateinit var value:CountDownTimer
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        var view = inflater.inflate(R.layout.fragment_questions, container, false)




         value = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                cnt.setText(""+millisUntilFinished / 1000)
            }

            override fun onFinish() {
                next(view)
            }
        }

//        value.onFinish(view)

//        database.child("question1").get().addOnSuccessListener {
//            Log.d("firebase", "Got value ${it.value}")
//        }.addOnFailureListener{
//            Log.d("firebase", "Error getting data", it)
//        }

        database = Firebase.database.reference


        render(view)

        if (index == 0) {
            prevButton.setVisibility(View.INVISIBLE)
            op1.setVisibility(View.INVISIBLE)
            op2.setVisibility(View.INVISIBLE)
            op3.setVisibility(View.INVISIBLE)
            op4.setVisibility(View.INVISIBLE)
        }


        nextButton.setOnClickListener()
        {

            value.cancel()
            value.start()
            next(view)
        }
        prevButton.setOnClickListener()
        {
            prev(view)
        }

        return view
    }

    private fun next(view: View) {
        val blue: Int = resources.getColor(R.color.purple_500)

        op1.setBackgroundColor(blue)
        op2.setBackgroundColor(blue)
        op3.setBackgroundColor(blue)
        op4.setBackgroundColor(blue)

        index++
        if (index == 11) {
            val trueCount = rewardBool.count { it }
            Log.e(TAG, "index 11:$trueCount ", )


            val bundle = bundleOf("reward" to trueCount.toString())
            Navigation.findNavController(view)
                .navigate(R.id.action_questionsFragment_to_resultsFragment, bundle)
        }

        var q = "question" + index

        database.child(q).get().addOnSuccessListener {
            Log.d("firebase", "Got value ${it.value}")
            if (it.exists()) {

                prevButton.setVisibility(View.VISIBLE)
                op1.setVisibility(View.VISIBLE)
                op2.setVisibility(View.VISIBLE)
                op3.setVisibility(View.VISIBLE)
                op4.setVisibility(View.VISIBLE)

                    d = it.child("description").value as String
                    o1 = it.child("option1").value as String
                    o2 = it.child("option2").value as String
                    o3 = it.child("option3").value as String
                    o4 = it.child("option4").value as String
                    ans = it.child("answer").value as String

                val fadein = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                desc.startAnimation(fadein)
                op1.startAnimation(fadein)
                op2.startAnimation(fadein)
                op3.startAnimation(fadein)
                op4.startAnimation(fadein)
                cnt.startAnimation(fadein)
                    desc.setText(d)
                    op1.setText(o1)
                    op2.setText(o2)
                    op3.setText(o3)
                    op4.setText(o4)

                    buttonClick(view)
//                    chk()
            }
            }
    }

    private fun prev(view: View) {
        index--
        var q = "question" + index
        database.child(q).get().addOnSuccessListener {
            Log.d("firebase", "Got value ${it.value}")
            if (it.exists()) {
                render(view)

                 d = it.child("description").value as String
                 o1 = it.child("option1").value as String
                 o2 = it.child("option2").value as String
                 o3 = it.child("option3").value as String
                 o4 = it.child("option4").value as String
                 ans = it.child("answer").value as String


                desc.setText(d)
                op1.setText(o1)
                op2.setText(o2)
                op3.setText(o3)
                op4.setText(o4)


                buttonClick(view)
            }
        }
    }

         fun render(view: View) {

            op1 = view.findViewById(R.id.option1)
            op2 = view.findViewById(R.id.option2)
            op3 = view.findViewById(R.id.option3)
            op4 = view.findViewById(R.id.option4)
            desc = view.findViewById(R.id.description)
            prevButton = view.findViewById(R.id.prvBtn)
            nextButton = view.findViewById(R.id.nxtBtn)
            cnt = view.findViewById(R.id.countdownCount)
        }

        private fun chk()
        {
            if(usrAns==ans)
            {
                rewardBool[index]=true
                Log.e(TAG, "$usrAns: true ", )
            }
            else
            {
                Log.e(TAG, "\n $usrAns: false ", )
            }
        }

         fun buttonClick(view: View) {
            op1.setOnClickListener()
            {
                usrAns=o1
                chk()
                val blue: Int = resources.getColor(R.color.purple_500)

                val animationFadeIn = AnimationUtils.loadAnimation(context, R.anim._rotate)
                op1.startAnimation(animationFadeIn)
                op1.setBackgroundColor(Color.GREEN)
                op2.setBackgroundColor(blue)
                op3.setBackgroundColor(blue)
                op4.setBackgroundColor(blue)


            }
            op2.setOnClickListener()
            {
                usrAns=o2
                chk()
                val blue: Int = resources.getColor(R.color.purple_500)

                val animationFadeIn2 = AnimationUtils.loadAnimation(context, R.anim.bounce)
                op2.startAnimation(animationFadeIn2)
                op1.setBackgroundColor(blue)
                op2.setBackgroundColor(Color.GREEN)
                op3.setBackgroundColor(blue)
                op4.setBackgroundColor(blue)

//                if (ans == o2) {
//                    reward++
//                }
            }
            op3.setOnClickListener()
            {
                usrAns=o3
                chk()
                val animationFadeIn = AnimationUtils.loadAnimation(context, R.anim._rotate)
                animationView.startAnimation(animationFadeIn)
                val blue: Int = resources.getColor(R.color.purple_500)

                op1.setBackgroundColor(blue)
                op2.setBackgroundColor(blue)
                op3.setBackgroundColor(Color.GREEN)
                op4.setBackgroundColor(blue)

//                if (ans == o3) {
//                    reward++
//                }
            }
             op4.setOnClickListener()
             {
                 usrAns=o4
                 chk()
                 val blue: Int = resources.getColor(R.color.purple_500)

                 op1.setBackgroundColor(blue)
                op2.setBackgroundColor(blue)
                op3.setBackgroundColor(blue)
                op4.setBackgroundColor(Color.GREEN)
//                 if (ans == o3) {
//                     reward++
//                 }
             }
        }

    }

