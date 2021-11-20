package com.example.atv1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class NovaCorActivity2 : AppCompatActivity() {

    private lateinit var redBar: SeekBar
    private lateinit var greenBar: SeekBar
    private lateinit var blueBar: SeekBar
    private lateinit var colors: LinearLayout
    private lateinit var tvColor: TextView
    private lateinit var etCorNome: EditText
    private lateinit var btnsalvar: Button
    private lateinit var btncancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_cor2)

        this.etCorNome = findViewById(R.id.edNomeCor)

        this.btnsalvar = findViewById(R.id.btnsalvar)
        this.btncancelar = findViewById(R.id.btncancelar)

        this.colors = findViewById(R.id.colors)
        this.tvColor = findViewById(R.id.tvColor)

        this.redBar = findViewById(R.id.skred)
        this.redBar.setOnSeekBarChangeListener(OnChange())

        this.blueBar = findViewById(R.id.skblue)
        this.blueBar.setOnSeekBarChangeListener(OnChange())

        this.greenBar = findViewById(R.id.skgreen)
        this.greenBar.setOnSeekBarChangeListener(OnChange())

        this.btncancelar.setOnClickListener { finish() }
        this.btnsalvar.setOnClickListener { salvar() }
    }

    private fun salvar(){
        var red = this.redBar.progress
        var blue = this.blueBar.progress
        var green = this.greenBar.progress
        var nome = this.etCorNome.text.toString()
        var cores = Cores(-1, red, blue, green, nome)
        val intent = Intent().apply {
            putExtra("CORES", cores)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
    inner class OnChange: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            backgroundColorUpdate()
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    fun backgroundColorUpdate(){
        var red = this.redBar.progress
        var green = this.greenBar.progress
        var blue = this.blueBar.progress
        var color = Color.rgb(red, green, blue)
        this.colors.setBackgroundColor(color)
        this.tvColor.text = (red.toString() + green.toString() + blue.toString())

    }

}