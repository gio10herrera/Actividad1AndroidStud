package com.example.actividad1androidstud.views.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad1androidstud.R
import com.example.actividad1androidstud.models.Estudiante
import com.example.actividad1androidstud.models.EstudianteModel
import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Fields


class EstudianteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtNombreEstudiante: TextView = view.findViewById(R.id.txtNombreEstudiante)
    //modificar el view que recibamos
    fun render(estudiante: EstudianteModel){
        txtNombreEstudiante.text = estudiante.nombre
    }
}