package com.ttn.animationdemo

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dotAnimation()
    }



    /**
     * dot loading animation
     */
    private fun dotAnimation() {
        val spannable: Spannable = SpannableString.valueOf(getString(R.string.subheading))

        ValueAnimator.ofInt(0, 4).apply {
            repeatCount = 10
            duration = 1000
            addUpdateListener { valueAnimator ->
                val dotCount = valueAnimator.animatedValue as Int
                if (dotCount < 4) {
                    spannable.setSpan(
                        ForegroundColorSpan(Color.RED),
                        23 + dotCount,
                        26,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    subheading.text = spannable.subSequence(0, 23 + dotCount)
                    subheading.invalidate()
                }
            }
        }.start()
    }
}
