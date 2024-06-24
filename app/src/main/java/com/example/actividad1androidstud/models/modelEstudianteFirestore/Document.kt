package com.example.actividad1androidstud.models.modelEstudianteFirestore

data class Document(
    val createTime: String,
    val fields: Fields,
    val name: String,
    val updateTime: String
)