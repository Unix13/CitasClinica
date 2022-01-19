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

	public static Opciones elegirOpcion() {
		int opcionMenu;
		Opciones opcion = null;
		do {
			System.out.println("Introduce la opción a realizar (0-5) para volver a visualizar el menú pulse 6");
			System.out.print("Opción: ");
			opcionMenu = org.iesalandalus.programacion.utilidades.Entrada.entero();
			if (opcionMenu == 6) {
				mostrarMenu();
			}
		} while (opcionMenu < 0 || opcionMenu > 5);
		// no se indica opcionMenu>6 porque saldria del bucle do-while en el caso de
		// volver a mostrar el menu de opciones, no se habría selecionado ninguna opcion
		// válida en el switch a continuación
		switch (opcionMenu) {
		case 0:
			opcion = Opciones.SALIR;
			break;
		case 1:
			opcion = Opciones.INSERTAR_CITA;
			break;
		case 2:
			opcion = Opciones.BUSCAR_CITA;
			break;
		case 3:
			opcion = Opciones.BORRAR_CITA;
			break;
		case 4:
			opcion = Opciones.MOSTRAR_CITAS;
			break;
		case 5:
			opcion = Opciones.MOSTRAR_CITAS_DIA;
			break;
		}
		return opcion;
	}
	public static Paciente leerPaciente() {
		String nombre,dni,telefono;
		Paciente paciente = null;
		boolean error;
		do {
			error=false;
			System.out.println("Introduzca el nombre junto los apellidos del paciente");
			System.out.print("Nombre:");
			nombre=Entrada.cadena();
			System.out.println("Introduzca el dni del paciente");
			System.out.print("DNI:");
			dni=Entrada.cadena();
			System.out.println("Introduzca el telefono del paciente");
			System.out.print("Teléfono:");
			telefono=Entrada.cadena();
			try
			{
				paciente=new Paciente(nombre,dni,telefono);
			}
			catch(NullPointerException | IllegalArgumentException e)
			{
				System.out.println(e.getMessage());
				error=true;
			}
		}while(error);
		
		return paciente;
	}
	

}
