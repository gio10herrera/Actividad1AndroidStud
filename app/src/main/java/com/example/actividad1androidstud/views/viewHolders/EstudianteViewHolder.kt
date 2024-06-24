package com.example.actividad1androidstud.views.viewHolders

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad1androidstud.R
import com.example.actividad1androidstud.models.EstudianteModel
import android.widget.ImageView


class EstudianteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //widgets del item_estudiante
    private val txtNombreEstudiante: TextView = view.findViewById(R.id.txtNombreEstudiante)
    private val txtDniEstudiante: TextView = view.findViewById(R.id.txtDniEstudiante)
    private val txtMasterEstudiante: TextView = view.findViewById(R.id.txtMasterEstudiante)
    private val imgViewBandera: ImageView = view.findViewById(R.id.imgViewBandera)

    //traemos el string "DNI" de nuestro archivo string por buenas practicas
    //y luego concatenarlo con el dni del estudiante y mostrarlo en el TextView
    private val dni: String = view.context.getString(R.string.dni)
    //modificar el view que recibamos
    @SuppressLint("SetTextI18n")
    fun render(estudiante: EstudianteModel){
        txtNombreEstudiante.text = estudiante.nombre + " " + estudiante.apellido
        txtDniEstudiante.text = dni + ": " + estudiante.dni
        txtMasterEstudiante.text = estudiante.master
        //le agregamos la bandera dependiendo al pais, tambien utilizamos una por
        //defecto
        when(estudiante.pais.lowercase()){
            "peru" -> imgViewBandera.setImageResource(R.drawable.bandera_peru)
            "ecuador" -> imgViewBandera.setImageResource(R.drawable.bandera_ecu)
            "chile" -> imgViewBandera.setImageResource(R.drawable.bandera_chile)
            "colombia" -> imgViewBandera.setImageResource(R.drawable.bandera_col)
            "argentina" -> imgViewBandera.setImageResource(R.drawable.bandera_arg)
            "mexico" -> imgViewBandera.setImageResource(R.drawable.bandera_mex)
            else -> imgViewBandera.setImageResource(R.drawable.sin_bandera)
        }

    }
}