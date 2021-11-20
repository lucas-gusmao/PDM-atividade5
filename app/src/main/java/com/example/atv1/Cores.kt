package com.example.atv1

import java.io.Serializable

class Cores(var id: Int, var red: Int, var blue: Int, var green: Int, var nome: String): Serializable{
    override fun toString(): String {
        return "$red.$blue.$green"
    }
}