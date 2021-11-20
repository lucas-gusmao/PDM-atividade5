package com.example.atv1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var colors: LinearLayout
    private lateinit var btnnovacor: Button
    private lateinit var lvCores: ListView
    private lateinit var lista: ArrayList<Cores>
    private lateinit var dao: CoresDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = CoresDAO(this)
        this.btnnovacor = findViewById(R.id.btnnovacor)
        this.colors = findViewById(R.id.llcores)
        this.lvCores = findViewById(R.id.lvcores)
        this.lvCores.onItemLongClickListener = OnLongClick()
        this.atualiza()

        val novaCorResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val cores = it.data?.getSerializableExtra("CORES") as Cores
                this.dao.insert(cores)
                this.atualiza()
                Log.i("APP_BANCOOO", this.dao.count().toString())
            }else{
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnnovacor.setOnClickListener {
            val intent  = Intent(this, NovaCorActivity2::class.java)
            novaCorResult.launch(intent)
        }
    }
    inner class OnLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            val cor = this@MainActivity.lista.get(position)
            this@MainActivity.dao.delete(cor.id)
            this@MainActivity.atualiza()
            return true
        }
    }
    fun atualiza(){
        this.lista = this.dao.get()
        this.lvCores.adapter = CoresAdapter(this, lista)
        (this.lvCores.adapter as BaseAdapter).notifyDataSetChanged()
    }
}