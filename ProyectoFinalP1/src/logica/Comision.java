package logica;

import java.util.ArrayList;
import java.util.Date;

public class Comision {

	private ArrayList<Jurado> misJurados;
	private String area;
	private String codigo;
	private Date fechaCreacion;
	private Jurado presidente;
	
	public Comision(ArrayList<Jurado> misJurados, String area, String codigo, Date fechaCreacion, Jurado presidente) {
		super();
		this.misJurados = misJurados;
		this.area = area;
		this.codigo = codigo;
		this.fechaCreacion = fechaCreacion;
		this.presidente = presidente;
	}

	public ArrayList<Jurado> getMisJurados() {
		return misJurados;
	}

	public void setMisJurados(ArrayList<Jurado> misJurados) {
		this.misJurados = misJurados;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Jurado getPresidente() {
		return presidente;
	}

	public void setPresidente(Jurado presidente) {
		this.presidente = presidente;
	}

	
	
	
	
}
