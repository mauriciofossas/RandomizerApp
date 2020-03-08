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
import kotlinx.android.synthetic.main.fragment_item_adder.*
import kotlinx.android.synthetic.main.fragment_number_of_items.*

class ItemAdder : Fragment() {
    var numItems = 0
    var thingsToRandomize : java.util.ArrayList<String>? = ArrayList<String>()
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        numItems = arguments!!.getInt("numItems", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_adder, container, false)
    }

    override fun onStart() {
        super.onStart()

        addItem.setOnClickListener {
            if(count < numItems - 1){
                if(itemToAdd.text.toString() != ""){
                    thingsToRandomize?.add(itemToAdd.text.toString())
                    ++count
                    (Toast.makeText(activity, "Item added successfully", Toast.LENGTH_SHORT)).show()
                    itemToAdd.text.clear()
                    itemToAdd.hint = "Add item #" + (count + 1).toString()
                } else{
                    (Toast.makeText(activity, "Please enter an item before continuing.", Toast.LENGTH_SHORT)).show()
                }
            } else{
                thingsToRandomize?.add(itemToAdd.text.toString())
                val intent = Intent (activity, PersonalRandomizer::class.java)
                intent.putExtra("numItems", numItems)
                intent.putExtra("thingsList", thingsToRandomize)
                activity!!.startActivity(intent)
            }
        }
    }
}
