package com.example.actividad1androidstud.views

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad1androidstud.R
import com.example.actividad1androidstud.adapters.EstudianteAdapter
import com.example.actividad1androidstud.models.Estudiante
import com.example.actividad1androidstud.models.EstudianteModel
import com.example.actividad1androidstud.models.EstudianteProvider
import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse
import com.example.actividad1androidstud.services.RetrofitServiceFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val estudiantes = mutableListOf<EstudianteModel>()
    /*private val estudiantes = listOf(
        EstudianteModel("Giovan", "Herrera", "1555245", 37, "Big Data", "Colombia", "Aprobado"),
        EstudianteModel("Carlos", "Posada", "1000000", 25, "Desarrollo Web", "Ecuador", "Aprobado"),
        EstudianteModel("Juan", "Fernandez", "11111111", 21, "Analisis de Datos", "Peru", "Aprobado"),
        EstudianteModel("Raquel", "Sarmiento", "333333", 18, "Big Data", "Venezuela", "Aprobado"),
        EstudianteModel("Santiago", "Angulo", "444444", 32, "Big Data", "Colombia", "Aprobado"),
    )*/



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainPrincipal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBuscarEst: Button = findViewById(R.id.btnBuscarEst)
        btnBuscarEst.setOnClickListener {
            val intent = Intent(this, BuscarEstudiante::class.java)
            startActivity(intent)
        }

        //capturar los recyclers
        val rvEstudiantes : RecyclerView = findViewById(R.id.rvEstudiantes)




        //Montamos el recycler de estudiantes
        val estudiantesAdapter = EstudianteAdapter(estudiantes)
        rvEstudiantes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvEstudiantes.adapter = estudiantesAdapter

        //Lanzamos la petición contra Firestore
        //Montamos el servicio para lanzar la peticion contra el api
        val apiEstudiantesService = RetrofitServiceFactory.getEstudiantesRetrofit()

        lifecycleScope.launch {
            val estudiantesResponse: EstudianteResponse = apiEstudiantesService.getEstudiantes("estudiantes")
            //Relleno los datos del estudiante provider
            val estudiantesList = EstudianteProvider.estudianteResponseAdapter(estudiantesResponse)
            estudiantes.clear()
            estudiantes.addAll(estudiantesList)
            estudiantesAdapter.notifyDataSetChanged()

        }

    }
}