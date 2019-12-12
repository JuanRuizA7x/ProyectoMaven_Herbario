package org.persistenciaHerbario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.persistenciaHerbario.Usuario;

/**
 * Entity implementation class for Entity: Recolector
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Recolector.LISTAR_TODO_RECOLECTOR, query = "SELECT r FROM Recolector r"),
		@NamedQuery(name = Recolector.LISTAR_POR_ID_RECOLECTOR, query = "SELECT r FROM Recolector r WHERE r.cedula = :cedula"),
		@NamedQuery(name = Recolector.BUSCAR_RECOLECTOR_EMAIL, query = "SELECT r FROM Recolector r WHERE r.email = :email") })
public class Recolector extends Usuario implements Serializable {

	/**
	 * referencia para listar los Recolector
	 */
	public static final String LISTAR_TODO_RECOLECTOR = "ListarLosRecolector";
	/**
	 * referencia para buscar por id de Recolector
	 */
	public static final String LISTAR_POR_ID_RECOLECTOR = "ListarRecolectorPorId";
	/**
	 * referencia para buscar por id de Recolector
	 */
	public static final String BUSCAR_RECOLECTOR_EMAIL = "BuscarRecolectorPorEmail";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Recolector() {

	}

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasenia
	 * @param activo
	 * @param creacion
	 */
	public Recolector(String cedula, String nombre, String apellido, String email, String contrasenia, Boolean activo,
			Date creacion) {
		super(cedula, nombre, apellido, email, contrasenia, activo, creacion);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Recolector [getCedula()=" + getCedula() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getActivo()=" + getActivo() + ", getEmail()=" + getEmail() + ", getContrasenia()="
				+ getContrasenia() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
