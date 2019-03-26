package logica;

public class Jurado extends Persona {
	private String area;

	public Jurado(String cedula, String nombre, String telefono, String direccion, String sexo, String gradoAcademico, String area) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
