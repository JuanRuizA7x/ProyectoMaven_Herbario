/**
 * 
 */
package com.system.engineer.modelo;

import javafx.beans.property.StringProperty;

/**
 * @author Shonny
 *
 */
public class LoginObservable {
	
	
	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty contrasenia;
	
	/**
	 * @param cedula
	 * @param contrasenia
	 */
	public LoginObservable(StringProperty cedula, StringProperty contrasenia) {
		this.cedula = cedula;
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the contrasenia
	 */
	public StringProperty getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(StringProperty contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
