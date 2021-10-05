package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastnumeric= false
    var lastdot= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        TvInput.append((view as Button).text)
        lastnumeric=true
    }
    fun onclear(view :View){
        TvInput.text=""
        lastnumeric=false
        lastdot=false
    }
    fun ondot(view: View){
        if(lastnumeric && !lastdot) {
            TvInput.append((view as Button).text)
            lastnumeric=false
            lastdot=true
        }
    }

    fun onoperator(view:View)
    {
        if(lastnumeric && !isoperatoradded(TvInput.text.toString())){
            TvInput.append((view as Button).text)
            lastnumeric=false
            lastdot=false
        }
    }
    fun removezero(result: String):String{
        var value= result
        if(value.contains(".0"))
            value=result.substring(0,result.length-2)
        return value

    }
    private fun isoperatoradded(value : String):Boolean{
        return if (value.startsWith("-"))
        {false}
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
    fun onequal(view: View){
        if(lastnumeric){
            var tvvalue = TvInput.text.toString()
            var prefix =""
            if(tvvalue.startsWith("-")){
                prefix="-"
                tvvalue=tvvalue.substring(1)
            }
            try{
                if(tvvalue.contains("-")){
                    val splitval = tvvalue.split("-")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    TvInput.text = removezero((one.toDouble()-two.toDouble()).toString())
                 }
                else if(tvvalue.contains("+")){
                    val splitval = tvvalue.split("+")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    TvInput.text = removezero((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvvalue.contains("*")){
                    val splitval = tvvalue.split("*")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    TvInput.text = removezero((one.toDouble()*two.toDouble()).toString())
                }
                else if(tvvalue.contains("/")){
                    val splitval = tvvalue.split("/")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    TvInput.text = removezero((one.toDouble()/two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}