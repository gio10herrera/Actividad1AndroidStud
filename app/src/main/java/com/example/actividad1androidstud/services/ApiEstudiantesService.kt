package com.example.actividad1androidstud.services

import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiEstudiantesService {
    @GET
    suspend fun getEstudiantes(@Url url:String) : EstudianteResponse
}

object RetrofitServiceFactory{
    //Funcion que crea un objeto retrofit a partir de una url
    fun getEstudiantesRetrofit(): ApiEstudiantesService {
        return Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/datos-estudiantes-a2a0d/databases/(default)/documents/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiEstudiantesService::class.java)
    }
}