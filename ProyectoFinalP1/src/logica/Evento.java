package logica;

import java.util.ArrayList;
import java.util.Date;

public class Evento {

	private Comision laComision;
	private String tipoEvento;
	private Date fechaEvento;
	private String recursos[];
	private String areaEvento;
	private int cantAsistentes;
	private Proyecto elProyecto;
	private String nombreEvento;
	private String identificador;
	
	public Evento(Comision laComision, String tipoEvento, Date fechaEvento, String[] recursos, String areaEvento,
			int cantAsistentes, Proyecto elProyecto, String nombreEvento, String identificador) {
		super();
		this.laComision = laComision;
		this.tipoEvento = tipoEvento;
		this.fechaEvento = fechaEvento;
		this.recursos = recursos;
		this.areaEvento = areaEvento;
		this.cantAsistentes = cantAsistentes;
		this.elProyecto = elProyecto;
		this.nombreEvento = nombreEvento;
		this.identificador = identificador;
	}

	public Comision getLaComision() {
		return laComision;
	}

	public void setLaComision(Comision laComision) {
		this.laComision = laComision;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String[] getRecursos() {
		return recursos;
	}

	public void setRecursos(String[] recursos) {
		this.recursos = recursos;
	}

	public String getAreaEvento() {
		return areaEvento;
	}

	public void setAreaEvento(String areaEvento) {
		this.areaEvento = areaEvento;
	}

	public int getCantAsistentes() {
		return cantAsistentes;
	}

	public void setCantAsistentes(int cantAsistentes) {
		this.cantAsistentes = cantAsistentes;
	}

	public Proyecto getElProyecto() {
		return elProyecto;
	}

	public void setElProyecto(Proyecto elProyecto) {
		this.elProyecto = elProyecto;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	
}
