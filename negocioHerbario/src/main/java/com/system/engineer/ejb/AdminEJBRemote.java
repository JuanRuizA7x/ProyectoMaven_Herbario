package com.system.engineer.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@Remote
public interface AdminEJBRemote {

	String JNDI = "java:global/earHerbario/negocioHerbario/AdminEJB!com.system.engineer.ejb.AdminEJBRemote";

	/**
	 * Permite agregar una persona en la base de datos
	 * 
	 * @param empleado empleado a insetar
	 * @return deveuelve el empleado insertado o null
	 * @throws ElementoRepetidoException cuando hay informacion repetida
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException;

	/**
	 * Permite eliminar una persona según por medio de su cedula
	 * 
	 * @param cedula cedula de la persona
	 * @return persona elminada
	 * @throws ElementoNoEncontradoException si la persona no es encontrada
	 */
	Usuario eliminarPersona(String cedula) throws ElementoNoEncontradoException;

	/**
	 * Permite mostrar todos loe empleados registrado
	 * 
	 * @return lista de empleado
	 */
	List<Empleado> listarEmpleados();

	Empleado modificarEmpleado(Empleado emp) throws ElementoRepetidoException;

	/**
	 * Permite agregar una persona en la base de datos
	 * 
	 * @param empleado empleado a insetar
	 * @return deveuelve el empleado insertado o null
	 * @throws ElementoRepetidoException cuando hay informacion repetida
	 */
	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException;

	Recolector modificarRecolector(Recolector recolector) throws ElementoRepetidoException;

	/**
	 * Permite mostrar todos loe empleados registrado
	 * 
	 * @return lista de empleado
	 */
	List<Recolector> listarRecolector();

	Usuario eliminarRecolector(String cedula) throws ElementoNoEncontradoException;

	Usuario buscarUsuarioId(String cedula) throws ElementoNoEncontradoException;
}
