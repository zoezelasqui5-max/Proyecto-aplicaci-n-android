package com.zoezelasqui.agendadeturnos

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TurnosRecurrentesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_turnos_recurrentes)

        // OBTENER LOS CAMPOS

        val etNombre = findViewById<EditText>(R.id.etNombreRecurrente)
        val spServicio = findViewById<Spinner>(R.id.spServicioRecurrente)
        val etFecha = findViewById<EditText>(R.id.etFechaRecurrente)
        val etHora = findViewById<EditText>(R.id.etHoraRecurrente)
        val etIntervalo = findViewById<EditText>(R.id.etIntervalo)
        val etCantidad = findViewById<EditText>(R.id.etCantidadTurnos)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmarRecurrentes)

        // LIMPIAR HORARIO SI CAMBIA EL SERVICIO

        spServicio.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: android.view.View?,
                    position: Int,
                    id: Long
                ) {
                    etHora.setText("")
                }

                override fun onNothingSelected(
                    parent: android.widget.AdapterView<*>?
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
                            "No hay horarios disponibles para esta fecha."
                        )
                        .setPositiveButton("Aceptar", null)
                        .show()

                } else {

                    AlertDialog.Builder(this)
                        .setTitle("Seleccioná un horario")
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

        // CONFIRMAR TURNOS RECURRENTES

        btnConfirmar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val servicio = spServicio.selectedItem.toString()
            val fechaInicial = etFecha.text.toString()
            val hora = etHora.text.toString()

            val intervalo = etIntervalo.text.toString().toIntOrNull()
            val cantidad = etCantidad.text.toString().toIntOrNull()

            if (
                nombre.isEmpty() ||
                spServicio.selectedItemPosition == 0 ||
                fechaInicial.isEmpty() ||
                hora.isEmpty() ||
                intervalo == null ||
                cantidad == null ||
                intervalo <= 0 ||
                cantidad <= 0 ||
                cantidad > 20
            ) {

                Toast.makeText(
                    this,
                    "Completá todos los campos correctamente (máximo 20 turnos)",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                val formatoFecha = SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                )

                formatoFecha.isLenient = false

                val fecha = try {
                    formatoFecha.parse(fechaInicial)
                } catch (e: Exception) {
                    null
                }

                if (fecha == null) {

                    Toast.makeText(
                        this,
                        "Fecha no válida",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    val calendario = Calendar.getInstance()
                    calendario.time = fecha

                    // CALCULAR FECHAS

                    val fechasCalculadas = mutableListOf<String>()

                    repeat(cantidad) {

                        fechasCalculadas.add(
                            formatoFecha.format(calendario.time)
                        )

                        calendario.add(
                            Calendar.DAY_OF_MONTH,
                            intervalo
                        )
                    }

                    // COMPROBAR DISPONIBILIDAD DE TODAS LAS FECHAS

                    val fechasNoDisponibles =
                        fechasCalculadas.filter { fechaTurno ->

                            !DisponibilidadTurnos.estaDisponible(
                                this,
                                fechaTurno,
                                hora,
                                servicio
                            )
                        }

                    if (fechasNoDisponibles.isNotEmpty()) {

                        AlertDialog.Builder(this)
                            .setTitle("Hay turnos no disponibles")
                            .setMessage(
                                "No se pueden reservar todos los turnos " +
                                        "porque las siguientes fechas no tienen " +
                                        "disponible el horario de las $hora:\n\n" +
                                        fechasNoDisponibles.joinToString("\n") +
                                        "\n\nProbá con otro horario o intervalo."
                            )
                            .setPositiveButton("Aceptar", null)
                            .show()

                    } else {

                        // VISTA PREVIA

                        val resumen =
                            fechasCalculadas.mapIndexed { indice, fechaTurno ->
                                "${indice + 1}. $fechaTurno - $hora"
                            }.joinToString("\n")

                        AlertDialog.Builder(this)
                            .setTitle("Confirmar $cantidad turnos")
                            .setMessage(
                                "Clienta: $nombre\n" +
                                        "Servicio: $servicio\n\n" +
                                        "Fechas:\n$resumen"
                            )
                            .setNegativeButton("Cancelar", null)
                            .setPositiveButton("Guardar") { _, _ ->

                                // COMPROBAR NUEVAMENTE ANTES DE GUARDAR

                                val siguenDisponibles =
                                    fechasCalculadas.all { fechaTurno ->

                                        DisponibilidadTurnos.estaDisponible(
                                            this,
                                            fechaTurno,
                                            hora,
                                            servicio
                                        )
                                    }

                                if (!siguenDisponibles) {

                                    AlertDialog.Builder(this)
                                        .setTitle("Disponibilidad modificada")
                                        .setMessage(
                                            "Alguno de los horarios ya no está disponible. " +
                                                    "Revisá las fechas antes de continuar."
                                        )
                                        .setPositiveButton("Aceptar", null)
                                        .show()

                                } else {

                                    // GUARDAR RESERVAS

                                    val preferencias =
                                        getSharedPreferences(
                                            "turnos",
                                            MODE_PRIVATE
                                        )

                                    val turnosAnteriores =
                                        preferencias.getString(
                                            "lista_turnos",
                                            ""
                                        ) ?: ""

                                    val nuevosTurnos = StringBuilder()

                                    fechasCalculadas.forEach { fechaTurno ->

                                        nuevosTurnos.append(
                                            "Clienta: $nombre\n" +
                                                    "Servicio: $servicio\n" +
                                                    "Fecha: $fechaTurno\n" +
                                                    "Horario: $hora\n\n"
                                        )
                                    }

                                    preferencias.edit()
                                        .putString(
                                            "lista_turnos",
                                            turnosAnteriores +
                                                    nuevosTurnos.toString()
                                        )
                                        .apply()

                                    AlertDialog.Builder(this)
                                        .setTitle("¡Reservas guardadas!")
                                        .setMessage(
                                            "Se guardaron $cantidad turnos correctamente.\n\n" +
                                                    "Podés consultarlos desde Mis turnos."
                                        )
                                        .setPositiveButton("Aceptar", null)
                                        .show()

                                    // LIMPIAR CAMPOS

                                    etNombre.setText("")
                                    etFecha.setText("")
                                    etHora.setText("")
                                    etIntervalo.setText("")
                                    etCantidad.setText("")
                                    spServicio.setSelection(0)
                                }
                            }
                            .show()
                    }
                }
            }
        }
    }
}