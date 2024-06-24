package com.example.actividad1androidstud.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.actividad1androidstud.R
import com.example.actividad1androidstud.models.EstudianteProvider
import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse
import com.example.actividad1androidstud.services.RetrofitServiceFactory
import com.example.actividad1androidstud.viewModels.EstudianteViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class BuscarEstudiante : AppCompatActivity() {

    //by viewModels() engancha directamente todo el ciclo de vida (on create, observable, etc)
    //de un activity sin tener que extender a programarlo
    private val estudianteViewModel: EstudianteViewModel by viewModels()

    //todos los componentes del layout
    private lateinit var mainBuscarEst: ConstraintLayout
    private lateinit var editTxtDni: TextInputLayout
    private lateinit var btnBuscar: Button
    private lateinit var txtNombre: TextView
    private lateinit var txtApellido: TextView
    private lateinit var txtDni: TextView
    private lateinit var txtEdad: TextView
    private lateinit var txtMaster: TextView
    private lateinit var txtPais: TextView
    private lateinit var txtEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_estudiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainBuscarEst)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recuperar los widgets
        mainBuscarEst = findViewById(R.id.mainBuscarEst)
        editTxtDni = findViewById(R.id.txtInputLayoutDni)
        btnBuscar = findViewById(R.id.btnBuscar)
        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtDni = findViewById(R.id.txtDni)
        txtEdad = findViewById(R.id.txtEdad)
        txtMaster = findViewById(R.id.txtMaster)
        txtPais = findViewById(R.id.txtPais)
        txtEstado = findViewById(R.id.txtEstado)





        //programar el listener del boton buscar
        btnBuscar.setOnClickListener {
            val dni: String = editTxtDni.editText?.text.toString()
            estudianteViewModel.actualizarEstMostrado(dni)
        }

        //Enganchar la info de viewModel con los
        //widgets del layout, en caso que sea nulo limpia los widgets
        estudianteViewModel.estudianteData.observe(this, Observer {
            if (it != null) {
                txtNombre.text = it.nombre
                txtApellido.text = it.apellido
                txtDni.text = it.dni
                txtEdad.text = it.edad.toString()
                txtMaster.text = it.master
                txtPais.text = it.pais
                txtEstado.text = it.estado
            } else {
                txtNombre.text = ""
                txtApellido.text = ""
                txtDni.text = ""
                txtEdad.text = ""
                txtMaster.text = ""
                txtPais.text = ""
                txtEstado.text = ""
                //mandamos una notificación informando que el estudiante no fue encontrado
                //Usando un Snackbar de material design
                Snackbar.make(mainBuscarEst, R.string.infoSnackbar, Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.botones))
                    .setTextColor(resources.getColor(R.color.textosSecundarios))
                    .show()

            }
        })

        //Lanzamos la petición contra Firestore
        //Montamos el servicio para lanzar la peticion contra el api
        val apiEstudiantesService = RetrofitServiceFactory.getEstudiantesRetrofit()

        lifecycleScope.launch {
            val estudiantesResponse: EstudianteResponse = apiEstudiantesService.getEstudiantes("estudiantes")
            //Relleno los datos del estudiante provider
            EstudianteProvider.estudiantesFirestore = EstudianteProvider.estudianteResponseAdapter(estudiantesResponse)
        }
    }

}