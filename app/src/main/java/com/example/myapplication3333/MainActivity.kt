package com.example.myapplication3333

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.text.TextWatcher



@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {
    private lateinit var redSwitch: Switch
    private lateinit var greenSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var blueSwitch: Switch
    private lateinit var redSlider: SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    private lateinit var redValue: EditText
    private lateinit var greenValue: EditText
    private lateinit var blueValue: EditText
    private lateinit var colorPreview: View
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redSwitch = findViewById(R.id.redSwitch)
        greenSwitch = findViewById(R.id.greenSwitch)
        blueSwitch = findViewById(R.id.blueSwitch)
        redSlider = findViewById(R.id.redSlider)
        greenSlider = findViewById(R.id.greenSlider)
        blueSlider = findViewById(R.id.blueSlider)
        redValue = findViewById(R.id.redValue)
        greenValue = findViewById(R.id.greenValue)
        blueValue = findViewById(R.id.blueValue)
        colorPreview = findViewById(R.id.colorPreview)
        resetButton = findViewById(R.id.resetButton)

        // Initialize sliders and values
        redSlider.progress = 0
        greenSlider.progress = 0
        blueSlider.progress = 0
        redValue.setText("0.0")
        greenValue.setText("0.0")
        blueValue.setText("0.0")

        // Set up switch listeners
        redSwitch.setOnCheckedChangeListener { _, isChecked ->
            redSlider.isEnabled = isChecked
            redValue.isEnabled = isChecked
            if (!isChecked) {
                redSlider.progress = 0
                redValue.setText("0.0")
            }
            updateColorPreview()
        }

        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            greenSlider.isEnabled = isChecked
            greenValue.isEnabled = isChecked
            if (!isChecked) {
                greenSlider.progress = 0
                greenValue.setText("0.0")
            }
            updateColorPreview()
        }

        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            blueSlider.isEnabled = isChecked
            blueValue.isEnabled = isChecked
            if (!isChecked) {
                blueSlider.progress = 0
                blueValue.setText("0.0")
            }
            updateColorPreview()
        }

        // Set up slider listeners
        redSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                redValue.setText("%.2f".format(progress / 100.0))
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        greenSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                greenValue.setText("%.2f".format(progress / 100.0))
                updateColorPreview()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        blueSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blueValue.setText("%.2f".format(progress / 100.0))
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Set up value listeners
        redValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val value = s.toString().toFloatOrNull()
                if (value != null && value >= 0 && value <= 1) {
                    redSlider.progress = (value * 100).toInt()
                    updateColorPreview()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        greenValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val value = s.toString().toFloatOrNull()
                if (value != null && value >= 0 && value <= 1) {
                    greenSlider.progress = (value * 100).toInt()
                    updateColorPreview()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        blueValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val value = s.toString().toFloatOrNull()
                if (value != null && value >= 0 && value <= 1) {
                    blueSlider.progress = (value * 100).toInt()
                    updateColorPreview()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Set up reset button listener
        resetButton.setOnClickListener {
            redSlider.progress = 0
            greenSlider.progress = 0
            blueSlider.progress = 0
            redValue.setText("0.0")
            greenValue.setText("0.0")
            blueValue.setText("0.0")
            redSwitch.isChecked = false
            greenSwitch.isChecked = false
            blueSwitch.isChecked = false
            updateColorPreview()
        }
    }

    private fun updateColorPreview() {
        val redIntensity = if (redSwitch.isChecked) redValue.text.toString().toFloat() else 0f
        val greenIntensity = if (greenSwitch.isChecked) greenValue.text.toString().toFloat() else 0f
        val blueIntensity = if (blueSwitch.isChecked) blueValue.text.toString().toFloat() else 0f
        colorPreview.setBackgroundColor(Color.rgb(
            (redIntensity * 255).toInt(),
            (greenIntensity * 255).toInt(),
            (blueIntensity * 255).toInt()
        ))
    }
}

