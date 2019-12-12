/**
 * 
 */
package org.Web.Herbario;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

/**
 * @author Shonny
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("cambioBean")
@ApplicationScoped
public class CambioBean implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String atributo1;
	private String atributo2;
	
	public CambioBean() {
		
	}
	
	public void cambiar() {
		String temp = atributo1;
		atributo1 = atributo2;
		atributo2 = temp;
	}
	
	/**
	 * @return the atributo1
	 */
	public String getAtributo1() {
		return atributo1;
	}
	/**
	 * @param atributo1 the atributo1 to set
	 */
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	/**
	 * @return the atributo2
	 */
	public String getAtributo2() {
		return atributo2;
	}
	/**
	 * @param atributo2 the atributo2 to set
	 */
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

}
