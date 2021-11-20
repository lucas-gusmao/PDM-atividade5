package com.example.atv1

import android.content.ContentValues
import android.content.Context

class CoresDAO {

    private var banco: BancoHelper

    constructor(context: Context){
        this.banco = BancoHelper((context))
    }

    fun insert(cores: Cores){
        val cv = ContentValues()
        cv.put("red", cores.red)
        cv.put("blue", cores.blue)
        cv.put("green", cores.green)
        cv.put("nome", cores.nome)
        this.banco.writableDatabase.insert("cores", null, cv)
    }

    fun count(): Int{
        val sql = "select count(id) from cores"
        val cursor = this.banco.readableDatabase.rawQuery(sql, null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    fun get():ArrayList<Cores>{
        val lista = arrayListOf<Cores>()
        val colunas = arrayOf("id", "red", "blue", "green", "nome")
        val cursor = this.banco.readableDatabase.query("cores", colunas, null, null, null, null, null)
        cursor.moveToFirst()

        for (i in 1..cursor.count){
            val id = cursor.getInt(0)
            val red = cursor.getInt(1)
            val blue = cursor.getInt(2)
            val green = cursor.getInt(3)
            val nome = cursor.getString(4)
            lista.add(Cores(id, red, blue, green, nome))
            cursor.moveToNext()
        }
        return lista
    }

    fun get(id: Int): Cores?{
        return null
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.banco.writableDatabase.delete("cores", where, pWhere)
    }

    fun update(cores: Cores){

    }
}