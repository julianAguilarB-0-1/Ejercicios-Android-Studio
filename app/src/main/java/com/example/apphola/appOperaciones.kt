package com.example.apphola

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_operaciones)
        iniciarComponentes()
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
}