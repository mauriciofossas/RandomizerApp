package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
    }

    override fun onStart() {
        super.onStart()

        roll_dice.setOnClickListener {
            val goToMainActivity =  Intent(this, MainActivity::class.java)
            startActivity(goToMainActivity)
        }

        other_randomizer.setOnClickListener {
            val goToPersonalActivity =  Intent(this, PersonalRandomizer::class.java)
            startActivity(goToPersonalActivity)
        }
    }
}
