package com.example.apphola

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainCBActivity : AppCompatActivity() {

    private lateinit var txtUsuario : TextView
    private lateinit var txtPassword : TextView
    private lateinit var btnIngresar : Button
    private lateinit var btnRegresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_cbactivity)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes(){
        txtUsuario=findViewById<TextView>(R.id.txtUsuario)
        txtPassword=findViewById<TextView>(R.id.txtPassword)
        btnIngresar=findViewById<Button>(R.id.btnIngresar)
        btnRegresar=findViewById<Button>(R.id.btnRegresar)
    }

    private fun eventosClick(){
        btnIngresar.setOnClickListener {

            if (txtUsuario.text.toString().isEmpty() ||
                txtPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Faltó capturar usuario o contraseña",
                    Toast.LENGTH_SHORT).show()
            } else {
                val usuarioCorrecto = getString(R.string.user)
                val passwordCorrecto = getString(R.string.pass)

                if (txtUsuario.text.toString() == usuarioCorrecto &&
                    txtPassword.text.toString() == passwordCorrecto) {
                    val intent = Intent(this, CuentabancoActivity::class.java)
                    intent.putExtra("usuario", txtUsuario.text.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRegresar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Examen")
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