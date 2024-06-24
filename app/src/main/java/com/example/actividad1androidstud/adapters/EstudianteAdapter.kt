package com.example.actividad1androidstud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad1androidstud.R
import com.example.actividad1androidstud.models.Estudiante
import com.example.actividad1androidstud.models.EstudianteModel
import com.example.actividad1androidstud.views.viewHolders.EstudianteViewHolder

class EstudianteAdapter(private val estudiantes: List<EstudianteModel>) : RecyclerView.Adapter<EstudianteViewHolder>() {
    //inflar el elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        //crear el view a partir del layout item_estudiante
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante,parent,false)
        return EstudianteViewHolder(view)
    }

    //contar los items
    override fun getItemCount(): Int {
        return estudiantes.size
    }

    //adaptar una de las vistas
    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val est = estudiantes[position]
        holder.render(est)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(est.nombre, est.apellido, est.master, est.estado)
        }
    }

    // Interfaz para manejar los clics
    interface OnItemClickListener {
        fun onItemClick(nombre: String, apellido: String, master: String, estado: String)
    }

    private var listener: OnItemClickListener? = null

    // Método para establecer el listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}