package com.zoezelasqui.agendadeturnos

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AgregarTurnoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_turno)

        // OBTENER LOS CAMPOS DE LA PANTALLA

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val spServicio = findViewById<Spinner>(R.id.spServicio)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val etHora = findViewById<EditText>(R.id.etHora)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmarTurno)

        // LIMPIAR HORARIO CUANDO CAMBIA EL SERVICIO

        spServicio.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: android.view.View?,
                    position: Int,
                    id: Long
                ) {
                    etHora.setText("")
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {}
            }

        // SELECCIONAR FECHA

        etFecha.setOnClickListener {

            val calendario = Calendar.getInstance()

            val selectorFecha = DatePickerDialog(
                this,
                { _, anio, mes, dia ->

                    val fecha = "%02d/%02d/%04d".format(
                        dia,
                        mes + 1,
                        anio
                    )

                    etFecha.setText(fecha)

                    // LIMPIAR EL HORARIO ANTERIOR
                    etHora.setText("")
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
            )

            selectorFecha.datePicker.minDate =
                System.currentTimeMillis() - 1000

            selectorFecha.show()
        }

        // MOSTRAR HORARIOS DISPONIBLES

        etHora.setOnClickListener {

            val fecha = etFecha.text.toString()
            val servicio = spServicio.selectedItem.toString()

            if (
                fecha.isEmpty() ||
                spServicio.selectedItemPosition == 0
            ) {

                Toast.makeText(
                    this,
                    "Primero seleccioná un servicio y una fecha",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val horariosDisponibles =
                    DisponibilidadTurnos.obtenerHorariosDisponibles(
                        this,
                        fecha,
                        servicio
                    )

                if (horariosDisponibles.isEmpty()) {

                    AlertDialog.Builder(this)
                        .setTitle("Sin horarios disponibles")
                        .setMessage(
                            "No hay horarios disponibles para " +
                                    "el servicio seleccionado en esta fecha.\n\n" +
                                    "Probá con otro día."
                        )
                        .setPositiveButton("Aceptar", null)
                        .show()

                } else {

                    AlertDialog.Builder(this)
                        .setTitle("Seleccioná un horario disponible")
                        .setItems(
                            horariosDisponibles.toTypedArray()
                        ) { _, posicion ->

                            etHora.setText(
                                horariosDisponibles[posicion]
                            )
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }
            }
        }

        // CONFIRMAR TURNO

        btnConfirmar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val servicio = spServicio.selectedItem.toString()
            val fecha = etFecha.text.toString()
            val hora = etHora.text.toString()

            if (
                nombre.isEmpty() ||
                spServicio.selectedItemPosition == 0 ||
                fecha.isEmpty() ||
                hora.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Por favor, completá todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (
                !DisponibilidadTurnos.estaDisponible(
                    this,
                    fecha,
                    hora,
                    servicio
                )
            ) {

                AlertDialog.Builder(this)
                    .setTitle("Horario no disponible")
                    .setMessage(
                        "Este horario ya no está disponible.\n\n" +
                                "Seleccioná otro horario."
                    )
                    .setPositiveButton("Aceptar", null)
                    .show()

                etHora.setText("")

            } else {

                // GUARDAR TURNO

                val preferencias =
                    getSharedPreferences("turnos", MODE_PRIVATE)

                val turnosAnteriores =
                    preferencias.getString("lista_turnos", "") ?: ""

                val nuevoTurno =
                    "Clienta: $nombre\n" +
                            "Servicio: $servicio\n" +
                            "Fecha: $fecha\n" +
                            "Horario: $hora\n\n"

                preferencias.edit()
                    .putString(
                        "lista_turnos",
                        turnosAnteriores + nuevoTurno
                    )
                    .apply()

                // MOSTRAR CONFIRMACION

                AlertDialog.Builder(this)
                    .setTitle("¡Turno confirmado!")
                    .setMessage(
                        "Clienta: $nombre\n" +
                                "Servicio: $servicio\n" +
                                "Fecha: $fecha\n" +
                                "Horario: $hora"
                    )
                    .setPositiveButton("Aceptar", null)
                    .show()

                // LIMPIAR LOS CAMPOS

                etNombre.setText("")
                etFecha.setText("")
                etHora.setText("")
                spServicio.setSelection(0)
            }
        }
    }
}