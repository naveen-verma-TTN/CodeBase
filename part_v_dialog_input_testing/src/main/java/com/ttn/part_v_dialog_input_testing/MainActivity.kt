package com.ttn.part_v_dialog_input_testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_launch_dialog.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        MaterialDialog(this)
                .show {
                    input(
                            waitForPositiveButton = true,
                            allowEmpty = false
                    ) { _, name ->
                        setNameToTextView(name.toString())
                    }
                    title(R.string.dialog_title)
                    positiveButton(R.string.text_ok)
                }
    }

    private fun setNameToTextView(name: String) {
        text_name.text = name
    }

}
