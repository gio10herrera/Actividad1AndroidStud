package com.example.actividad1androidstud.models


import com.example.actividad1androidstud.models.modelEstudianteFirestore.Apellido
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Dni
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Document
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Edad
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Estado
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Fields
import com.example.actividad1androidstud.models.modelEstudianteFirestore.EstudianteResponse
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Master
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Nombre
import com.example.actividad1androidstud.models.modelEstudianteFirestore.Pais
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EstudianteProviderTest{

    // Configuración inicial para cada prueba
    private lateinit var estudianteProvider: EstudianteProvider.Companion

    @Before
    fun setUp() {
        // Inicializamos la lista de estudiantes para cada prueba
        EstudianteProvider.estudiantesFirestore = mutableListOf(
            EstudianteModel("Juan", "Perez", "12345678A", 20, "Master1", "España", "aprobado"),
            EstudianteModel("Maria", "Gomez", "87654321B", 22, "Master2", "México", "pendiente")
        )
    }

    // Prueba para verificar que se encuentra un estudiante por su DNI en Firestore
    @Test
    fun testDameEstPorDniFirestore_Found() {
        val estudiante = EstudianteProvider.dameEstPorDniFirestore("12345678A")
        assertNotNull(estudiante)
        assertEquals("Juan", estudiante?.nombre)
        assertEquals("Perez", estudiante?.apellido)
    }

    // Prueba para verificar que no se encuentra un estudiante por un DNI inexistente en Firestore
    @Test
    fun testDameEstPorDniFirestore_NotFound() {
        val estudiante = EstudianteProvider.dameEstPorDniFirestore("00000000X")
        assertNull(estudiante)
    }

    // Prueba para verificar la adaptación de la respuesta de Firestore a Estudiante
    @Test
    fun testEstudianteResponseAdapter() {
        // Datos simulados de respuesta de Firestore para la prueba
        val documentos = listOf(
            Document(
                createTime = "timestamp1",
                 fields = Fields(
                    nombre = Nombre("Carlos"),
                    apellido = Apellido("Lopez"),
                    dni = Dni("11223344C"),
                    edad = Edad("25"),
                    master = Master("Master3"),
                    pais = Pais("Argentina"),
                    estado = Estado("reprobado")
                ),
                name = "1",
                updateTime = "timestamp2"
            ),
            Document(
                createTime = "timestamp3",
                fields = Fields(
                    nombre = Nombre("Ana"),
                    apellido = Apellido("Martinez"),
                    dni = Dni("55667788D"),
                    edad = Edad("27"),
                    master = Master("Master4"),
                    pais = Pais("Chile"),
                    estado = Estado("aprobado")
                ),
                name = "2",
                updateTime = "timestamp4"
            )
        )

        // Crear una instancia de EstudianteResponse con los documentos simulados
        val estudianteResponse = EstudianteResponse(documentos)

        // Llamar a la función a probar
        val estudiantes = EstudianteProvider.estudianteResponseAdapter(estudianteResponse)

        // Verificar el resultado esperado
        assertEquals(2, estudiantes.size)

        val estudiante1 = estudiantes[0]
        assertEquals("Carlos", estudiante1.nombre)
        assertEquals("Lopez", estudiante1.apellido)
        assertEquals("11223344C", estudiante1.dni)
        assertEquals(25, estudiante1.edad)
        assertEquals("Master3", estudiante1.master)
        assertEquals("Argentina", estudiante1.pais)
        assertEquals("reprobado", estudiante1.estado)

        val estudiante2 = estudiantes[1]
        assertEquals("Ana", estudiante2.nombre)
        assertEquals("Martinez", estudiante2.apellido)
        assertEquals("55667788D", estudiante2.dni)
        assertEquals(27, estudiante2.edad)
        assertEquals("Master4", estudiante2.master)
        assertEquals("Chile", estudiante2.pais)
        assertEquals("aprobado", estudiante2.estado)
    }
}