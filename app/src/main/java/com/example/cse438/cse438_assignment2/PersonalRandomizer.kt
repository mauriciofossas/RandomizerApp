package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_personal_randomizer.*
import kotlin.math.roundToInt

class PersonalRandomizer : AppCompatActivity() {
    var numberItems : Int? = null
    var thingsToRandomize : java.util.ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_randomizer)
    }

    override fun onStart() {
        super.onStart()

        var bundle :Bundle ?=intent.extras
        numberItems = bundle?.getInt("numItems")
        thingsToRandomize = bundle?.getStringArrayList("thingsList")

        if(numberItems != null){
            if(thingsToRandomize != null){
                instruction_text.text = "Your chosen item is: "
                val fragment = RandomSelectionFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.personal_container, fragment)
                var bundle = Bundle()
                bundle!!.putString("finalString", findItem())
                fragment.arguments = bundle
                transaction.commit()
                personal_cancel.text = "Home"
            } else{
                instruction_text.text = "Now, enter the items to be selected from: "
                val fragment = ItemAdder()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.personal_container, fragment)
                var bundle = Bundle()
                //Weird hack to get around the fact that numberItems can be null and putInt only takes Int and not Int?
                bundle!!.putInt("numItems", numberItems.toString().toInt())
                fragment.arguments = bundle
                transaction.commit()
            }
        } else{
            val fragment = NumberOfItems()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.personal_container, fragment)
            transaction.commit()
        }

        personal_cancel.setOnClickListener {
            val goToSelection =  Intent(this, SelectionActivity::class.java)
            startActivity(goToSelection)
        }
    }

    fun findItem() : String{
        val randomIndex = (numberItems.toString().toInt() - 1) * Math.random()
        //ceiling and floor used to give greater probability to the item at index 0 to be chosen
        if(Math.random() < 0.5){
            val ceilingOfIndex = Math.ceil(randomIndex).roundToInt()
            return thingsToRandomize!!.get(ceilingOfIndex)
        } else{
            val floorOfIndex = Math.floor(randomIndex).roundToInt()
            return thingsToRandomize!!.get(floorOfIndex)
        }

    }
}
