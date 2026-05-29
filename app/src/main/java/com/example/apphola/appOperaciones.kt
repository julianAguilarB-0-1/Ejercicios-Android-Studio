package com.example.apphola

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appOperaciones : AppCompatActivity() {

    private lateinit var txtNum1 : EditText
    private lateinit var txtNum2: EditText
    private lateinit var spnOperaciones: Spinner
    private lateinit var img : ImageView
    private lateinit var txtResultado : TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private var pos: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_operaciones)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtNum1 = findViewById<EditText>(R.id.txtNum1)
        txtNum2 = findViewById<EditText>(R.id.txtNum2)
        spnOperaciones = findViewById<Spinner>(R.id.spnOperaciones)
        img = findViewById<ImageView>(R.id.imgOp)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)

        // datos del array
        val items = resources.getStringArray(R.array.operaciones)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        spnOperaciones.adapter = adapter
    }

    fun eventosClick() {
        // Spinner
        spnOperaciones.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {

                    pos = p2

                    when(pos){
                        0 -> img.setImageResource(R.mipmap.operaciones)
                        1 -> img.setImageResource(R.mipmap.suma)
                        2 -> img.setImageResource(R.mipmap.resta)
                        3 -> img.setImageResource(R.mipmap.multiplicar)
                        4 -> img.setImageResource(R.mipmap.dividir)
                        else -> img.setImageResource(R.mipmap.operaciones)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    pos = 0
                }
            }

        // calcular
        btnCalcular.setOnClickListener {

            // validar vacios
            if(txtNum1.text.toString().trim().isEmpty() ||
                txtNum2.text.toString().trim().isEmpty()){

                Toast.makeText(
                    this,
                    R.string.strMensaje,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // convertir seguros
            val num1 = txtNum1.text.toString().toFloatOrNull()

            val num2 = txtNum2.text.toString().toFloatOrNull()

            // validar numeros
            if(num1 == null || num2 == null){
                Toast.makeText(
                    this,
                    "Ingrese números válidos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // validar cero
            if(num1 == 0f || num2 == 0f){

                Toast.makeText(
                    applicationContext,
                    "El num1 y num2 no pueden ser cero",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // operaciones
            val resultado = when(pos){
                0 -> num1 + num2
                1 -> num1 - num2
                2 -> num1 * num2
                3 -> num1 / num2

                else -> 0.0f
            }

            txtResultado.text = String.format("Resultado: %.2f", resultado)
        }

        // limpiar
        btnLimpiar.setOnClickListener {
            txtNum1.setText("")
            txtNum2.setText("")
            txtResultado.text = "Su resultado aqui"
            spnOperaciones.setSelection(0)
            img.setImageResource(R.mipmap.bajopeso)
        }

        // cerrar
        btnCerrar.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Operaciones")
            builder.setMessage("¿Deseas cerrar la aplicación?")
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
        }
    }
}