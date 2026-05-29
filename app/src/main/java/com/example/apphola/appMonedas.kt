
package com.example.apphola

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appMonedas : AppCompatActivity() {

    private lateinit var txtCantidad : EditText
    private lateinit var spnMonedas: Spinner
    private lateinit var txtResultado: TextView
    private lateinit var btnConvertir: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private var pos: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_monedas)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        txtCantidad=findViewById<EditText>(R.id.txtCantidad)
        txtResultado=findViewById<TextView>(R.id.txtResultado)
        spnMonedas=findViewById<Spinner>(R.id.spnMonedas)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnConvertir = findViewById<Button>(R.id.btnConvertir)
        // obtener los datos del array.string para ponerlo en el adapter
        val items = resources.getStringArray(R.array.monedas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            items)
        spnMonedas.adapter = adapter

    }

    fun eventosClick(){
        //buscar la posicion del elemento seleccionado
        spnMonedas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = p2
            }

            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btnConvertir.setOnClickListener {
            //validar
            if(txtCantidad.text.toString().contentEquals(charSequence = "")){
                Toast.makeText(this, R.string.strMensaje, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var dolarA:Float=getString(R.string.dolarA).toFloat()
            var dolarC:Float=getString(R.string.dolarc).toFloat()
            var euro:Float=getString(R.string.euro).toFloat()
            var libra:Float=getString(R.string.libra).toFloat()
            var cantMx:Float=txtCantidad.text.toString().toFloat()

            val resultado = when(pos){
                0->cantMx/dolarA
                1->cantMx/dolarC
                2->cantMx/euro
                3->cantMx/libra
                else -> 0.0f
            }
            txtResultado.setText(resultado.toString())
        }


        //limpiar y cerrar
        btnLimpiar.setOnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("Su resultado aqui")
            spnMonedas.setSelection(0)

        }

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("app Monedas")
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