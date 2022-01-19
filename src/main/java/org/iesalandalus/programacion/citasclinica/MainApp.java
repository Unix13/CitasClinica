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

	public static void main(String[] args) {
		System.out.println("Programa para gestionar las citas de la Clínica.");
		
		
		
		private static void insertarCita() {
			Cita cita;
			try {
				cita=new Cita(Consola.leerCita());
				citasClinica.insertar(cita);
			} catch (OperationNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

