package logica;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.w3c.dom.ls.LSOutput;

public class PlanificacionEvento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Comision> lasComisiones;
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Evento> losEventos;
	private ArrayList<Trabajo> losTrabajos;
	private ArrayList<String> recursos;
	private static PlanificacionEvento laPlanificacion;
	private int codTrabjo;
	private int codComision;
	private int codEvento;
	private static User loginUser;
	private static boolean firstTime;
	private ArrayList<User> misUsers;
	
	
	private  PlanificacionEvento() {
		super();
		this.lasComisiones = new ArrayList();
		this.lasPersonas = new ArrayList();
		this.losEventos = new ArrayList();
		this.losTrabajos = new ArrayList();
		this.recursos =new ArrayList(Arrays.asList("Local","Proyector","Mesas","Sillas", "Microfonos y Bocinas","Podium","Computador"));
		this.codTrabjo=1;
		this.codComision=1;
		this.setCodEvento(1);
		misUsers = new ArrayList<>();
		
		
		
	}
	
	public static PlanificacionEvento getInstance() { 
		
		if (laPlanificacion== null) {
			laPlanificacion= new PlanificacionEvento();
			
		}

		return laPlanificacion;
	}
	
	
	public static PlanificacionEvento getLaPlanificacion() {
		return laPlanificacion;
	}

	public static void setLaPlanificacion(PlanificacionEvento laPlanificacion) {
		PlanificacionEvento.laPlanificacion = laPlanificacion;
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

	public ArrayList<Trabajo> getLosTrabajos() {
		return losTrabajos;
	}

	public ArrayList<String> getRecursos() {
		return recursos;
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

	public void setLosProyectos(ArrayList<Trabajo> losTrabajos) {
		this.losTrabajos = losTrabajos;
	}

	public void setRecursos(ArrayList<String> recursos) {
		this.recursos = recursos;
	}

	public int getCodTrabjo() {
		return codTrabjo;
	}

	public void setCodTrabjo(int codTrabjo) {
		this.codTrabjo = codTrabjo;
	}

	public int getCodComision() {
		return codComision;
	}

	public void setCodComision(int codComision) {
		this.codComision = codComision;
	}

	public int getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(int codEvento) {
		this.codEvento = codEvento;
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void insertarPersona(Persona p1) {
		
		lasPersonas.add(p1);
	}
	
	public void insertarTrabajo(Trabajo pr1) {
		
		losTrabajos.add(pr1);
		BuscarComisionPorCodigo(pr1.getLaComision().getCodigo()).insertarTrabajo(pr1);
		BuscarEventoCodigo(pr1.getElEvento().getIdentificador()).insertarTrabajo(pr1);
		((Participante)buscarPersonaPorCedula(pr1.getElParticipante().getCedula())).insertarTrabajo(pr1);
	}
	
	public void insertarComision(Comision c1) {
		
		lasComisiones.add(c1);
		BuscarEventoCodigo(c1.getElEvento().getIdentificador()).insertarComision(c1);
	}
	
	public void insertarEvento(Evento e1) {
		
		losEventos.add(e1);
	}
	public void insertarRecuso(String recurso) {
		
		recursos.add(recurso);
	}
	
	public Persona buscarPersonaPorCedula(String cedula) {
		
		Persona per=null;
		for (Persona aux : lasPersonas) {
			if(aux.getCedula().equalsIgnoreCase(cedula)) {
				per=aux;
			}
		}
		return per;		
	}
	
	public Comision BuscarComisionPorCodigo (String Codigo) {
		Comision lasComi= null;
		
		for (Comision aux : lasComisiones) {
			if(aux.getCodigo().equalsIgnoreCase(Codigo)) {
				
				lasComi=aux;
			}
		}
		
		return lasComi;
	}
	
	public Evento BuscarEventoCodigo (String Codigo) {
		Evento elEvento= null;
		
		for (Evento aux : losEventos) {
			if(aux.getIdentificador().equalsIgnoreCase(Codigo)) {
				
				elEvento=aux;
			}
		}
		
		return elEvento;
	}
	
	public Trabajo  BuscarTrabajoPorCodigo (String Codigo) {
		Trabajo elTrabajo= null;
		
		for (Trabajo aux : losTrabajos) {
			if(aux.getIdentificador().equalsIgnoreCase(Codigo)) {
				
				elTrabajo=aux;
			}
		}
		
		return elTrabajo;
	}
	
	public void ActualizarParticipante(String cedula, Persona p1) {
	
		for (Trabajo auxTrabajo : losTrabajos) {
			if(auxTrabajo.getElParticipante().getCedula().equalsIgnoreCase(cedula)) {
				
				auxTrabajo.setElParticipante(p1);
					
			}
			for (Evento auxEvento : losEventos) {
				for (int i = 0; i < auxEvento.getLosTrabajos().size(); i++) {
				
					if(auxEvento.getLosTrabajos().get(i).getIdentificador().equalsIgnoreCase(auxTrabajo.getIdentificador())) {
						auxEvento.getLosTrabajos().get(i).setElParticipante(p1);
						
					}
				}
			}
		
		}

	}


	public void ActualizarJurado(String cedula, Persona p1) {
	
		for (Comision auxComision : lasComisiones) {
			if(auxComision.getPresidente().getCedula().equalsIgnoreCase(cedula)) {
				
				auxComision.setPresidente((Jurado)p1);
					
			}
			for (int i = 0; i < auxComision.getMisJurados().size(); i++) {
				
				if(auxComision.getMisJurados().get(i).getCedula().equalsIgnoreCase(cedula)) {
					
					auxComision.getMisJurados().set(i, (Jurado)p1);
				}
				
			}
			for (Evento auxEvento : losEventos) {
				for (int i = 0; i < auxEvento.getLasComisiones().size(); i++) {
				
					if(auxEvento.getLasComisiones().get(i).getCodigo().equalsIgnoreCase(auxComision.getCodigo())) {
						auxEvento.getLasComisiones().set(i, auxComision);
						
					}
				}
			}
		
		}

	}
	
	
	public void ActualizarTrabajo(Trabajo t1) {
		
		
			for (Evento auxEvento : losEventos) {
				for (int i = 0; i < auxEvento.getLosTrabajos().size(); i++) {
				
					if(auxEvento.getLosTrabajos().get(i).getIdentificador().equalsIgnoreCase(t1.getIdentificador())) {
						auxEvento.getLosTrabajos().set(i, t1);
						
					}
				}
			}
			
			for (Persona auxPersona : lasPersonas) {
				if(auxPersona instanceof Participante) {
				for (int i = 0; i < ((Participante)auxPersona).getLosTrabajos().size(); i++) {
					if(((Participante)auxPersona).getLosTrabajos().get(i).getIdentificador().equalsIgnoreCase(t1.getIdentificador())) {
						
						((Participante)auxPersona).getLosTrabajos().set(i, t1);
					}
				}
			}}
			
			for (Comision auxComision : lasComisiones) {
				for (int i = 0; i < auxComision.getLosTrabajos().size(); i++) {
					
					if(auxComision.getLosTrabajos().get(i).getIdentificador().equalsIgnoreCase(t1.getIdentificador())) {
						auxComision.getLosTrabajos().set(i, t1);
						
					}
				}
				
				
			}
			
			
			
	}
		
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		PlanificacionEvento.loginUser = loginUser;
	}

	public void regUser(User user) {
		misUsers.add(user);
		
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		PlanificacionEvento.firstTime = firstTime;
	}
	
	public boolean BuscarUsuarioPorNombre(String NombreUsuario) {
		boolean encontrado =false;
		
		for (User aux : misUsers) {
			if(aux.getUserName().equalsIgnoreCase(NombreUsuario)) {
				encontrado=true;
			}
		}
		
		return encontrado;
		
		
	}

	public boolean confirmLogin(String usuario, String contraseña) {
		boolean login = false;
		for (User user : misUsers) {
			if(user.getUserName().equals(usuario) && user.getPass().equals(contraseña) ){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	public int[] buscarGeneroPorEvento(String Codigo) {
		
		int cantHombre=0;
		int cantMujer=0;
		Evento aux = BuscarEventoCodigo(Codigo);
		System.out.println(aux.getIdentificador());
		if(aux!=null) {
		for (int i = 0; i < aux.getLosTrabajos().size(); i++) {
			if (aux.getLosTrabajos().get(i).getElParticipante().getSexo().equalsIgnoreCase("Hombre")) {
				cantHombre++;
			}
			if (aux.getLosTrabajos().get(i).getElParticipante().getSexo().equalsIgnoreCase("Mujer")) {
				cantMujer++;
			}
		}}
		System.out.println(cantHombre);
		System.out.println(cantMujer);

		int [] genero = {cantHombre, cantMujer};
		
		return genero;
	}

	public int[] buscarGradoPorEvento(String Codigo) {
		
		int cantBachiller=0;
		int cantLicenciado=0;
		int cantMagi=0;
		int cantDoctorado=0;
		Evento aux = BuscarEventoCodigo(Codigo);
		for (int i = 0; i < aux.getLosTrabajos().size(); i++) {
			if (aux.getLosTrabajos().get(i).getElParticipante().getGradoAcademico().equalsIgnoreCase("Bachiller")) {
				cantBachiller++;
			}
			if (aux.getLosTrabajos().get(i).getElParticipante().getGradoAcademico().equalsIgnoreCase("Licenciado")) {
				cantLicenciado++;
			}
			if (aux.getLosTrabajos().get(i).getElParticipante().getGradoAcademico().equalsIgnoreCase("Mag\\u00EDster")) {
				cantMagi++;
			}
			if (aux.getLosTrabajos().get(i).getElParticipante().getGradoAcademico().equalsIgnoreCase("Doctorado")) {
				cantDoctorado++;
			}
		}
		
		int [] grado = {cantBachiller, cantLicenciado, cantMagi, cantDoctorado};
		
		return grado;
	}

	



	
}
