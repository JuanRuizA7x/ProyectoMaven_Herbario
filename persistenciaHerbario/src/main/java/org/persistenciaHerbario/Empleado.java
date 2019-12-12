package org.persistenciaHerbario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.persistenciaHerbario.Usuario;

/**
 * Entity implementation class for Entity: Empleado
 * 
 * @author Shonny
 */
@Entity
@NamedQueries({ @NamedQuery(name = Empleado.LISTAR_TODO_EMPLEADO, query = "SELECT e FROM Empleado e"),
		@NamedQuery(name = Empleado.LISTAR_POR_ID_EMPLEADO, query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula"),
		@NamedQuery(name = Empleado.LISTAR_POR_EMAIL_EMPLEADO, query = "SELECT e FROM Empleado e WHERE e.email = :email") })
public class Empleado extends Usuario implements Serializable {

	/**
	 * referencia para listar los usuario
	 */
	public static final String LISTAR_TODO_EMPLEADO = "ListarLosEmpleados";
	/**
	 * referencia para listar por usuario
	 */
	public static final String LISTAR_POR_ID_EMPLEADO = "ListarEmpleadosPorId";
	/**
	 * referencia para listar por usuario
	 */
	public static final String LISTAR_POR_EMAIL_EMPLEADO = "ListarEmpleadosPorEmail";

	/**
	 * 
	 */
	@Column(name = "salario", length = 10)
	private Double salario;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Empleado() {

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
	public Empleado(String cedula, String nombre, String apellido, String email, String contrasenia, Boolean activo,
			Date creacion, Double salario) {
		super(cedula, nombre, apellido, email, contrasenia, activo, creacion);
		this.salario = salario;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the salario
	 */
	public Double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Empleado [salario=" + salario + ", toString()=" + super.toString() + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

}
