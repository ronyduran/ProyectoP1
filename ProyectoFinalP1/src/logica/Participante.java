package logica;

import java.util.ArrayList;

public class Participante extends Persona {
	
	ArrayList<Proyecto> losProyectos;

	public Participante(String cedula, String nombre, String telefono, String direccion, String sexo,
			String gradoAcademico, ArrayList<Proyecto> losProyectos) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		this.losProyectos = new ArrayList();
	}

	public ArrayList<Proyecto> getLosProyectos() {
		return losProyectos;
	}

	public void setLosProyectos(ArrayList<Proyecto> losProyectos) {
		this.losProyectos = losProyectos;
	}

}
