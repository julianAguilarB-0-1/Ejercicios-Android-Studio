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

class nominaActivity : AppCompatActivity() {
    private lateinit var txtNombreT : EditText
    private lateinit var btnIngresar : Button
    private lateinit var btnRegresar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nomina)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun iniciarComponentes(){
        txtNombreT=findViewById<EditText>(R.id.txtNombreT)
        btnIngresar=findViewById<Button>(R.id.btnIngresar)
        btnRegresar=findViewById<Button>(R.id.btnRegresar)

    }
    public fun eventosClick(){
        btnIngresar.setOnClickListener(View.OnClickListener{
            if(txtNombreT.text.toString().contentEquals(charSequence = "")){
                Toast.makeText(this,
                    "Falto capturar el nombre del trabajador",
                    Toast.LENGTH_SHORT).show();
                txtNombreT.requestFocus()
            } else{
                val intent= Intent(this, ReciboNominaActivity::class.java)
                intent.putExtra("txtNombreT",txtNombreT.text.toString())
                startActivity(intent)
            } })

        btnRegresar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Nomina")
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