package org.Web.Herbario;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

/**
 * Este bean se encagarga de mostrar un saludo en pantalla
 * 
 * @author EinerZG
 * @version 1.0
 */

@FacesConfig(version = Version.JSF_2_3)
@Named("holaMundoBean")
@ApplicationScoped
public class HolaMundoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * mensaje a mostrar en pantalla
	 */
	private String mensaje;

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		mensaje = "Hola mundo";
		return mensaje;
	}

}
