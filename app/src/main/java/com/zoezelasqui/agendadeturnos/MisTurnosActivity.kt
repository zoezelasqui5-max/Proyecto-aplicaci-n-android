package com.zoezelasqui.agendadeturnos

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MisTurnosActivity : AppCompatActivity() {

    private lateinit var contenedor: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_turnos)

        contenedor = findViewById(R.id.contenedorTurnos)

        mostrarTurnos()
    }

    // MOSTRAR LAS RESERVAS GUARDADAS

    private fun mostrarTurnos() {

        // LIMPIAR LA PANTALLA ANTES DE ACTUALIZARLA

        contenedor.removeAllViews()

        val preferencias = getSharedPreferences(
            "turnos",
            MODE_PRIVATE
        )

        val listaTurnos = preferencias.getString(
            "lista_turnos",
            ""
        ) ?: ""

        // COMPROBAR SI EXISTEN RESERVAS

        if (listaTurnos.isBlank()) {

            val mensaje = TextView(this).apply {
                text = "Todavía no tenés turnos registrados."
                textSize = 16f
                setTextColor(Color.DKGRAY)
                setPadding(20, 30, 20, 30)
            }

            contenedor.addView(mensaje)
            return
        }

        // SEPARAR LOS TURNOS

        val turnos = listaTurnos.trim()
            .split("\n\n")
            .filter { it.isNotBlank() }

        // CREAR UNA TARJETA PARA CADA RESERVA

        turnos.forEachIndexed { indice, turno ->

            val tarjeta = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(35, 30, 35, 30)
                setBackgroundColor(Color.WHITE)
                elevation = 6f
            }

            val titulo = TextView(this).apply {
                text = "Turno ${indice + 1}"
                textSize = 19f
                setTextColor(Color.rgb(103, 58, 183))
                setTypeface(null, Typeface.BOLD)
            }

            val datos = TextView(this).apply {
                text = turno
                textSize = 16f
                setTextColor(Color.DKGRAY)
                setPadding(0, 15, 0, 15)
            }

            // BOTÓN PARA CANCELAR EL TURNO

            val btnCancelar = Button(this).apply {
                text = "Cancelar turno"
                setTextColor(Color.WHITE)
                setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(
                        Color.rgb(156, 39, 176)
                    )
                )
            }

            btnCancelar.setOnClickListener {

                AlertDialog.Builder(this)
                    .setTitle("¿Cancelar este turno?")
                    .setMessage(
                        "¿Estás segura de que querés eliminar esta reserva?\n\n$turno"
                    )
                    .setNegativeButton("Volver", null)
                    .setPositiveButton("Sí, cancelar") { _, _ ->

                        // ELIMINAR SOLAMENTE EL TURNO SELECCIONADO

                        val turnosActualizados = turnos.toMutableList()

                        turnosActualizados.removeAt(indice)

                        // GUARDAR LA LISTA ACTUALIZADA

                        val nuevaLista = if (turnosActualizados.isEmpty()) {
                            ""
                        } else {
                            turnosActualizados.joinToString("\n\n") + "\n\n"
                        }

                        preferencias.edit()
                            .putString("lista_turnos", nuevaLista)
                            .apply()

                        // ACTUALIZAR LA PANTALLA

                        mostrarTurnos()
                    }
                    .show()
            }

            // AGREGAR LOS ELEMENTOS A LA TARJETA

            tarjeta.addView(titulo)
            tarjeta.addView(datos)
            tarjeta.addView(btnCancelar)

            val parametros = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 25
            }

            contenedor.addView(tarjeta, parametros)
        }
    }
}