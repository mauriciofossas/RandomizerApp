package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {

    //Statistics we get from our roll activity that we have to send back over
    var averageOfRolls = 0
    var highestRoll = Int.MIN_VALUE
    var lowestRoll = Int.MAX_VALUE
    var numRolls = 0
    var numFaces = 0
    var averageRolls = -123456
    var allRolls : java.util.ArrayList<Int>? = arrayListOf<Int>()
    var totalSum = 0
    var cumRolls = 0

    var highest = true
    var lowest = false
    var average = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
    }

    override fun onStart() {
        super.onStart()

        //Getting our stats from the roll activity:
        var bundle :Bundle ?=intent.extras
        averageOfRolls = bundle!!.getInt("average")
        highestRoll = bundle!!.getInt("highestRoll")
        lowestRoll = bundle!!.getInt("lowestRoll")
        numRolls = bundle!!.getInt("rolls")
        numFaces = bundle!!.getInt("faces")
        allRolls = intent?.getIntegerArrayListExtra("allRolls")
        totalSum = bundle!!.getInt("totalSum")
        cumRolls = bundle!!.getInt("cumRolls")


        putHighest()

        switch_button1.setOnClickListener {
            if(highest){
                putAverage()
            } else {
                putHighest()
            }
        }

        switch_button2.setOnClickListener {
            if(lowest){
                putAverage()
            } else{
                putLowest()
            }
        }

        cancel_button.setOnClickListener {
            val goToRollActivity = Intent(this, RollActivity::class.java)
            goToRollActivity.putExtra("rolls", numRolls)
            goToRollActivity.putExtra("faces", numFaces)
            goToRollActivity.putExtra("average", averageOfRolls)
            goToRollActivity.putExtra("allRolls", allRolls)
            goToRollActivity.putExtra("lowestRoll", lowestRoll)
            goToRollActivity.putExtra("highestRoll", highestRoll)
            goToRollActivity.putExtra("haveToRoll", false)
            goToRollActivity.putExtra("totalSum", totalSum)
            goToRollActivity.putExtra("cumRolls", cumRolls)
            startActivity(goToRollActivity)
        }
    }

    fun putHighest(){
        val fragment = HighestFragment()
        var bundle = Bundle()
        bundle.putInt("highestRoll", highestRoll)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.stats_container, fragment)
        transaction.commit()
        highest = true
        lowest = false
        average = false
        switch_button1.text = "Average"
        switch_button2.text = "Lowest"
    }

    fun putAverage(){
        val fragment = AverageFragment()
        var bundle = Bundle()
        bundle.putInt("average", averageOfRolls)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.stats_container, fragment)
        transaction.commit()
        highest = false
        lowest = false
        average = true
        switch_button1.text = "Highest"
        switch_button2.text = "Lowest"
    }

    fun putLowest(){
        val fragment = LowestFragment()
        var bundle = Bundle()
        bundle.putInt("lowestRoll", lowestRoll)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.stats_container, fragment)
        transaction.commit()
        highest = false
        lowest = true
        average = false
        switch_button1.text = "Highest"
        switch_button2.text = "Average"
    }
}
