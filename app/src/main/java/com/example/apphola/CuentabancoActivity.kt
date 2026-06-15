package com.example.apphola

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appholamundo.CuentaBanco

class CuentabancoActivity : AppCompatActivity() {

    private lateinit var txtUsuario: TextView

    private lateinit var txtNumCuenta: EditText
    private lateinit var txtNombreCliente: EditText
    private lateinit var txtBanco: EditText
    private lateinit var txtSaldo: EditText

    private lateinit var rdbDeposito: RadioButton
    private lateinit var rdbRetiro: RadioButton
    private lateinit var rdbConsulta: RadioButton

    private lateinit var txtNSaldo: TextView
    private lateinit var txtCBCantidad: EditText

    private lateinit var btnRegistrarCuenta: Button
    private lateinit var btnMovimiento: Button
    private lateinit var btnCerrar: Button

    private var cuenta = CuentaBanco()
    private var cuentaRegistrada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuentabanco)

        iniciarComponentes()
        eventosClick()
    }

    fun iniciarComponentes() {

        txtUsuario = findViewById(R.id.txtUsuario)

        txtNumCuenta = findViewById(R.id.txtNumCuenta)
        txtNombreCliente = findViewById(R.id.txtNombreCliente)
        txtBanco = findViewById(R.id.txtBanco)
        txtSaldo = findViewById(R.id.txtSaldo)

        rdbDeposito = findViewById(R.id.rdbDeposito)
        rdbRetiro = findViewById(R.id.rdbRetiro)
        rdbConsulta = findViewById(R.id.rdbConsulta)

        txtNSaldo = findViewById(R.id.txtNSaldo)
        txtCBCantidad = findViewById(R.id.txtCBCantidad)

        btnRegistrarCuenta = findViewById(R.id.btnRegistrarCuenta)
        btnMovimiento = findViewById(R.id.btnMovimiento)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)

        txtUsuario.text =
            "Usuario: " + intent.getStringExtra("usuario")
    }

    fun eventosClick() {

        btnRegistrarCuenta.setOnClickListener {

            if (txtNumCuenta.text.toString().isEmpty() ||
                txtNombreCliente.text.toString().isEmpty() ||
                txtBanco.text.toString().isEmpty() ||
                txtSaldo.text.toString().isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Faltó capturar algún dato",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                cuenta.numCuenta =
                    txtNumCuenta.text.toString().toInt()

                cuenta.nombreCliente =
                    txtNombreCliente.text.toString()

                cuenta.banco =
                    txtBanco.text.toString()

                cuenta.saldo =
                    txtSaldo.text.toString().toFloat()

                cuentaRegistrada = true

                txtNSaldo.text =
                    "Nuevo Saldo: $" + cuenta.obtenerSaldo()
                Toast.makeText(
                    this, "Cuenta registrada correctamente",
                    Toast.LENGTH_SHORT).show()
            }
        }

        btnMovimiento.setOnClickListener {
            if (!cuentaRegistrada) {
                Toast.makeText(this, "Primero registre una cuenta",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (rdbConsulta.isChecked) {
                    Toast.makeText(
                        this, "Saldo actual: $" + cuenta.obtenerSaldo(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    if (txtCBCantidad.text.toString().isEmpty()) {
                        Toast.makeText(
                            this, "Capture una cantidad",
                            Toast.LENGTH_SHORT).show()

                    } else {
                        val cantidad = txtCBCantidad.text.toString().toFloat()
                        if (rdbDeposito.isChecked) {
                            cuenta.hacerDeposito(cantidad)
                            txtNSaldo.text =
                                "Nuevo Saldo: $" + cuenta.obtenerSaldo()
                            Toast.makeText(
                                this, "Depósito realizado",
                                Toast.LENGTH_SHORT).show()
                        }

                        if (rdbRetiro.isChecked) {
                            if (cuenta.retirarDinero(cantidad)) {
                                txtNSaldo.text =
                                    "Nuevo Saldo: $" + cuenta.obtenerSaldo()
                                Toast.makeText(
                                    this, "Retiro realizado",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Saldo insuficiente",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
        btnCerrar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Banco")
            builder.setMessage("¿Deseas cerrar la cuenta banco?")
            builder.setPositiveButton("ACEPTAR"){ dialog, which -> finish()
            }
            builder.setNegativeButton("CANCELAR"){ dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Continuamos con la app",
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        })
    }
}