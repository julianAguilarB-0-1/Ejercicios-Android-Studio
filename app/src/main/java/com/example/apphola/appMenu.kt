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
    private lateinit var crvpre1 : CardView
    private lateinit var crvpre2 : CardView

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
        crvpre1 = findViewById<CardView>(R.id.crvpre1)
        crvpre2 = findViewById<CardView>(R.id.crvpre2)
        crvCotizacion=findViewById<CardView>(R.id.crvCotizacion)
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
        crvMonedas.setOnClickListener {
            val intenteMoneda = Intent(this, appMonedas::class.java)
            startActivity(intenteMoneda)
        }
        crvpre1.setOnClickListener {
            val intentePre1 = Intent(this, appOperaciones::class.java)
            startActivity(intentePre1)
        }
        crvpre2.setOnClickListener {
            val intentePre2 = Intent(this, nominaActivity::class.java)
            startActivity(intentePre2)
        }
        crvCotizacion.setOnClickListener {
            val intenteCotizacion = Intent(this, clienteActivity::class.java)
            startActivity(intenteCotizacion)
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