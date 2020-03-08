package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.parseInt
import android.content.Intent

class MainActivity : AppCompatActivity() {

    var numRolls = 0
    var numFaces = 0
    //Values that you get back from the RollActivity
    var averageRolls = -123456
    var allRolls : java.util.ArrayList<Int>? = arrayListOf<Int>()
    var highestRoll = Int.MIN_VALUE
    var lowestRoll = Int.MAX_VALUE
    var totalSum = 0
    var cumRolls = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        //Get stored values from the roll activity when the user chooses to "Go home"
        averageRolls = intent!!.getIntExtra("average", -654321)
        highestRoll = intent!!.getIntExtra("highestRoll", Int.MIN_VALUE)
        lowestRoll = intent!!.getIntExtra("lowestRoll", Int.MAX_VALUE)
        totalSum = intent!!.getIntExtra("totalSum", 0)
        cumRolls = intent!!.getIntExtra("cumRolls", 0)
        allRolls = intent?.getIntegerArrayListExtra("allRolls")

        roll_button.setOnClickListener {
            var numDice = dice_number.text
            var maxValue = max_dice_value.text

            if(is_positive(numDice.toString()) && is_positive(maxValue.toString())){
                //Open new activity
                numRolls = parseInt(numDice.toString())
                numFaces = parseInt(maxValue.toString())

                val goToRollActivity = Intent(this, RollActivity::class.java)
                goToRollActivity.putExtra("rolls", numRolls)
                goToRollActivity.putExtra("faces", numFaces)
                goToRollActivity.putExtra("average", averageRolls)
                goToRollActivity.putExtra("allRolls", allRolls)
                goToRollActivity.putExtra("lowestRoll", lowestRoll)
                goToRollActivity.putExtra("highestRoll", highestRoll)
                goToRollActivity.putExtra("haveToRoll", true)
                goToRollActivity.putExtra("totalSum", totalSum)
                goToRollActivity.putExtra("cumRolls", cumRolls)
                startActivity(goToRollActivity)
            } else{
                val myToast = Toast.makeText(this, "Please enter reasonable values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }

        clear_button.setOnClickListener {
            numRolls = 0
            numFaces = 0
            averageRolls = 0
            allRolls = arrayListOf<Int>()
            highestRoll = Int.MIN_VALUE
            lowestRoll = Int.MAX_VALUE
            totalSum = 0
            cumRolls = 0
            (Toast.makeText(this, "Data cleared successfully", Toast.LENGTH_SHORT)).show()
        }

        return_button.setOnClickListener {
            startActivity(Intent(this, SelectionActivity::class.java))
        }
    }

    //Function to see if a string is numeric
    private fun is_numeric(input : String): Boolean {
        var numeric = true
        try {
            parseInt(input)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        return numeric
    }

    private fun is_positive(input : String): Boolean{
        if(!is_numeric(input)){
            return false
        } else {
            return parseInt(input) > 0
        }
    }
}
