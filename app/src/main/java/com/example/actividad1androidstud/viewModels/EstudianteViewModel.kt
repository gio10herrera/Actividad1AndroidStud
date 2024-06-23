package com.example.actividad1androidstud.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actividad1androidstud.models.EstudianteModel
import com.example.actividad1androidstud.models.EstudianteProvider

class EstudianteViewModel: ViewModel() {

    //Enganchar modelo y que reaccione ante el cambio
    val estudianteData = MutableLiveData<EstudianteModel?>()

    fun actualizarEstMostrado(dni: String){
        val estActual = EstudianteProvider.dameEstPorDniFirestore(dni)
        estudianteData.postValue(estActual)
    }
}