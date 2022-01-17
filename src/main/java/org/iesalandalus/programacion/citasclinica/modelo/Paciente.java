package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.OperationNotSupportedException;

public class Paciente {
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "([69])([0-9]{8})";
	private String nombre;
	private String dni;
	private String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}

		nombre = formateaNombre(nombre);
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		dni = dni.toUpperCase();
		if (comprobarLetraDni(dni)) {
			this.dni = dni;
		} else {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}
		Pattern patron;
		Matcher comparador;
		patron = Pattern.compile(ER_TELEFONO);
		comparador = patron.matcher(telefono);
		if (comparador.matches()) {
			this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
	}

	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		}
		setNombre(paciente.getNombre());
		setDni(paciente.getDni());
		setTelefono(paciente.getTelefono());
	}
}
