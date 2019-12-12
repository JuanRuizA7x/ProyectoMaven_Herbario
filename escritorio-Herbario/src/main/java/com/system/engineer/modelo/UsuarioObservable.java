package com.system.engineer.modelo;

import java.util.Date;

import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Shonny
 *
 */
public class UsuarioObservable {

	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * email observable de un empleado
	 */
	private StringProperty email;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty contrasenia;
	/**
	 * activo observable de un empleado
	 */
	private SimpleBooleanProperty activo;
	/**
	 * activo observable de un empleado
	 */
	private ObjectProperty<Date> creacion;
	/**
	 * empleado asociado
	 */
	private Usuario usuario;
	private Object tipo;

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasenia
	 * @param activo
	 * @param creacion
	 * @param usuario
	 */
	public UsuarioObservable(Usuario usuario, Object tipo) {
		this.usuario = usuario;
		this.tipo = tipo;
		this.cedula = new SimpleStringProperty(usuario.getCedula());
		this.nombre = new SimpleStringProperty(usuario.getNombre());
		this.apellido = new SimpleStringProperty(usuario.getApellido());
		this.email = new SimpleStringProperty(usuario.getEmail());
		this.contrasenia = new SimpleStringProperty(usuario.getContrasenia());
		this.activo = new SimpleBooleanProperty(usuario.getActivo());
		this.creacion = new SimpleObjectProperty<>(usuario.getCreacion());
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
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(StringProperty email) {
		this.email = email;
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

	/**
	 * @return the activo
	 */
	public SimpleBooleanProperty getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(SimpleBooleanProperty activo) {
		this.activo = activo;
	}

	/**
	 * @return the creacion
	 */
	public ObjectProperty<Date> getCreacion() {
		return creacion;
	}

	/**
	 * @param creacion the creacion to set
	 */
	public void setCreacion(ObjectProperty<Date> creacion) {
		this.creacion = creacion;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the tipo
	 */
	public Object getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Object tipo) {
		this.tipo = tipo;
	}

}
