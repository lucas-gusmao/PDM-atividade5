package com.example.atv1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CoresAdapter (var contexto: Context, var lista: ArrayList<Cores>): BaseAdapter() {
    override fun getCount(): Int {
        return this.lista.count()
    }

    override fun getItem(position: Int): Any {
        return this.lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (convertView == null){
            LayoutInflater.from(this.contexto).inflate(R.layout.cores_layout, parent, false)
        }else{
            convertView
        }

        val cor = this.lista.get(position)

        val ivCor = view.findViewById<ImageView>(R.id.ivCores)
        val tvCorNome = view.findViewById<TextView>(R.id.tvCorNome)
        val tvCorCodigo = view.findViewById<TextView>(R.id.tvCorCodigo)

        tvCorNome.text = cor.nome
        tvCorCodigo.text = "${cor.red}.${cor.blue}.${cor.green}"
        ivCor.setColorFilter(Color.rgb(cor.red, cor.green, cor.blue))

        view.minimumHeight = 100

        return view


    }
}