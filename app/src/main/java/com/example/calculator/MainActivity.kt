package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClear.setOnClickListener {
            clearInput()
        }

        binding.buttonBracketLeft.setOnClickListener {
            addToInputText("(")
        }

        binding.buttonBracketRight.setOnClickListener {
            addToInputText(")")
        }

        binding.button0.setOnClickListener {
            addToInputText("0")
        }

        binding.button1.setOnClickListener {
            addToInputText("1")
        }

        binding.button2.setOnClickListener {
            addToInputText("2")
        }

        binding.button3.setOnClickListener {
            addToInputText("3")
        }

        binding.button4.setOnClickListener {
            addToInputText("4")
        }

        binding.button5.setOnClickListener {
            addToInputText("5")
        }

        binding.button6.setOnClickListener {
            addToInputText("6")
        }

        binding.button7.setOnClickListener {
            addToInputText("7")
        }

        binding.button8.setOnClickListener {
            addToInputText("8")
        }

        binding.button9.setOnClickListener {
            addToInputText("9")
        }

        binding.buttonDot.setOnClickListener {
            addToInputText(".")
        }

        binding.buttonDivision.setOnClickListener {
            addToInputText("÷") // ALT + 0247
        }

        binding.buttonMultiply.setOnClickListener {
            addToInputText("×") // ALT + 0215
        }

        binding.buttonSubtraction.setOnClickListener {
            addToInputText("-")
        }

        binding.buttonAddition.setOnClickListener {
            addToInputText("+")
        }


        binding.buttonEquals.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String) {
        binding.input.text = "${binding.input.text}$buttonValue"
    }

    private fun clearInput() {
        binding.input.text = ""
        binding.output.text = ""
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = "Error"
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}
