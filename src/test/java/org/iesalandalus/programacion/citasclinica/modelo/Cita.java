package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {

	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente;

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}

		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}
	public Cita(Paciente paciente,LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}
	
	public Cita(Cita cita) {
		if (cita==null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		setPaciente(new Paciente(cita.getPaciente()));
		setFechaHora(cita.getFechaHora());
	}
	
}
