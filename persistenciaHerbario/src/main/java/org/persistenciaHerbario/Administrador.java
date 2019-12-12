package org.persistenciaHerbario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Administrador.LISTAR_TODO_ADMIN, query = "SELECT a FROM Administrador a"),
		@NamedQuery(name = Administrador.LISTAR_POR_ID_ADMIN, query = "SELECT a FROM Administrador a WHERE a.cedula = :cedula"),
		@NamedQuery(name = Administrador.BUSCAR_ADMIN_EMAIL, query = "SELECT a FROM Administrador a WHERE a.email = :email"),
		@NamedQuery(name = Administrador.CONTAR_ADMINISTRADOR, query = "SELECT COUNT(a) FROM Administrador a") })
public class Administrador extends Empleado implements Serializable {

	/**
	 * referencia para listar los usuario
	 */
	public static final String LISTAR_TODO_ADMIN = "ListarLosAdministradores";
	/**
	 * referencia para listar por usuario
	 */
	public static final String LISTAR_POR_ID_ADMIN = "ListarAdminPorId";
	/**
	 * Buscar admin por cedula
	 */
	public static final String BUSCAR_ADMIN_EMAIL= "BuscarAdminEmail";
	/**
	 * referencia para listar por usuario
	 */
	public static final String CONTAR_ADMINISTRADOR = "ContarAdministrador";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Administrador() {
		super();
	}

	/**
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasenia
	 * @param activo
	 * @param creacion
	 * @param salario
	 */
	public Administrador(String cedula, String nombre, String apellido, String email, String contrasenia,
			Boolean activo, Date creacion, Double salario) {
		super(cedula, nombre, apellido, email, contrasenia, activo, creacion, salario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Administrador [toString()=" + super.toString() + "]";
	}

}
