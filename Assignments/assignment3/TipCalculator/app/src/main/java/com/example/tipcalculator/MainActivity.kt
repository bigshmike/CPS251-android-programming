package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTipButton.setOnClickListener {
            if (binding.billAmount.text.toString().isNotEmpty()) {
                calculateTip()
            }
            else {
                binding.tipAmounts.text = getString(R.string.tipAmountsErrorMessage)
            }
        }
    }

    private fun calculateTip() {
        // converts "Editable" -> Int to perform arithmetic operations
        val billAmount = binding.billAmount.text.toString().toInt()

        // compute tip percentages based on bill total
        val tenPercent = billAmount * 0.10
        val fifteenPercent = billAmount * 0.15
        val twentyPercent = billAmount * 0.20

        // original bill + tip amount percentages
        val tenPercentPlusBillAmount = billAmount + tenPercent
        val fifteenPercentPlusBillAmount = billAmount + fifteenPercent
        val twentyPercentPlusBillAmount = billAmount + twentyPercent

        // prints bill + tip in the tipAmounts TextView
        val billAmountPlusTip = "The tips are as follows: " +
                "\n\n10%: $tenPercentPlusBillAmount" +
                "\n15%: $fifteenPercentPlusBillAmount" +
                "\n20%: $twentyPercentPlusBillAmount"
        binding.tipAmounts.text = billAmountPlusTip
    }

}
