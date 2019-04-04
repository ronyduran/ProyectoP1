package logica;

import java.util.ArrayList;
import java.util.Date;

public class Evento {

	private ArrayList<Comision> lasComisiones;
	private String tipoEvento;
	private Date fechaEvento;
	private ArrayList<String> recursos;
	private int cantAsistentes;
	private ArrayList<Trabajo> losTrabajos;
	private String nombreEvento;
	private String identificador;
	private boolean estado;
	private String justificacion;
	
	
	public Evento(String tipoEvento, Date fechaEvento, ArrayList<String> recursos,String nombreEvento, String identificador) {
		
		super();
		this.lasComisiones = new ArrayList();
		this.tipoEvento = tipoEvento;
		this.fechaEvento = fechaEvento;
		this.recursos = recursos;
		this.cantAsistentes = cantAsistentes;
		this.losTrabajos = new ArrayList();
		this.nombreEvento = nombreEvento;
		this.identificador = identificador;
		this.estado = true;
		this.setJustificacion(null);
	}


	public ArrayList<Comision> getLasComisiones() {
		return lasComisiones;
	}


	public String getTipoEvento() {
		return tipoEvento;
	}


	public Date getFechaEvento() {
		return fechaEvento;
	}


	public ArrayList<String> getRecursos() {
		return recursos;
	}


	public int getCantAsistentes() {
		return cantAsistentes;
	}


	public ArrayList<Trabajo> getLosTrabajos() {
		return losTrabajos;
	}


	public String getNombreEvento() {
		return nombreEvento;
	}


	public String getIdentificador() {
		return identificador;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setLasComisiones(ArrayList<Comision> lasComisiones) {
		this.lasComisiones = lasComisiones;
	}


	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}


	public void setRecursos(ArrayList<String> recursos) {
		this.recursos = recursos;
	}


	public void setCantAsistentes(int cantAsistentes) {
		this.cantAsistentes = cantAsistentes;
	}


	public void setLosTrabajos(ArrayList<Trabajo> losTrabajos) {
		this.losTrabajos = losTrabajos;
	}


	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public String getJustificacion() {
		return justificacion;
	}


	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
	public void insertarTrabajo(Trabajo t1) {
		
		losTrabajos.add(t1);
	}
	
	public void comprobarfecha() {
		Date actual=new Date();
		if(actual.getTime()>fechaEvento.getTime()) {
			estado=false;
		}
		
	}
	
public void insertarComision(Comision c1) {
		
		lasComisiones.add(c1);
	}



	
	
}
