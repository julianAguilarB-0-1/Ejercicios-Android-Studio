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
import com.example.appholamundo.ReciboNomina
import kotlin.math.abs

class ReciboNominaActivity : AppCompatActivity() {

    private lateinit var txtNumRecibo : TextView
    private lateinit var txtNombreT : TextView

    private lateinit var txtHorasNormal : EditText
    private lateinit var txtHorasExtra : EditText

    private lateinit var rbdAuxiliar : RadioButton
    private lateinit var rbdAlbañil : RadioButton
    private lateinit var rbdIng : RadioButton

    private lateinit var txtSubtotal : TextView
    private lateinit var txtImpuesto : TextView
    private lateinit var txtTotalpag : TextView

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recibo_nomina)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes(){
        txtNumRecibo=findViewById<TextView>(R.id.txtNumRecibo)
        txtNombreT=findViewById<TextView>(R.id.txtNombreT)

        txtHorasNormal=findViewById<EditText>(R.id.txtHorasNormal)
        txtHorasExtra=findViewById<EditText>(R.id.txtHorasExtra)

        rbdAuxiliar=findViewById<RadioButton>(R.id.rbdAuxiliar)
        rbdAlbañil=findViewById<RadioButton>(R.id.rbdAlbañil)
        rbdIng=findViewById<RadioButton>(R.id.rbdIng)

        txtSubtotal=findViewById<TextView>(R.id.txtSubtotal)
        txtImpuesto=findViewById<TextView>(R.id.txtImpuesto)
        txtTotalpag=findViewById<TextView>(R.id.txtTotalpag)

        btnCalcular=findViewById<Button>(R.id.btnCalcular)
        btnLimpiar=findViewById<Button>(R.id.btnLimpiar)
        btnCerrar=findViewById<Button>(R.id.btnCerrar)

        var strTrabajador: String = intent.getStringExtra("txtNombreT").toString()
        txtNombreT.text = strTrabajador.toString();

        //agregar numrecibo
        var numrecibo: Int = abs(ReciboNomina().generaRecibo())
        txtNumRecibo.text=numrecibo.toString()
    }

    private fun eventosClick(){

        btnCalcular.setOnClickListener {

            if (txtHorasNormal.text.toString().isEmpty() ||
                txtHorasExtra.text.toString().isEmpty())
            {
                Toast.makeText(
                    this,
                    "Falto capturar algún dato",
                    Toast.LENGTH_SHORT
                ).show()
            }else{

                val recibo = ReciboNomina()

                recibo.nombre = txtNombreT.text.toString()
                recibo.horasNormales = txtHorasNormal.text.toString().toInt()
                recibo.horasExtra = txtHorasExtra.text.toString().toInt()

                if (rbdAuxiliar.isChecked) recibo.puesto = 1
                if (rbdAlbañil.isChecked) recibo.puesto = 2
                if (rbdIng.isChecked) recibo.puesto = 3

                txtSubtotal.text = "Subtotal"+": $"+recibo.calcularSubtotal()
                txtImpuesto.text = "Impuesto"+": $"+recibo.calcularImpuesto()
                txtTotalpag.text = "Total a Pagar"+": $"+recibo.calcularTotalPagar()
            }
        }


        btnLimpiar.setOnClickListener {
            txtHorasNormal.setText("")
            txtHorasExtra.setText("")

            txtSubtotal.text = "Subtotal"
            txtImpuesto.text = "Impuesto"
            txtTotalpag.text = "Total a Pagar"

            rbdAuxiliar.isChecked = true
        }

        btnCerrar.setOnClickListener(View.OnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Nomina")
            builder.setMessage("¿Deseas cerrar el Recibo Nomina?")
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