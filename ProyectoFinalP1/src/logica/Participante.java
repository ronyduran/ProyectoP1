package logica;

import java.io.Serializable;
import java.util.ArrayList;


public class Participante extends Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Trabajo> losTrabajos;

	public Participante(String cedula, String nombre, String telefono, String direccion, String sexo,
			String gradoAcademico) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		this.losTrabajos = new ArrayList();
	}

	public ArrayList<Trabajo> getLosTrabajos() {
		return losTrabajos;
	}

	public void setLosTrabajos(ArrayList<Trabajo> losProyectos) {
		this.losTrabajos = losProyectos;
	}
	
	public void insertarTrabajo(Trabajo t1) {
		
		losTrabajos.add(t1);
	}
	
	
}
