package com.example.apphola

import android.content.Intent
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

class clienteActivity : AppCompatActivity() {
    private lateinit var txtCliente : TextView
    private lateinit var btnIngresar : Button
    private lateinit var btnRegresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)

        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun iniciarComponentes(){
        txtCliente =findViewById<EditText>(R.id.txtCliente)
        btnRegresar=findViewById<Button>(R.id.btnRegresar)
        btnIngresar=findViewById<Button>(R.id.btnIngresar)
    }

    public fun eventosClick(){
        btnIngresar.setOnClickListener(View.OnClickListener{
            if(txtCliente.text.toString().contentEquals(charSequence = "")){
                Toast.makeText(this,
                    "Falto capturar el nombre del cliente", Toast.LENGTH_SHORT).show();
                txtCliente.requestFocus()
            } else{
                val intent= Intent(this, appCotizacion::class.java)
                intent.putExtra("cliente",txtCliente.text.toString())
                startActivity(intent)
            } })

        btnRegresar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Cotización")
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
        })
    }

}