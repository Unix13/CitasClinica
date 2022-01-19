package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static final int NUM_MAX_CITAS = 30;
	private static Citas citasClinica;

	private static void insertarCita() {
		Cita cita;
		try {
			cita = new Cita(Consola.leerCita());
			citasClinica.insertar(cita);
		} catch (OperationNotSupportedException e) {

			e.printStackTrace();
		}
	}

	private static void buscarCita() {
		LocalDateTime fecha;
		Cita citaABuscar, citaEncontrada;

		try {
			Paciente pacienteEjemplo = new Paciente("Ejemplo Ejemplo Ejemplo", "11223344b", "950999999");
			fecha = Consola.leerFechaHora();// podria estar fuera del try
			citaABuscar = new Cita(pacienteEjemplo, fecha);
			if (citasClinica.buscar(citaABuscar) == null) {
				System.out.println("No se ha encontrado la cita");
			} else {
				System.out.println(citasClinica.buscar(citaABuscar).toString());
			}
		} catch (NullPointerException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void borrarCita() {
		LocalDateTime fecha;
		Cita citaABorrar, citaEncontrada;
		try {
			Paciente pacienteEjemplo = new Paciente("Ejemplo Ejemplo Ejemplo", "11223344b", "950999999");
			fecha = Consola.leerFechaHora();// podria estar fuera del try
			citaABorrar = new Cita(pacienteEjemplo, fecha);
			if (citasClinica.buscar(citaABorrar) == null) {
				System.out.println("No se ha encontrado la cita");
			} else {
				citaEncontrada = new Cita(citasClinica.buscar(citaABorrar));
				citasClinica.borrar(citaEncontrada);
			}
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarCitas() {
		int i;
		boolean coincidencias = false;

		Cita[] citasAMostrar = citasClinica.getCitas();

		for (i = 0; i < citasClinica.getTamano(); i++) {
			System.out.println(citasAMostrar[i].toString());
			coincidencias = true;
		}
		if (coincidencias == false) {
			System.out.println("No hay citas almacenadas");
		}

	}
}
