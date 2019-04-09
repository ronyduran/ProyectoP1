package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Trabajo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreTrabajo;
	private String areaTrabajo;
	private String descripcion;
	private Persona elParticipante;
	private Evento elEvento;
	private Comision laComision;
	private String identificador;
	
	public Trabajo(String nombreTrabajo, String areaTrabajo, String descripcion,
			Evento elEvento, Comision laComision, String identificador, Persona elParticipante) {
		super();
		this.nombreTrabajo = nombreTrabajo;
		this.areaTrabajo = areaTrabajo;
		this.descripcion = descripcion;
		this.elParticipante= elParticipante;
		this.elEvento = elEvento;
		this.laComision = laComision;
		this.identificador=identificador;
	}

	public String getNombreTrabajo() {
		return nombreTrabajo;
	}

	public String getAreaTrabajo() {
		return areaTrabajo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	

	public Evento getElEvento() {
		return elEvento;
	}

	public Comision getLaComision() {
		return laComision;
	}

	public void setNombreTrabajo(String nombreProyecto) {
		this.nombreTrabajo = nombreProyecto;
	}

	public void setAreaTrabajo(String areaProyecto) {
		this.areaTrabajo = areaProyecto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public void setElEvento(Evento elEvento) {
		this.elEvento = elEvento;
	}

	public void setLaComision(Comision laComision) {
		this.laComision = laComision;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Persona getElParticipante() {
		return elParticipante;
	}

	public void setElParticipante(Persona elParticipante) {
		this.elParticipante = elParticipante;
	}
	
	
}
