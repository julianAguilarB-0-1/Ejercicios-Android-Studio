package com.example.appholamundo

import java.io.Serializable

class CuentaBanco : Serializable {
    var numCuenta: Int = 0
    var nombreCliente: String = ""
    var banco: String = ""
    var saldo: Float = 0.0f

    constructor() {
        this.numCuenta = 0
        this.nombreCliente = ""
        this.banco = ""
        this.saldo = 0.0f
    }

    fun obtenerSaldo(): Float {
        return saldo
    }
    fun hacerDeposito(cantidad: Float): Float {
        saldo += cantidad
        return saldo
    }
    fun retirarDinero(cantidad: Float): Boolean {
        if (cantidad <= saldo) {
            saldo -= cantidad
            return true
        }
        return false
    }
}