package com.example.cse438.cse438_assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_random_selection.*


class RandomSelectionFragment : Fragment() {
    var finalString = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finalString = arguments!!.getString("finalString")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random_selection, container, false)
    }

    override fun onStart() {
        super.onStart()

        theChosenOne.text = finalString
        //Animation technique taken from: https://stackoverflow.com/questions/9294112/how-to-make-the-textview-blinking/11991516#11991516
        val myText = theChosenOne
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 500
        animation.startOffset = 200
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = Animation.INFINITE
        myText.startAnimation(animation)
    }
}
