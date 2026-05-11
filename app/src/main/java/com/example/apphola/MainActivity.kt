package com.example.apphola

import android.os.Bundle
import android.text.method.Touch
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // declarar objetos
    private lateinit var txtSaludo: EditText
    private lateinit var btnSaludar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //llamar a las funciones
        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){
        //relacionar los componentes del layout con los objetos
        txtSaludo = findViewById<EditText>(R.id.txtnombre)
        btnSaludar = findViewById<Button>(R.id.btnPulsar)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
    }

    fun eventosClick(){
        btnSaludar.setOnClickListener(View.OnClickListener{
            var strNombre : String = ""
            //validar
            if(txtSaludo.text.toString().contentEquals(charSequence = "")) {
                Toast.makeText(
                    applicationContext, "Falto capturar el nombre",
                    Toast.LENGTH_SHORT ).show()
            }
            else{
                strNombre= "Hola " + txtSaludo.text.toString() + " ,como estas "
                txtSaludo.setText(strNombre)
            }
        })

        btnLimpiar.setOnClickListener {
            txtSaludo.setText("")
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}