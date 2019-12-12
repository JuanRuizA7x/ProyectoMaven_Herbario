/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;

import com.system.engineer.ejb.AdminEJB;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("recolectorBean")
@ApplicationScoped
public class RecolectorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Recolector recolector;
	private List<Recolector> recolectores;
	
	/**
	 * Referencia al EJB de negocio para admin
	 */
	@EJB
	private AdminEJB adminEJB;
	
	@PostConstruct
	public void init() {
		recolector= new Recolector();
		recolector.setActivo(true);
	}
	
	public String registrar() {
		try {
			recolector.setCreacion(new Date());
			adminEJB.insertarRecolector(recolector);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "Listo";
		} catch (ElementoRepetidoException e) {
			// e.printStackTrace();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	
	/**
	 * Permite editar los datos de un recolector
	 * 
	 * @param id
	 * @return
	 */
	public String editar(String cedula) {
		Recolector reco = null;
		String redireccion = "";
		try {
			reco = adminEJB.buscarRecolectorId(cedula);
			if (reco != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("recole", reco);
				redireccion = "editar_recolector?faces-redirect=true";
			}
			return redireccion;
		} catch (ElementoRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Permite activar o desactivar un recolector
	 * 
	 * @param id
	 * @return
	 */
	public void cambiarEstado(String cedula) {
		try {
			adminEJB.eliminarRecolector(cedula);
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
	}
	
	public String actualizar(Recolector reco) {
		try {
			adminEJB.modificarRecolector(reco);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Recolector actualizado correctamente",
					"Recolector actualizado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listar_recolector?faces-redirect=true";
		} catch (ElementoRepetidoException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}
	

	/**
	 * @return the recolectores
	 */
	public List<Recolector> getRecolectores() {
		recolectores = adminEJB.listarRecolector();
		return recolectores;
	}

	/**
	 * @return the recolector
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Recolector recolector) {
		this.recolector = recolector;
	}
	
	

}
