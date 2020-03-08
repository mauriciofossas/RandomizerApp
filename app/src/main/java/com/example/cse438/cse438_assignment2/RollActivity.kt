package com.example.cse438.cse438_assignment2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_roll.*
import kotlin.math.roundToInt

class RollActivity : AppCompatActivity() {

    var allRolls : java.util.ArrayList<Int>? = ArrayList<Int>()
    var averageOfRolls = 0
    var numRolls = 0
    var numFaces = 0
    var highestRoll = Int.MIN_VALUE
    var lowestRoll = Int.MAX_VALUE
    //To be able to keep track of the total sum of all rolls to be able to average
    //rolls for our stats
    var totalSumOfRolls = 0
    var cumRolls = 0
    //To see if we are coming from the main activity and have to roll or if we are coming
    //from "cancel" in our stats activity and don't have to roll
    var haveToRoll = false
    //If the user chooses to reroll then we want a sum of rolls only for the roll,
    //but still want to keep the total sum of rolls for statistical purposes.
    var individualSumOfRolls = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll)
    }

    override fun onStart() {
        super.onStart()

        //Getting the extras from the main activity
        var bundle :Bundle ?=intent.extras
        averageOfRolls = bundle!!.getInt("average")
        highestRoll = bundle!!.getInt("highestRoll")
        lowestRoll = bundle!!.getInt("lowestRoll")
        numRolls = bundle!!.getInt("rolls")
        numFaces = bundle!!.getInt("faces")
        allRolls = intent?.getIntegerArrayListExtra("allRolls")
        haveToRoll=bundle!!.getBoolean("haveToRoll", false)
        totalSumOfRolls = bundle!!.getInt("totalSum")
        cumRolls = bundle!!.getInt("cumRolls")

        if(haveToRoll){
            setDisplay()
        }


        reroll_button.setOnClickListener {
            setDisplay()
        }

        see_results_button.setOnClickListener {
            val goToStatsActivity = Intent(this, StatsActivity::class.java)
            sendData(goToStatsActivity)
        }

        home_button.setOnClickListener {
            val goToMainActivity =  Intent(this, MainActivity::class.java)
            sendData(goToMainActivity)
        }

    }

    fun rollDice(numFaces : Int, numRolls : Int){
        //Reset the sum of rolls for this "roll" whenever the user rerolls
        cumRolls += numRolls
        individualSumOfRolls = 0
        for (x in 0 until numRolls){
            val randomNumber = Math.random() * numFaces
            val ceilingOfRoll = Math.ceil(randomNumber).roundToInt()
            //Sets the lowest and highest rolls
            if(ceilingOfRoll < lowestRoll){
                lowestRoll = ceilingOfRoll
            }
            if(ceilingOfRoll > highestRoll){
                highestRoll = ceilingOfRoll
            }
            totalSumOfRolls += ceilingOfRoll
            individualSumOfRolls += ceilingOfRoll
            allRolls?.add(ceilingOfRoll)
        }
        //Sets the average of all rolls
        averageOfRolls = totalSumOfRolls/cumRolls
    }

    fun setDisplay(){
        rollDice(numFaces, numRolls)
        valueOfRoll.text = individualSumOfRolls.toString()

        val lowThreshold = numFaces*numRolls/3
        val medThreshold = (numFaces*numRolls/3)*2
        if(individualSumOfRolls <= lowThreshold){
            valueOfRoll.setTextColor(Color.RED)
        } else{
            if(individualSumOfRolls <= medThreshold){
                valueOfRoll.setTextColor(Color.BLACK)
            } else{
                valueOfRoll.setTextColor(Color.GREEN)
            }
        }
    }

    //Simple function to send data to
    fun sendData(act : Intent){
        //Put the information to our intent
        act.putExtra("average", averageOfRolls)
        act.putExtra("lowestRoll", lowestRoll)
        act.putExtra("highestRoll", highestRoll)
        act.putExtra("rolls", numRolls)
        act.putExtra("faces", numFaces)
        act.putExtra("allRolls", allRolls)
        act.putExtra("totalSum", totalSumOfRolls)
        act.putExtra("cumRolls", cumRolls)
        startActivity(act)
    }
}
