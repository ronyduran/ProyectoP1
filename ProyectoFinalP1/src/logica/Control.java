package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Control implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<User> misUsers;
	private static Control control;
	private static User loginUser;
	private static boolean firstTime;
	
	private Control() {
		misUsers = new ArrayList<>();
	}
	
	public static Control getInstance(){
		if(control == null){
			control = new Control();
		}
		return control;
	}

	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}

	public static Control getControl() {
		return control;
	}

	public static void setControl(Control control) {
		Control.control = control;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Control.loginUser = loginUser;
	}

	public void regUser(User user) {
		misUsers.add(user);
		
	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Control.firstTime = firstTime;
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

	public boolean confirmLogin(String usuario, String contrase�a) {
		boolean login = false;
		for (User user : misUsers) {
			if(user.getUserName().equals(usuario) && user.getPass().equals(contrase�a) ){
				loginUser = user;
				login = true;
			}
		}
		return login;
	}

}