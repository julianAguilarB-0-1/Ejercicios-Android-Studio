package com.example.apphola

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appIMC : AppCompatActivity() {
    private lateinit var txtAltura : EditText
    private lateinit var txtPeso : EditText
    private lateinit var txtResultado : TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentes()
        eventosClick()
    }

    fun iniciarComponentes(){
        txtAltura = findViewById<EditText>(R.id.txtAltura)
        txtPeso = findViewById<EditText>(R.id.txtPeso)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
    }

    fun eventosClick(){
        btnCalcular.setOnClickListener(View.OnClickListener{
            if (txtAltura.text.toString().contentEquals(charSequence = "") ||
                txtPeso.text.toString().contentEquals(charSequence = "")) {

                Toast.makeText(
                    applicationContext, "Faltó capturar datos",
                    Toast.LENGTH_SHORT).show()
            }
            else if (txtAltura.text.toString().contentEquals(charSequence = "0") ||
                txtPeso.text.toString().contentEquals(charSequence = "0")) {

                Toast.makeText(
                    applicationContext, "La altura y el peso no pueden ser cero",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                var altura: Float = txtAltura.text.toString().toFloat()
                var peso: Float = txtPeso.text.toString().toFloat()
                var imc: Float = 0.0f

                imc = peso / (altura * altura)
                txtResultado.text = imc.toString()
            }
        })

        //limpiar y cerrar
        btnLimpiar.setOnClickListener {
            txtAltura.setText("")
            txtPeso.setText("")
            txtResultado.setText("Su resultado aqui")
        }

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("app IMC")
            builder.setMessage(" ¿Deseas cerrar la aplicación?")
            builder.setPositiveButton("ACCEPTAR"){
                    dialog , which -> finish()
            }
            builder.setNegativeButton("CANCELAR"){
                    dialog , which ->
                Toast.makeText(applicationContext,
                    "Continuamos con la app", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}