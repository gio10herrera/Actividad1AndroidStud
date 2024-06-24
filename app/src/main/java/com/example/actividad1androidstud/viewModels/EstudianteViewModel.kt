package com.example.actividad1androidstud.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actividad1androidstud.models.EstudianteModel
import com.example.actividad1androidstud.models.EstudianteProvider

class EstudianteViewModel: ViewModel() {

    //Enganchar modelo y que reaccione ante el cambio
    val estudianteData = MutableLiveData<EstudianteModel?>()

    //Cambia los datos mostrados en el activity_buscar_estudiante cada vez
    //que se busca por una nueva dni
    fun actualizarEstMostrado(dni: String){
        val estActual = EstudianteProvider.dameEstPorDniFirestore(dni)
        estudianteData.postValue(estActual)
    }
}