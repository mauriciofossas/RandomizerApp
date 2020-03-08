package com.example.cse438.cse438_assignment2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_highest.*


class HighestFragment : Fragment() {

    var highestRoll = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Retrieve the highest roll from our bundle
        highestRoll = arguments!!.getInt("highestRoll", 0)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highest, container, false)
    }

    override fun onStart() {
        super.onStart()

        //Set the view for our highest roll
        highest_roll_value.text = highestRoll.toString()

    }


}