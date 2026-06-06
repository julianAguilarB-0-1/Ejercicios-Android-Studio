package com.example.appholamundo

import java.io.Serializable
import kotlin.math.abs
import kotlin.random.Random

class ReciboNomina : Serializable {

    var numRecibo:Int = 0
    var nombre:String = ""
    var horasNormales:Int = 0
    var horasExtra:Int = 0
    var puesto:Int = 0
    var porcentajeImpuesto:Float = 16.0f

    constructor(){
        numRecibo = 0
        nombre = ""
        horasNormales = 0
        horasExtra = 0
        puesto = 0
        porcentajeImpuesto = 16.0f
    }

    fun obtenerPagoHora():Float{
        var pagoBase = 200.0f

        when(puesto){
            1 -> pagoBase += pagoBase * 0.20f
            2 -> pagoBase += pagoBase * 0.50f
            3 -> pagoBase += pagoBase * 1.00f
        }

        return pagoBase
    }

    fun calcularSubtotal():Float{
        val pagoHora = obtenerPagoHora()
        return (horasNormales * pagoHora) +
                (horasExtra * pagoHora * 2)
    }

    fun calcularImpuesto():Float{
        return calcularSubtotal() * porcentajeImpuesto / 100
    }

    fun calcularTotalPagar():Float{
        return calcularSubtotal() - calcularImpuesto()
    }

    fun generaRecibo():Int{
        return abs(Random.nextInt() % 1001)
    }
}