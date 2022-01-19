package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

public class Citas {
	
	private Cita [] coleccionCitas;
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
	
	public Cita[] getCitas(LocalDate fecha) {
		if (fecha==null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un d�a nulo.");
		}
		Cita[] coleccionCitasFecha= new Cita[tamano];
	
		int i,j=0;
		for (i=0; i<tamano;i++) {
			if(coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)){
			
				coleccionCitasFecha[j]= new Cita(coleccionCitas[i]);
				j++;
			}
		}
		return coleccionCitasFecha;
	}
	private boolean tamanoSuperado (int nuevoTamano) {
		boolean superado=false;
		if (nuevoTamano>=tamano) {
			superado=true;
		}
		return superado;
	}
	
	
	private boolean capacidadSuperada(int nuevoTamano) {
		boolean superado=false;
		if (nuevoTamano>=capacidad) {
			superado=true;
		}
		return superado;
	}
	private int buscarIndice(Cita cita) {
		int indice=tamano+1; //se inicializa a tamano+1 para el caso en que no se encuentre indice.
		int i;
		for (i=0; i<tamano;i++) {
			if (coleccionCitas[i].equals(cita)){ 
				//solo se comparan las fechas obviando los nombres.
				//se utiliza el m�todo equals para el array ya que es un array de objetos
				indice=i;
			}
		}
		return indice ;
	}
	public void insertar(Cita cita) throws OperationNotSupportedException{
		if (cita==null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		//Buscamos si ya existe la cita.
		if(buscar(cita)!=null) {//es decir s� ha encontrado una cita ya existente. Recuerda que tamano+1 indica que no se ha encontrado
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}
		
		
		if (capacidadSuperada(tamano)==true) {
			throw new OperationNotSupportedException("ERROR: No se aceptan m�s citas.");
		}else {
			coleccionCitas[tamano]=new Cita(cita); //a�ade una cita justo despues de la �ltima almacenada
			System.out.println("Cita introducida correctamente.");
			tamano++;
		}		
	}
}
