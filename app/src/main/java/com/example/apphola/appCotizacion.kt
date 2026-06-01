package com.example.apphola

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appholamundo.Cotizacion
import kotlin.math.abs

class appCotizacion : AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_cotizacion)

        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtCliente=findViewById(R.id.txtCliente) as TextView
        txtFolio=findViewById(R.id.txtFolio) as TextView
        txtDescripcion=findViewById(R.id.txtDescripcion) as EditText
        txtPrecio=findViewById(R.id.txtPrecio) as EditText
        txtPorPagI=findViewById(R.id.txtPorcentaje) as EditText
        txtPagoInicial=findViewById(R.id.txtPagoInicial) as TextView
        txtTotalFin=findViewById(R.id.txtTotalfin) as TextView
        txtPagoMensual=findViewById(R.id.txtPagoMensual) as TextView

        rdb12=findViewById(R.id.rbd12) as RadioButton
        rdb24=findViewById(R.id.rbd24) as RadioButton
        rdb36=findViewById(R.id.rbd36) as RadioButton
        rdb48=findViewById(R.id.rbd48) as RadioButton

        btnCalcular=findViewById(R.id.btnCalcular) as Button
        btnLimpiar=findViewById(R.id.btnLimpiar) as Button
        btnCerrar=findViewById(R.id.btnCerrar) as Button

        var strCliente: String = intent.getStringExtra("cliente").toString()
        txtCliente.text = strCliente.toString();

        //agregar Folio
        var folio: Int = abs(Cotizacion().generaFolio())
        txtFolio.text="Folio: "+folio.toString()
    }

    fun eventosClick(){
        btnCalcular.setOnClickListener(View.OnClickListener{
            //generar objeto de clase cotizacion
            var cotizacion = Cotizacion()
            //validar
            if(txtDescripcion.text.toString().contentEquals(charSequence = "") ||
                txtPrecio.text.toString().contentEquals(charSequence = "") ||
                txtPorPagI.text.toString().contentEquals(charSequence = "")){
                Toast.makeText(this,"Falto capturar algun dato",
                    Toast.LENGTH_SHORT).show()
            }else{
                txtFolio.text = cotizacion.generaFolio().toString()
                cotizacion.descripcion = txtDescripcion.text.toString()
                cotizacion.precio=txtPrecio.text.toString().toFloat()
                cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()
                //plazos
                if(rdb12.isChecked) cotizacion.plazos=12
                if(rdb24.isChecked) cotizacion.plazos=24
                if(rdb36.isChecked) cotizacion.plazos=36
                if(rdb48.isChecked) cotizacion.plazos=48
                //calculos
                txtPagoInicial.text = "Pago Inicial"+": $"+cotizacion.calcularPagoInicial()
                txtTotalFin.text = "Total a Financiar"+": $"+cotizacion.calcularTotalFin()
                txtPagoMensual.text = "Pago Mensual"+": $"+cotizacion.calcularPagoMensual()
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener{
            //limpiar textviews
            txtFolio.text=""
            txtPagoInicial.text="Pago Inicial"
            txtPagoMensual.text="Pago Mensual"
            txtTotalFin.text="Total a Financiar"
            //los edittext
            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtPorPagI.setText("")
            // activar el radio
            rdb12.isChecked=true
        })

        btnCerrar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Cotización")
            builder.setMessage("¿Deseas cerrar la cotización?")
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