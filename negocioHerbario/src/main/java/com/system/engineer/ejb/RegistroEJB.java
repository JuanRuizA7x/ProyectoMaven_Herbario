package com.system.engineer.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Divipola;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Opciones;
import org.persistenciaHerbario.Registro;

import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

/**
 * Se encarga de todas las transaciones que realiza la Familia
 * 
 * @author Shonny
 */
@Stateless
@LocalBean
public class RegistroEJB implements RegistroEJBRemote {

	/**
	 * medio por el cual se realizan las transaciones
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public RegistroEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Método que inserta un registro
	 * 
	 * @param registro
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public Registro insertarRegistro(Registro registro) throws ElementoNoEncontradoException {
		if (registro == null) {
			throw new ElementoNoEncontradoException("El registro esta imcompleto");
		}
		try {
			Registro regis = registro;
			
			entityManager.persist(registro);
			return regis;
		} catch (Exception e) {
			System.err.println(String.format("Error al ingresar el registro", e.getMessage()));
			return null;
		}
	}

	/**
	 * Método que modifica un registro
	 * 
	 * @param fam
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Registro modificarRegistro(Registro registro) throws ElementoRepetidoException {
		Registro regis = registro;
		if (regis == null) {
			throw new ElementoRepetidoException("El registro no existe");
		}
		try {
			regis = registro;
			System.out.println(regis.getId() + "  fdasdf");
			entityManager.merge(regis);
			return regis;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar el registro", e.getMessage()));
			return null;
		}

	}
	
	/**
	 * Método que acepta un registro
	 * 
	 * @param registro
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	//@Override
	public Registro aceptarRegistro(Registro registro) throws ElementoNoEncontradoException {

		Registro regis = registro;
		if (regis == null) {
			throw new ElementoNoEncontradoException("Registro no encontrado en los registros");
		} else {
			Opciones opcion = null;
			regis.setOpciones(opcion.AGREGADA);
			entityManager.merge(regis);
			return regis;
		}
	}
	
	/**
	 * Método que rechaza un registro
	 * 
	 * @param registro
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	//@Override
	public Registro rechazarRegistro(Registro registro) throws ElementoNoEncontradoException {

		Registro regis = registro;
		if (regis == null) {
			throw new ElementoNoEncontradoException("Registro no encontrado en los registros");
		} else {
			Opciones opcion = null;
			regis.setOpciones(opcion.RECHAZADA);
			entityManager.merge(regis);
			return regis;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistros() {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_TODO_REGISTRO, Registro.class);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistrosAceptados() {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_OPCION_REGISTRO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("opciones", Opciones.AGREGADA);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/**
	 * Metodo para listar los registros aceptadas por usuario
	 */
	@Override
	public List<Registro> listarRegistrosAceptadosPorUsuario(String cedula) {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_USUARIO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("cedula", cedula);
		query.setParameter("opciones", Opciones.AGREGADA);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/**
	 * Metodo para listar los registros rechazadas por usuario
	 */
	@Override
	public List<Registro> listarRegistrosRechazadosPorUsuario(String cedula) {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_USUARIO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("cedula", cedula);
		query.setParameter("opciones", Opciones.RECHAZADA);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/**
	 * Metodo para listar los registros pendientes por usuario
	 */
	@Override
	public List<Registro> listarRegistrosPendientesPorUsuario(String cedula) {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_USUARIO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("cedula", cedula);
		query.setParameter("opciones", Opciones.PENDIENTE);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistrosRechazados() {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_OPCION_REGISTRO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("opciones", Opciones.RECHAZADA);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistrosPendientes() {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_OPCION_REGISTRO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("opciones", Opciones.PENDIENTE);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistrosPorGenero(Genero gen) {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_GENERO_REGISTRO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("genero", gen);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public List<Registro> listarRegistrosPorFamilia(Familia fam) {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.LISTAR_POR_FAMILIA_REGISTRO, Registro.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		query.setParameter("familia", fam);
		List<Registro> registros = query.getResultList();
		return registros;
	}
	
	/**
	 * Buscar registro por id
	 * 
	 * @throws ElementoRepetidoException
	 */
	public Registro buscarRegistroPorId(Integer id) {
		try {
			Registro regis = entityManager.find(Registro.class, id);
			return regis;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Permite buscar un registro por nombre de la especie asociada
	 * 
	 * @param nombre
	 * @return Registro con el nombre indicado
	 */
	@Override
	public Registro buscarRegistroPorNombre(String nombre) {
		try {
			TypedQuery<Registro> query = entityManager.createNamedQuery(Registro.OBTENER_NOMBRE_REGISTRO, Registro.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Método que inserta una especie
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Especie insertarEspecie(Especie especie) throws ElementoRepetidoException {
		Especie esp = buscarEspeciePorNombre(especie.getNombre());
		if (esp != null && !esp.equals(especie)) {
			throw new ElementoRepetidoException("El genero con nombre " + especie.getNombre() + " ya esta registrada");
		}
		try {
			entityManager.persist(especie);
			return especie;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar la especie", e.getMessage()));
			return null;
		}
	}
	
	/**
	 * Método que modifica una especie
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Especie modificarEspecie(Especie especie) throws ElementoRepetidoException {
		Especie especieBD = buscarEspeciePorNombre(especie.getNombre());
		if (especieBD != null && !especieBD.equals(especie)) {
			throw new ElementoRepetidoException("La especie con nombre " + especie.getNombre() + " ya está registrada");
		}
		try {
			especieBD = especie;
			System.out.println(especieBD.getId());
			entityManager.merge(especieBD);
			return especieBD;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar la especie", e.getMessage()));
			return null;
		}

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	public List<Especie> listarEspecies() {
		TypedQuery<Especie> query = entityManager.createNamedQuery(Especie.LISTAR_TODO_ESPECIE, Especie.class);
		query.setParameter("activoGen", true);
		query.setParameter("activoFam", true);
		List<Especie> especies = query.getResultList();
		return especies;
	}
	
	/**
	 * Permite buscar una especie por nombre
	 * 
	 * @param nombre
	 * @return Especie con el nombre indicado
	 */
	@Override
	public Especie buscarEspeciePorNombre(String nombre) {
		try {
			TypedQuery<Especie> query = entityManager.createNamedQuery(Especie.OBTENER_NOMBRE_ESPECIE, Especie.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Método que inserta una divipola
	 * 
	 * @param divipola
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Divipola insertarDivipola(Divipola divipola) throws ElementoRepetidoException {
		Divipola divi = buscarDivipolaPorMuniDep(divipola.getDepartamento(), divipola.getMunicipio());
		if (divi != null) {
			throw new ElementoRepetidoException("El municipio con nombre " + divipola.getMunicipio() + " ya esta registrado");
		}
		try {
			entityManager.persist(divipola);
			return divipola;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar el municipio", e.getMessage()));
			return null;
		}
	}
	
	/**
	 * Método que modifica una divipola
	 * 
	 * @param divipola
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Divipola modificarDivipola(Divipola divipola) throws ElementoRepetidoException {
		Divipola diviBD = buscarDivipolaPorMuniDep(divipola.getDepartamento(), divipola.getMunicipio());
		if (diviBD != null && !diviBD.equals(divipola)) {
			throw new ElementoRepetidoException("El municipio con nombre " + divipola.getMunicipio() + " ya esta registrado");
		}
		try {
			diviBD = divipola;
			System.out.println(diviBD.getId());
			entityManager.merge(diviBD);
			return diviBD;
		} catch (Exception e) {
			System.err.println(String.format("Error al actualizar la divipola", e.getMessage()));
			return null;
		}

	}
	
	public List<Divipola> listarDivipolas() {
		TypedQuery<Divipola> query = entityManager.createNamedQuery(Divipola.LISTAR_TODO_DIVIPOLA, Divipola.class);
		List<Divipola> divipolas = query.getResultList();
		return divipolas;
	}
	
	public Divipola buscarDivipolaPorMuniDep(String departamento, String municipio) {
		try {
			TypedQuery<Divipola> query = entityManager.createNamedQuery(Divipola.BUSCAR_MUNICIPIO, Divipola.class);
			query.setParameter("municipio", municipio);
			query.setParameter("departamento", departamento);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}