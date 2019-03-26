package logica;

import java.util.ArrayList;

public class Proyecto {

	private String nombreProyecto;
	private String areaProyecto;
	private String descripcion;
	private ArrayList<Persona> losParticipantes;
	private Evento elEvento;
	private Comision laComision;
	private String identificador;
	
	public Proyecto(String nombreProyecto, String areaProyecto, String descripcion, ArrayList<Persona> losParticipantes,
			Evento elEvento, Comision laComision, String identificador) {
		super();
		this.nombreProyecto = nombreProyecto;
		this.areaProyecto = areaProyecto;
		this.descripcion = descripcion;
		this.losParticipantes = losParticipantes;
		this.elEvento = elEvento;
		this.laComision = laComision;
		this.identificador=identificador;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public String getAreaProyecto() {
		return areaProyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ArrayList<Persona> getLosParticipantes() {
		return losParticipantes;
	}

	public Evento getElEvento() {
		return elEvento;
	}

	public Comision getLaComision() {
		return laComision;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public void setAreaProyecto(String areaProyecto) {
		this.areaProyecto = areaProyecto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLosParticipantes(ArrayList<Persona> losParticipantes) {
		this.losParticipantes = losParticipantes;
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
	
	
}
