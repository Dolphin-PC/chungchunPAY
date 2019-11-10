package com.example.chungchunpay

import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_full_main.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class full_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_main)
        delay(2000) {
            val transition = Transitioner(starting_view, ending_view)
            transition.duration = 500
            transition.interpolator = AccelerateDecelerateInterpolator()
            button.setOnClickListener {
                transition.animateTo(percent = 0f, duration = 500, interpolator = BounceInterpolator())
                transition.onProgressChanged {
                    seekBar.progress = (it * 100).toInt()
                }

            }

            var oldX = 0f
            val size = Point()
            windowManager.defaultDisplay.getSize(size)
            var screenSize = size.x
            screen.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        oldX = event.x
                        true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        transition.setProgress((event.x - oldX)/screenSize)
                        true
                    }
                    else -> {
                        true
                    }
                }
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    transition.setProgress(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
    }
}

fun delay(delay: Long, func: () -> Unit) {
    Handler().postDelayed({
        try {
            func()
        } catch (e: Exception) {
        }
    }, delay)
}
