/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Usuario;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("permisosBean")
@ApplicationScoped
public class PermisosBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String permiso;
	
	/**
	 * verifica si hay una sesion en el login, si la hay, redirecciona al index
	 * si no la hay, se queda en el login
	 */
	public void verificarSesionLogin() {
		try {
			FacesContext context =FacesContext.getCurrentInstance();
			Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(us!=null) {
				context.getExternalContext().redirect("index.xhtml");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * verifica si hay una sesion, si no la hay, redirecciona al index
	 * si la hay, se queda en la pagina
	 */
	public void verificarSesion() {
		try {
			FacesContext context =FacesContext.getCurrentInstance();
			Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(us==null) {
				context.getExternalContext().redirect("index.xhtml");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * verificar si es admin
	 */
	public void verificarSesionAdmin() {
		try {
			FacesContext context =FacesContext.getCurrentInstance();
			Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(us==null) {
				context.getExternalContext().redirect("index.xhtml");
			}else if(!(us.getClass().equals(Administrador.class))) {
				context.getExternalContext().redirect("index.xhtml");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * verificar si es admin y Empleado
	 */
	public void verificarSesionAdminEmpleado() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(us==null) {
				context.getExternalContext().redirect("index.xhtml");
			}else if(!(us.getClass().equals(Administrador.class) || us.getClass().equals(Empleado.class))) {
				context.getExternalContext().redirect("index.xhtml");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * verifica si hay una sesion en el login, si la hay, redirecciona al index
	 * si no la hay, se queda en el login
	 */
	public void nombrePermiso() {
		permiso=null;
		try {
			FacesContext context =FacesContext.getCurrentInstance();
			Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if(us!=null) {
				//ejemplo org.persistenciaHerbario.Administrador
				permiso= us.getClass().getName().substring(25);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * @return the permiso
	 */
	public String getPermiso() {
		return permiso;
	}

}
