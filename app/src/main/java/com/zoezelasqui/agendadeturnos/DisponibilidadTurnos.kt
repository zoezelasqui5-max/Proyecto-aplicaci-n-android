package com.zoezelasqui.agendadeturnos

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DisponibilidadTurnos {

    // DURACION DE CADA SERVICIO EN MINUTOS

    fun duracionServicio(servicio: String): Int {
        return when (servicio.trim().lowercase()) {
            "semipermanente" -> 30
            "kapping", "capping" -> 60
            "esculpidas" -> 120
            "retiro de esmalte" -> 15
            else -> 0
        }
    }

    // HORARIOS DE ATENCION SEGUN EL DIA

    private fun horarioAtencion(fecha: String): Pair<Int, Int>? {

        val formato = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        )

        formato.isLenient = false

        val fechaConvertida = try {
            formato.parse(fecha)
        } catch (e: Exception) {
            null
        } ?: return null

        val calendario = Calendar.getInstance()
        calendario.time = fechaConvertida

        return when (calendario.get(Calendar.DAY_OF_WEEK)) {

            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY -> Pair(10 * 60, 16 * 60)

            Calendar.SATURDAY -> Pair(10 * 60, 12 * 60)

            else -> null
        }
    }

    // CONVERTIR HORARIO A MINUTOS

    private fun horaAMinutos(hora: String): Int? {

        val partes = hora.split(":")

        if (partes.size != 2) return null

        val horas = partes[0].toIntOrNull() ?: return null
        val minutos = partes[1].toIntOrNull() ?: return null

        if (horas !in 0..23 || minutos !in 0..59) {
            return null
        }

        return horas * 60 + minutos
    }

    // CONSULTAR TURNOS GUARDADOS

    private fun obtenerTurnos(
        context: Context
    ): List<String> {

        val preferencias = context.getSharedPreferences(
            "turnos",
            Context.MODE_PRIVATE
        )

        val texto = preferencias.getString(
            "lista_turnos",
            ""
        ) ?: ""

        return texto.trim()
            .split(Regex("\\n\\s*\\n"))
            .filter { it.isNotBlank() }
    }

    // COMPROBAR SI UN HORARIO ESTA DISPONIBLE

    fun estaDisponible(
        context: Context,
        fecha: String,
        hora: String,
        servicio: String
    ): Boolean {

        val duracion = duracionServicio(servicio)

        if (duracion <= 0) return false

        val horario = horarioAtencion(fecha)
            ?: return false

        val inicio = horaAMinutos(hora)
            ?: return false

        val fin = inicio + duracion

        // VERIFICAR HORARIO LABORAL

        if (inicio < horario.first || fin > horario.second) {
            return false
        }

        // VERIFICAR RESERVAS EXISTENTES

        val turnos = obtenerTurnos(context)

        for (turno in turnos) {

            val datos = turno.lines()

            val fechaReservada = datos
                .find { it.startsWith("Fecha:") }
                ?.substringAfter("Fecha:")
                ?.trim()

            val horaReservada = datos
                .find { it.startsWith("Horario:") }
                ?.substringAfter("Horario:")
                ?.trim()

            val servicioReservado = datos
                .find { it.startsWith("Servicio:") }
                ?.substringAfter("Servicio:")
                ?.trim()

            if (fechaReservada != fecha) continue

            val inicioReservado = horaAMinutos(
                horaReservada ?: ""
            ) ?: continue

            val duracionReservada = duracionServicio(
                servicioReservado ?: ""
            )

            // Por seguridad, no ignorar servicios desconocidos
            if (duracionReservada <= 0) return false

            val finReservado =
                inicioReservado + duracionReservada

            // DETECTAR SUPERPOSICIONES

            if (
                inicio < finReservado &&
                fin > inicioReservado
            ) {
                return false
            }
        }

        return true
    }

    // GENERAR LISTA DE HORARIOS DISPONIBLES

    fun obtenerHorariosDisponibles(
        context: Context,
        fecha: String,
        servicio: String
    ): List<String> {

        val horario = horarioAtencion(fecha)
            ?: return emptyList()

        val duracion = duracionServicio(servicio)

        if (duracion <= 0) return emptyList()

        val disponibles = mutableListOf<String>()

        // GENERAR HORARIOS CADA 15 MINUTOS

        for (
        minutos in horario.first..
                (horario.second - duracion) step 15
        ) {

            val hora = "%02d:%02d".format(
                minutos / 60,
                minutos % 60
            )

            if (
                estaDisponible(
                    context,
                    fecha,
                    hora,
                    servicio
                )
            ) {
                disponibles.add(hora)
            }
        }

        return disponibles
    }
}

