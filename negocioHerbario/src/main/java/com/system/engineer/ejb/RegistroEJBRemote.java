package com.system.engineer.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.persistenciaHerbario.Divipola;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Registro;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@Remote
public interface RegistroEJBRemote {

	public static final String JNDI = "java:global/earHerbario/negocioHerbario/RegistroEJB!com.system.engineer.ejb.RegistroEJBRemote";

	/**
	 * Permite agregar un registro a la base de datos
	 * 
	 * @param registro
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Registro insertarRegistro(Registro registro) throws ElementoNoEncontradoException;

	/**
	 * Permite modificar un registro en la base de datos
	 * 
	 * @param reg
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Registro modificarRegistro(Registro reg) throws ElementoRepetidoException;
	
	/**
	 * Permite modificar una especie en la base de datos
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Especie modificarEspecie(Especie especie) throws ElementoRepetidoException;

	/**
	 * Permite aceptar un registro 
	 * 
	 * @param registro
	 * @return registro aceptado
	 * @throws ElementoNoEncontradoExceptio
	 */
	Registro aceptarRegistro(Registro registro) throws ElementoNoEncontradoException;
	
	/**
	 * Permite rechazar un registro 
	 * 
	 * @param registro
	 * @return registro rechazado
	 * @throws ElementoNoEncontradoException
	 */
	Registro rechazarRegistro(Registro registro) throws ElementoNoEncontradoException;

	/**
	 * Permite mostrar todos los registros existentes
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistros();
	
	/**
	 * Permite mostrar los registros aceptados
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosAceptados();
	
	/**
	 * Permite mostrar los registros aceptados por usuario
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosAceptadosPorUsuario(String cedula);
	
	/**
	 * Permite mostrar los registros rechazados por usuario
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosRechazadosPorUsuario(String cedula);
	
	/**
	 * Permite mostrar los registros pendientes por usuario
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosPendientesPorUsuario(String cedula);
	
	/**
	 * Permite mostrar los registros rechazados
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosRechazados();
	
	/**
	 * Permite mostrar los registros pendientes
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosPendientes();
	
	/**
	 * Permite mostrar los registros por genero
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosPorGenero(Genero gen);
	
	/**
	 * Permite mostrar los registros por familia
	 * 
	 * @return lista de registros
	 */
	List<Registro> listarRegistrosPorFamilia(Familia fam);
	
	/**
	 * Devuelve el registro con el id señalado
	 * @param nombre
	 * @return
	 */
	Registro buscarRegistroPorId(Integer id);
	
	/**
	 * Devuelve el registro con el nombre de la especie señalada
	 * @param nombre
	 * @return
	 */
	Registro buscarRegistroPorNombre(String nombre);
	
	/**
	 * Permite agregar una especie a la base de datos
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Especie insertarEspecie(Especie especie) throws ElementoRepetidoException;
	
	/**
	 * Permite mostrar todas las especies registradas
	 * @return lista de especies
	 */
	List<Especie> listarEspecies();

	/**
	 * Devuelve la especie con el nombre señalado
	 * 
	 * @param nombre
	 * @return
	 */
	Especie buscarEspeciePorNombre(String nombre);
	
	/**
	 * Permite agregar una divipola a la base de datos
	 * 
	 * @param divipola
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Divipola insertarDivipola(Divipola divipola) throws ElementoRepetidoException;
	
	/**
	 * Permite modificar una divipola en la base de datos
	 * 
	 * @param divipola
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Divipola modificarDivipola(Divipola divipola) throws ElementoRepetidoException;
	
	/**
	 * Permite mostrar todas las divipolas registradas
	 * @return lista de divipolas
	 */
	List<Divipola> listarDivipolas();
	
	/**
	 * Devuelve la divipola con el parametro señalado
	 * 
	 * @param departamento
	 * @param municipio
	 * @return
	 */
	Divipola buscarDivipolaPorMuniDep(String departamento, String municipio);
	
}