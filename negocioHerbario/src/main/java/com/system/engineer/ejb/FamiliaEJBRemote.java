package com.system.engineer.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * @author Shonny
 *
 */
@Remote
public interface FamiliaEJBRemote {

	public static final String JNDI = "java:global/earHerbario/negocioHerbario/FamiliaEJB!com.system.engineer.ejb.FamiliaEJBRemote";

	/**
	 * Permite agregar una familia a la base de datos
	 * 
	 * @param familia
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Familia insertarFamilia(Familia familia) throws ElementoRepetidoException;

	/**
	 * Permite modificar una familia en la base de datos
	 * 
	 * @param fam
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Familia modificarFamilia(Familia fam) throws ElementoRepetidoException;

	/**
	 * Permite desactivar una familia por medio de su nombre
	 * 
	 * @param nombre de la familia
	 * @return familia elminada
	 * @throws ElementoNoEncontradoException si la persona no es encontrada
	 */
	Familia eliminarFamilia(String nombre) throws ElementoNoEncontradoException;

	/**
	 * Permite mostrar todas las familias registradas
	 * 
	 * @return lista de familias
	 */
	List<Familia> listarFamilias();
	
	/**
	 * Devuelve la familia con el nombre señalado
	 * @param nombre
	 * @return
	 */
	Familia buscarFamiliaPorNombre(String nombre);
	
	/**
	 * Devuelve la familia con el nombre señalado
	 * @param nombre
	 * @return
	 */
	Familia buscarFamiliaPorId(Integer id);
	
	/**
	 * Permite agregar un género a la base de datos
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Genero insertarGenero(Genero genero) throws ElementoRepetidoException;
	
	/**
	 * Permite modificar un género en la base de datos
	 * 
	 * @param gen
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Genero modificarGenero(Genero gen) throws ElementoRepetidoException;
	
	/**
	 * Permite desactivar un genero por medio de su nombre
	 * 
	 * @param id de la familia
	 * @return familia elminada
	 * @throws ElementoNoEncontradoException si la persona no es encontrada
	 */
	Genero eliminarGenero(String nombre) throws ElementoNoEncontradoException;
	
	/**
	 * Permite mostrar todos los generos registrados
	 * @return lista de generos
	 */
	List<Genero> listarGeneros();

	/**
	 * Devuelve el genero con el nombre señalado
	 * @param nombre
	 * @return
	 */
	Genero buscarGeneroPorNombre(String nombre);
	
}