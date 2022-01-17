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

	private String formateaNombre(String nombre) {
		String nombreFormat, nombreResultado;
		nombreResultado = "";
		String palabraMayus, palabraFormateada = null;
		char primerCaracter;
		int inicioPalabra = 0, finPalabra;
		int posicionUltimoEspacio = 0;

		nombreFormat = nombre.toLowerCase().trim();
		String[] palabras = nombreFormat.split("//s+");

		do {
			inicioPalabra = posicionUltimoEspacio;
			finPalabra = palabras[0].indexOf(" ", inicioPalabra);
			posicionUltimoEspacio = finPalabra + 1;

			while (palabras[0].substring(posicionUltimoEspacio, posicionUltimoEspacio + 1).trim().isEmpty()) {
				posicionUltimoEspacio++;
			}
			if (finPalabra == -1) {
				finPalabra = palabras[0].length();
			}

			palabraMayus = palabras[0].substring(inicioPalabra, finPalabra).toUpperCase();
			primerCaracter = palabraMayus.charAt(0);
			palabraFormateada = primerCaracter + palabras[0].substring(inicioPalabra + 1, finPalabra);
			nombreResultado = nombreResultado + palabraFormateada;
			if (finPalabra != palabras[0].length()) {
				nombreResultado += " ";
			}
		} while (finPalabra != palabras[0].length());
		return nombreResultado;
	}

	private boolean comprobarLetraDni(String dni) {
		if (dni == null || dni.trim().isEmpty())
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");

		char[] LETRAS_DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		Pattern patron = null;
		Matcher comparador;
		patron = Pattern.compile(ER_DNI);
		comparador = patron.matcher(dni);
		boolean dniCorrecto = false;
		char letra;
		int numerosDni;

		if (!comparador.matches()) {
			return false;
		}

		try {
			numerosDni = Integer.parseInt(comparador.group(1));
			
		} catch (NumberFormatException e)
		
		{
			numerosDni = 0;
		}

		letra = LETRAS_DNI[numerosDni % 23];

		if (comparador.group(2).charAt(0) == letra) {
			
			dniCorrecto = true;
		} else {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		return dniCorrecto;
	}
	
	private String getIniciales(){
		String iniciales="";
		
		String [] palabras=nombre.split("\\s+");
		
		for(int i=0;i<palabras.length;i++)
		{
			iniciales=iniciales+palabras[i].charAt(0);
		}
		
		return iniciales;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ("nombre=" + nombre + " (" + getIniciales().toUpperCase() + ")" + ", DNI=" + dni + ", teléfono=" + telefono);
	}
}
