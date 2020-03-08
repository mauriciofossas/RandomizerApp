package com.example.cse438.cse438_assignment2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_number_of_items.*

class NumberOfItems : Fragment() {
    var numberItems : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number_of_items, container, false)
    }

    override fun onStart() {
        super.onStart()

        submitNumber.setOnClickListener {
            if(is_numeric(itemText.text.toString())){
                val temp = itemText.text.toString().toInt()
                if(temp in 1..15){
                    val intent = Intent (activity, PersonalRandomizer::class.java)
                    intent.putExtra("numItems", temp)
                    activity!!.startActivity(intent)

                } else{
                    (Toast.makeText(activity, "Only 1 to 15 items may be added.", Toast.LENGTH_SHORT)).show()
                }
            } else{
                (Toast.makeText(activity, "Please enter numeric input.", Toast.LENGTH_SHORT)).show()
            }
        }
    }

    //Function to see if a string is numeric
    private fun is_numeric(input : String): Boolean {
        var numeric = true
        try {
            Integer.parseInt(input)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        return numeric
    }

    private fun is_positive(input : String): Boolean{
        if(!is_numeric(input)){
            return false
        } else {
            return Integer.parseInt(input) > 0
        }
    }
}
