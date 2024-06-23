package com.example.actividad1androidstud.models

import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse

class EstudianteProvider {
    companion object {

        fun dameEstPorDniFirestore(dni: String): EstudianteModel? {
            return  estudiantesFirestore.find { it.dni == dni }
        }

        //Rellenar estudiantes desde Firestore
        fun estudianteResponseAdapter(estudianteResponse: EstudianteResponse):MutableList<EstudianteModel>{
            var estudiantesArray: MutableList<EstudianteModel> = arrayListOf()
            for (doc in estudianteResponse.documents){
                estudiantesArray.add(
                    EstudianteModel(doc.fields.nombre.stringValue, doc.fields.apellido.stringValue,
                    doc.fields.dni.stringValue, doc.fields.edad.integerValue.toInt(), doc.fields.master.stringValue,
                    doc.fields.pais.stringValue, doc.fields.estado.stringValue))
            }
            return estudiantesArray
        }

        //Estudiantes desde Firestore
        var estudiantesFirestore: MutableList<EstudianteModel> = arrayListOf()

    }
}