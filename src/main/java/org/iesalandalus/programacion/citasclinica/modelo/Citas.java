package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

public class Citas {

	private Cita[] coleccionCitas;
	private int capacidad;
	private int tamano;

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	
	private boolean tamanoSuperado(int nuevoTamano) {
		boolean superado = false;
		if (nuevoTamano >= tamano) {
			superado = true;
		}
		return superado;
	}

	private boolean capacidadSuperada(int nuevoTamano) {
		boolean superado = false;
		if (nuevoTamano >= capacidad) {
			superado = true;
		}
		return superado;
	}

	private int buscarIndice(Cita cita) {
		int indice = tamano + 1;
		int i;
		for (i = 0; i < tamano; i++) {
			if (coleccionCitas[i].equals(cita)) {

				indice = i;
			}
		}
		return indice;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		if (buscar(cita) !=null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}
		

		if (capacidadSuperada(tamano) == true) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		} else {
			coleccionCitas[tamano] = new Cita(cita);
			System.out.println("Cita introducida correctamente.");
			tamano++;
			
		}
	}

	public Cita buscar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede buscar una cita nula.");
		}
		int indice;
		Cita citaEncontrada = null;
		indice = buscarIndice(cita);
		if (indice != tamano + 1) {
			citaEncontrada = new Cita(coleccionCitas[indice]);
		}

		return citaEncontrada;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = 0; i < tamano; i++) {
			if (i > indice) {
				coleccionCitas[i - 1] = coleccionCitas[i];
			}
		}
		coleccionCitas[i] = null;
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		int indice;
		indice = buscarIndice(cita);
		if (indice != tamano + 1) {

			desplazarUnaPosicionHaciaIzquierda(indice);
			System.out.println("Cita borrada correctamente.");
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}
	}
	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		Cita[] coleccionCitasFecha = new Cita[tamano];

		int i, j = 0;
		for (i = 0; i < tamano; i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {

				coleccionCitasFecha[j] = new Cita(coleccionCitas[i]);
				j++;
			}
		}
		return coleccionCitasFecha;
	}
	public Citas(int capacidad){
		if(capacidad<=0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionCitas = new Cita[capacidad];
		this.capacidad=capacidad;
		this.tamano=0;
		
	}
}
