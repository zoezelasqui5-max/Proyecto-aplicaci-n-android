package com.zoezelasqui.agendadeturnos

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNuevoTurno = findViewById<Button>(R.id.btnNuevoTurno)
        val btnMisTurnos = findViewById<Button>(R.id.btnMisTurnos)
        val btnTurnosRecurrentes = findViewById<Button>(R.id.btnTurnosRecurrentes)

        btnNuevoTurno.setOnClickListener {
            val intent = Intent(this, AgregarTurnoActivity::class.java)
            startActivity(intent)
        }

            btnMisTurnos.setOnClickListener {
                Toast.makeText(
                    this,
                    "Consultar mis turnos",
                    Toast.LENGTH_SHORT
                ).show()
            }

            btnTurnosRecurrentes.setOnClickListener {
                Toast.makeText(
                    this,
                    "Reservar varios turnos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
