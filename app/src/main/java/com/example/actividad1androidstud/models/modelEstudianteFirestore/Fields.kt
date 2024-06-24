package com.example.actividad1androidstud.models.modelEstudianteFirestore

data class Fields(
    val apellido: Apellido,
    val dni: Dni,
    val edad: Edad,
    val estado: Estado,
    val master: Master,
    val nombre: Nombre,
    val pais: Pais
)