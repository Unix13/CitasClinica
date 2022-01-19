package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("");
		System.out.println("1.- Insertar cita");
		System.out.println("2.- Buscar cita");
		System.out.println("3.- Borrar cita");
		System.out.println("4.- Mostrar todas las citas");
		System.out.println("5.- Mostrar las citas de una fecha");
		System.out.println("6.- Volver a monstrar el menu de opciones");
		System.out.println("");
		System.out.println("0.- Salir.");
		System.out.println("");
	}

}
