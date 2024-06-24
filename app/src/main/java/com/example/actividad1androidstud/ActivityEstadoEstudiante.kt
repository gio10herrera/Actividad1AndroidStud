package com.example.actividad1androidstud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actividad1androidstud.views.MainActivity

class ActivityEstadoEstudiante : AppCompatActivity() {
    //Activity para mostrar si el estudiante esta aprobado, pendiente o reprobado
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estado_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainEstadoEst)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar los datos del Intent
        val paquete : Bundle? = intent.extras

        val nombre = paquete?.getString("nombre")?:""
        val apellido = paquete?.getString("apellido")?:""
        val master = paquete?.getString("master")?:""
        val estado = paquete?.getString("estado")?:""

        val txtNombreApellido: TextView = findViewById(R.id.txtNombreApellido)
        val txtMaster: TextView = findViewById(R.id.txtMaster)
        val txtEstado: TextView = findViewById(R.id.txtEstado)
        val btnRegresar: Button = findViewById(R.id.btnRegresar)

        txtNombreApellido.text = "$apellido $nombre"
        txtMaster.text = master
        txtEstado.text = estado
        cambiarColorEstado(txtEstado)

        btnRegresar.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cambiarColorEstado(txtEstado: TextView){
        val estado: String = txtEstado.text.toString()
        when(estado.lowercase()){
            "aprobado" -> txtEstado.setTextColor(getColor(R.color.verde))
            "pendiente" -> txtEstado.setTextColor(getColor(R.color.amarillo))
            "reprobado" -> txtEstado.setTextColor(getColor(R.color.rojo))
            else -> txtEstado.setTextColor(getColor(R.color.textosSecundarios))
        }
    }
}