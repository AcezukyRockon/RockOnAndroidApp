package com.example.rockonandroidapp

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Switch
import android.widget.TextView
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), View.OnTouchListener {


    private var dX : Float = 0.0f
    private var dY : Float = 0.0f
    private lateinit var size : Point


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnTouchListener(this)

    }

    // count for number of taps
    var count = 0
    var checktap = 0

    fun onTap(view: View) {
        var sw1 = findViewById<Switch>(R.id.switch1)
        sw1.setOnCheckedChangeListener { buttonView, onswitch ->
            if (onswitch)
                checktap = 1
            else checktap = 0
        }
        if (checktap==1)
            count++
        var textview = findViewById(R.id.textView) as TextView
        textview.text = "You tapped $count time(s)!"
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when(motionEvent.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                dX = view.getX() - motionEvent.getRawX()
                dY = view.getY() - motionEvent.getRawY()
                view.animate()
                    .x(motionEvent.rawX + dX)
                    .y(motionEvent.rawY + dY)
                    .setDuration(0)
                    .start()
            }
            MotionEvent.ACTION_MOVE -> {
                view.animate()
                    .x(motionEvent.rawX + dX)
                    .y(motionEvent.rawY + dY)
                    .setDuration(0)
                    .start()
            }
            /*MotionEvent.ACTION_UP -> {
                view.animate()
                    .x(constraint_guideline_marginStart.getLeft().toFloat())
                    .y(constraint_guideline_marginTop.getTop().toFloat())
                    .setDuration(250)
                    .start()
            }*/
            else -> {
                return false
            }
        }
        return true
    }
}

