package com.system.engineer.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * Se encarga de todas las transaciones que realiza la Familia
 * 
 * @author Shonny
 */
@Stateless
@LocalBean
public class FamiliaEJB implements FamiliaEJBRemote {

	/**
	 * medio por el cual se realizan las transaciones
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public FamiliaEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método que inserta una familia
	 * 
	 * @param familia
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Familia insertarFamilia(Familia familia) throws ElementoRepetidoException {
		Familia fami = buscarFamiliaPorNombre(familia.getNombre());
		if (fami != null) {
			throw new ElementoRepetidoException("La Familia con nombre " + familia.getNombre() + " ya esta registrada");
		}
		try {
			entityManager.persist(familia);
			return familia;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar la familia", e.getMessage()));
			return null;
		}
	}

	/**
	 * Método que modifica una familia
	 * 
	 * @param fam
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Familia modificarFamilia(Familia familia) throws ElementoRepetidoException {
		Familia familiaBD = buscarFamiliaPorNombre(familia.getNombre());
		if (familiaBD != null && !familiaBD.equals(familia)) {
			throw new ElementoRepetidoException("La Familia con nombre " + familia.getNombre() + " ya esta registrada");
		}
		try {
			familiaBD = familia;
			entityManager.merge(familiaBD);
			return familiaBD;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar la familia", e.getMessage()));
			return null;
		}

	}

	/**
	 * Permite desactivar una familia
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Familia eliminarFamilia(String nombre) throws ElementoNoEncontradoException {

		Familia familia = buscarFamiliaPorNombre(nombre);
		if (familia == null) {
			throw new ElementoNoEncontradoException("Familia no encontrada en los registros");
		} else {
			familia.setActivo(false);
			entityManager.merge(familia);
			List<Genero> generos = listarGeneros();
			for (Genero genero : generos) {
				if (genero.getFamilia().getId().equals(buscarFamiliaPorNombre(nombre).getId())) {
					eliminarGenero(genero.getNombre());
				}
			}
			return familia;
		}
	}

	/**
	 * Permite desactivar una familia
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Familia cambiarEstadoFamilia(String nombre) throws ElementoNoEncontradoException {

		Familia familia = buscarFamiliaPorNombre(nombre);
		if (familia == null) {
			throw new ElementoNoEncontradoException("Familia no encontrada en los registros");
		} else {
			if(familia.getActivo()) {
				familia.setActivo(false);
			}else {
				familia.setActivo(true);
			}
			entityManager.merge(familia);
			return familia;
		}
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	public List<Familia> listarFamilias() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_TODO_FAMILIA, Familia.class);
		List<Familia> familias = query.getResultList();
		return familias;
	}

	/**
	 * Permite buscar una Familia por nombre
	 * 
	 * @param nombre
	 * @return Familia con el nombre indicado
	 */
	@Override
	public Familia buscarFamiliaPorNombre(String nombre) {
		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.OBTENER_NOMBRE_FAMILIA, Familia.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Buscar empleado por cedula
	 * 
	 * @throws ElementoRepetidoException
	 */
	public Familia buscarFamiliaPorId(Integer id) {
		try {
			Familia fami = entityManager.find(Familia.class, id);
			return fami;
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método que inserta un genero
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero insertarGenero(Genero genero) throws ElementoRepetidoException {
		Genero gen = buscarGeneroPorNombre(genero.getNombre());
		if (gen != null && !gen.equals(genero)) {
			throw new ElementoRepetidoException("El genero con nombre " + genero.getNombre() + " ya esta registrada");
		}
		try {
			genero = entityManager.merge(genero);
			return genero;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar el género", e.getMessage()));
			return null;
		}
	}

	/**
	 * Método que modifica un genero
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero modificarGenero(Genero genero) throws ElementoRepetidoException {
		Genero generoBD = buscarGeneroPorNombre(genero.getNombre());
		if (generoBD != null && !generoBD.equals(genero)) {
			throw new ElementoRepetidoException("El género con nombre " + genero.getNombre() + " ya está registrado");
		}
		try {
			generoBD = genero;
			System.out.println(generoBD.getId());
			entityManager.merge(generoBD);
			return generoBD;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar el género", e.getMessage()));
			return null;
		}

	}

	/**
	 * Permite desactivar un genero
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero eliminarGenero(String nombre) throws ElementoNoEncontradoException {

		Genero genero = buscarGeneroPorNombre(nombre);
		if (genero == null) {
			throw new ElementoNoEncontradoException("Familia no encontrada en los registros");
		} else {
			genero.setActivo(false);
			entityManager.merge(genero);
			return genero;
		}
	}
	
	/**
	 * Permite desactivar un genero
	 * 
	 * @param nombre
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero cambiarEstadoGenero(String nombre) throws ElementoNoEncontradoException {

		Genero genero = buscarGeneroPorNombre(nombre);
		if (genero == null) {
			throw new ElementoNoEncontradoException("Genero no encontrado en los registros");
		} else {
			if(genero.getActivo()) {
				genero.setActivo(false);
			}else {
				genero.setActivo(true);
			}
			entityManager.merge(genero);
			return genero;
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	public List<Genero> listarGeneros() {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_TODO_GENERO, Genero.class);
		query.setParameter("activo",true);
		
		List<Genero> generos = query.getResultList();
		return generos;
	}

	/**
	 * Permite buscar un genero por nombre
	 * 
	 * @param nombre
	 * @return Genero con el nombre indicado
	 */
	@Override
	public Genero buscarGeneroPorNombre(String nombre) {
		try {
			TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.OBTENER_NOMBRE_GENERO, Genero.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}