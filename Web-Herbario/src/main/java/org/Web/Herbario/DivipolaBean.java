/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.persistenciaHerbario.Divipola;

import com.system.engineer.ejb.RegistroEJB;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("divipolaBean")
@ApplicationScoped
public class DivipolaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Divipola divipola;
	private List<Divipola> divipolas;

	@EJB
	private RegistroEJB registroEJB;

	@PostConstruct
	public void init() {
		divipola = new Divipola();
	}
	
	
	public String registrar() {
		try {
			registroEJB.insertarDivipola(divipola);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
					"Registro exitoso");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listo";
		} catch (ElementoRepetidoException e) {
			// e.printStackTrace();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}
	}

	/**
	 * @return the divipola
	 */
	public Divipola getDivipola() {
		return divipola;
	}

	/**
	 * @param divipola the divipola to set
	 */
	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
	}

	/**
	 * @return the divipolas
	 */
	public List<Divipola> getDivipolas() {
		divipolas = registroEJB.listarDivipolas();
		return divipolas;
	}
	
	

}