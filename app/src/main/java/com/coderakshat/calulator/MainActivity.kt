package com.coderakshat.calulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.coderakshat.calulator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false


    fun onDelete(view: View){

        var word = binding.tvResult.text.toString()
        var input: Int = word.length
        if (input > 0){
            binding.tvResult.text = (word.substring(0, input-1))
        }
    }

    fun onClear(view: View){
        binding.tvResult.text = ""
        lastNumeric = false
        lastDot =  false
    }

    fun onDigit(view: View){
        binding.tvResult.append((view as Button).text)
        lastNumeric = true
    }

    fun onDecimalDot(view: View){
        if (lastNumeric && !lastDot){
            binding.tvResult.append(".")
            lastNumeric = false
            lastDot =  true
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = binding.tvResult.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("+")){
                    prefix = ""
                    tvValue = tvValue.substring(1)
                }


                if (tvValue.contains("+")) {

                    val splitValue = tvValue.split("+")

                    var numOne = splitValue[0]
                    var numTwo = splitValue[1]

                    if (!prefix.isBlank()){
                        numOne = prefix + numOne
                    }

                    binding.tvResult.text = removeZeroAfterDot((numOne.toDouble() + numTwo.toDouble()).toString())

                } else if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")

                    var numOne = splitValue[0]
                    var numTwo = splitValue[1]

                    if (!prefix.isBlank()){
                        numOne = prefix + numOne
                    }

                    binding.tvResult.text = removeZeroAfterDot((numOne.toDouble() - numTwo.toDouble()).toString())

                } else if (tvValue.contains("x")) {

                    val splitValue = tvValue.split("x")

                    var numOne = splitValue[0]
                    var numTwo = splitValue[1]

                    if (!prefix.isBlank()){
                        numOne = prefix + numOne
                    }

                    binding.tvResult.text = removeZeroAfterDot((numOne.toDouble() * numTwo.toDouble()).toString())

                } else if (tvValue.contains("÷")) {

                    val splitValue = tvValue.split("÷")

                    var numOne = splitValue[0]
                    var numTwo = splitValue[1]

                    if (!prefix.isBlank()){
                        numOne = prefix + numOne
                    }

                    binding.tvResult.text = removeZeroAfterDot((numOne.toDouble() / numTwo.toDouble()).toString())

                } else if (tvValue.contains("%")) {

                    val splitValue = tvValue.split("%")

                    var numOne = splitValue[0]
                    var numTwo = splitValue[1]

                    if (!prefix.isBlank()){
                        numOne = prefix + numOne
                    }

                    binding.tvResult.text = removeZeroAfterDot((numOne.toDouble() % numTwo.toDouble()).toString())

                }

            } catch (e: ArithmeticException){
                e.printStackTrace()
            }


        }
    }

    private fun removeZeroAfterDot (result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
            return value
    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(binding.tvResult.text.toString())){
            binding.tvResult.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }


    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("+"))  {
            false
        }else {
            value.contains("-") || value.contains("*") || value.contains("/") || value.contains("%")
        }
    }




}