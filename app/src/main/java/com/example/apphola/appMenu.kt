package com.example.apphola

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
    }

    fun eventosClick(){
        crvHola.setOnClickListener {
            //realizar un objeto intent para mostrar la actividad
            val intente = Intent(this, MainActivity::class.java)
            startActivity(intente)
        }
    }
}