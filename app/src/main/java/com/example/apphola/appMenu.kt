package com.example.apphola

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appMenu : AppCompatActivity() {
    private lateinit var crvHola : CardView
    private lateinit var crvIMC : CardView
    private lateinit var crvGrados : CardView
    private lateinit var crvMonedas : CardView
    private lateinit var crvSpinner : CardView
    private lateinit var crvCotizacion : CardView
    private lateinit var crvSalir : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_menu)
        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        crvHola = findViewById<CardView>(R.id.crvHola)
        crvIMC = findViewById<CardView>(R.id.crvIMC)
        crvGrados = findViewById<CardView>(R.id.crvGrados)
        crvMonedas = findViewById<CardView>(R.id.crvMonedas)
        crvSpinner = findViewById<CardView>(R.id.crvSpinner)
        crvCotizacion = findViewById<CardView>(R.id.crvCotizacion)
        crvSalir = findViewById<CardView>(R.id.crvSalir)
    }

    fun eventosClick(){
        crvHola.setOnClickListener {
            //realizar un objeto intent para mostrar la actividad
            val intente = Intent(this, MainActivity::class.java)
            startActivity(intente)
        }

        crvIMC.setOnClickListener {
            val intenteIMC = Intent(this, appIMC::class.java)
            startActivity(intenteIMC)
        }
        crvGrados.setOnClickListener {
            val intenteGrados = Intent(this, appGrados::class.java)
            startActivity(intenteGrados)
        }

        crvSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Aplicación")
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