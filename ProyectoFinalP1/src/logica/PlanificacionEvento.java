package logica;

import java.util.ArrayList;

import org.w3c.dom.ls.LSOutput;

public class PlanificacionEvento {
	
	private ArrayList<Comision> lasComisiones;
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Evento> losEventos;
	private ArrayList<Proyecto> losProyectos;
	private ArrayList<String> recursos;
	private static PlanificacionEvento laPlanificacion;
	
	private  PlanificacionEvento() {
		super();
		this.lasComisiones = new ArrayList();
		this.lasPersonas = new ArrayList();
		this.losEventos = new ArrayList();
		this.losProyectos = new ArrayList();
		this.recursos =new ArrayList();
	}
	
	public static PlanificacionEvento getInstance() { 
		
		if (laPlanificacion== null) {
			laPlanificacion= new PlanificacionEvento();
		}
		
		return laPlanificacion;
	}
	
	public ArrayList<Comision> getLasComisiones() {
		return lasComisiones;
	}

	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}

	public ArrayList<Evento> getLosEventos() {
		return losEventos;
	}

	public ArrayList<Proyecto> getLosProyectos() {
		return losProyectos;
	}

	public ArrayList<String> getRecursos() {
		return recursos;
	}

	public static PlanificacionEvento getLaPlanificacion() {
		return laPlanificacion;
	}

	public void setLasComisiones(ArrayList<Comision> lasComisiones) {
		this.lasComisiones = lasComisiones;
	}

	public void setLasPersonas(ArrayList<Persona> lasPersonas) {
		this.lasPersonas = lasPersonas;
	}

	public void setLosEventos(ArrayList<Evento> losEventos) {
		this.losEventos = losEventos;
	}

	public void setLosProyectos(ArrayList<Proyecto> losProyectos) {
		this.losProyectos = losProyectos;
	}

	public void setRecursos(ArrayList<String> recursos) {
		this.recursos = recursos;
	}

	public static void setLaPlanificacion(PlanificacionEvento laPlanificacion) {
		PlanificacionEvento.laPlanificacion = laPlanificacion;
	}

	public void insertarPersona(Persona p1) {
		
		lasPersonas.add(p1);
	}
	
	public void insertarProyecto(Proyecto pr1) {
		
		losProyectos.add(pr1);
	}
	
	public void insertarComision(Comision c1) {
		
		lasComisiones.add(c1);
	}
	
	public void insertarEvento(Evento e1) {
		
		losEventos.add(p1);
	}
	public void insertarRecuso(String recurso) {
		
		recursos.add(recurso);
	}
	
	public Persona buscarPersonaPorCedula(String cedula) {
		
		Persona per=null;
		for (Persona aux : lasPersonas) {
			if(aux.getCedula.equalsIgnoreCase(cedula)) {
				per=aux;
			}
		}
		return per;		
	}
	
	public Comision BuscarComunionesPorCodigo (String Codigo) {
		Comision lasComi= null;
		
		for (Comision aux : lasComisiones) {
			if(aux.getIdentificador.equalsIgnoreCase(Codigo)) {
				
				lasComi=aux;
			}
		}
		
		return lasComi;
	}
	
	
	public Proyecto BuscarProyectoPorIdentificador(String Identificador) {
		
		Proyecto proyec=null;
		
		for (Proyecto aux : losProyectos) {
			if(aux.getIdentificador().equalsIgnoreCase(Identificador)) {
				
				proyec=aux;
			}	
		}
		return proyec;
	}
	
	public Evento BuscarEventoPorProyecto(String nombreProy) {
		
		Evento event=null;
		
		for (Evento aux : losEventos) {
			if(aux.getElProyecto.getNombreProyecto.equalsIgnoreCase(nombreProy)) {
				
				event=aux;
			}	
		}
		return event;
	}
	
}
