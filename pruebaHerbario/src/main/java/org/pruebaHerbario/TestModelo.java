package org.pruebaHerbario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Divipola;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Opciones;
import org.persistenciaHerbario.Persona;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Registro;
import org.persistenciaHerbario.Usuario;

/**
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author EinerZG
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestModelo {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	// --------------------------RECOLECTOR------------------------------------
	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarRecolectorTest() {
		Recolector recolector = new Recolector("55555", "cathe", "valderrama", "9999@9999.com", "12345", true, new Date());
		entityManager.persist(recolector);
		Recolector r = entityManager.find(Recolector.class, recolector.getCedula());
		Assert.assertNotNull(r);

	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarRecolectorTest() {
		Recolector r = entityManager.find(Recolector.class, "33333");
		Assert.assertNotNull(r);
		entityManager.remove(r);
		r = entityManager.find(Recolector.class, "33333");
		Assert.assertNull(r);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarRecolectorTest() {
		Recolector r = entityManager.find(Recolector.class, "33333");
		Assert.assertNotNull(r);
		r.setNombre("Ramon");
		r = entityManager.find(Recolector.class, "33333");
		Assert.assertNotNull(r);

	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarRecolectorTest() {
		Recolector r = entityManager.find(Recolector.class, "33333");
		Assert.assertNotNull(r);
	}

	// --------------------------EMPLEADO------------------------------------
	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarEmpleadoTest() {
		Empleado empleado = new Empleado("66666", "cesar", "valderrama", "9999@9999.com", "12345", true, new Date(), 1000000.0);
		entityManager.persist(empleado);
		Empleado e = entityManager.find(Empleado.class, empleado.getCedula());
		Assert.assertNotNull(e);

	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarEmpleadoTest() {
		Empleado e = entityManager.find(Empleado.class, "22222");
		Assert.assertNotNull(e);
		entityManager.remove(e);
		e = entityManager.find(Empleado.class, "22222");
		Assert.assertNull(e);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarEmpleadoTest() {
		Empleado e = entityManager.find(Empleado.class, "22222");
		Assert.assertNotNull(e);
		e.setNombre("Ramona");
		e = entityManager.find(Empleado.class, "22222");
		Assert.assertNotNull(e);

	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarEmpleadoTest() {
		Empleado e = entityManager.find(Empleado.class, "22222");
		Assert.assertNotNull(e);
	}

	// --------------------------ADMINISTRADOR------------------------------------
	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarAdministradorTest() {
		Administrador administrador = new Administrador("77777", "jhoan", "marin", "9999@9999.com", "12345", true, new Date(), 2000000.0);
		entityManager.persist(administrador);
		Administrador a = entityManager.find(Administrador.class, administrador.getCedula());
		Assert.assertNotNull(a);

	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarAdministradorTest() {
		Administrador a = entityManager.find(Administrador.class, "12345");
		Assert.assertNotNull(a);
		entityManager.remove(a);
		a = entityManager.find(Administrador.class, "12345");
		Assert.assertNull(a);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarAdministradorTest() {
		Administrador a = entityManager.find(Administrador.class, "12345");
		Assert.assertNotNull(a);
		a.setNombre("Walter");
		Administrador ad = entityManager.find(Administrador.class, "12345");
		Assert.assertNotNull(ad);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "usuarios.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarAdministradorTest() {
		Empleado a = entityManager.find(Administrador.class, "12345");
		Assert.assertNotNull(a);
	}

	// --------------------------FAMILIA------------------------------------
	/**
	 * @throws ParseException
	 * Metodo para agregar una familia
	 */
//	@Test
//	@UsingDataSet({ "familias.json", "generos.json" })
//	@Transactional(value = TransactionMode.ROLLBACK)
//	public void insertarFamiliaTest() throws ParseException {
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2019-04-19");
//		Genero genero = entityManager.find(Genero.class, "1");
//		List<Genero> generos = new ArrayList<Genero>();
//		generos.add(genero);
//		Familia familia = new Familia("4", "Asteraceae",
//				"reunen mas de 23.500 especies repartidas en unos 1600 generos", true, createAt);
//		entityManager.persist(familia);
//		Familia f = entityManager.find(Familia.class, familia.getId());
//		Assert.assertNotNull(f);
//	}

	/**
	 * Metodo para eliminar una familia
	 */
	@Test
	@UsingDataSet({ "familias.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarFamiliaTest() {
		Familia f = entityManager.find(Familia.class, "1");
		Assert.assertNotNull(f);
		entityManager.remove(f);
		f = entityManager.find(Familia.class, "1");
		Assert.assertNull(f);
	}

	/**
	 * Metodo para actualizar una familia
	 */
	@Test
	@UsingDataSet({ "familias.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarFamiliaTest() {
		Familia f = entityManager.find(Familia.class, "2");
		Assert.assertNotNull(f);
		f.setNombre("Poaceae");
		f = entityManager.find(Familia.class, "2");
		Assert.assertNotNull(f);
	}

	/**
	 * Metodo para buscar una familia
	 */
	@Test
	@UsingDataSet({ "familias.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarFamiliaTest() {
		Familia f = entityManager.find(Familia.class, "3");
		Assert.assertNotNull(f);
	}

	// --------------------------GENERO------------------------------------
	/**
	 * @throws ParseException
	 * Metodo para agregar un Genero
	 */
//	@Test
//	@UsingDataSet({ "familias.json", "generos.json", "especies.json" })
//	@Transactional(value = TransactionMode.ROLLBACK)
//	public void insertarGeneroTest() throws ParseException {
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2019-03-18");
//		Especie especie = entityManager.find(Especie.class, "1");
//		List<Especie> especies = new ArrayList<Especie>();
//		Familia familia = entityManager.find(Familia.class, "3");
//		especies.add(especie);
//		Genero genero = new Genero("4", "Sedum", "Consta de 48 generos y mas de 1.300 especies en todo el mundo", true,
//				familia, createAt);
//		entityManager.persist(genero);
//		Genero g = entityManager.find(Genero.class, genero.getId());
//		Assert.assertNotNull(g);
//	}

	/**
	 * Metodo para eliminar un Genero
	 */
	@Test
	@UsingDataSet({ "generos.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarGeneroTest() {
		Genero g = entityManager.find(Genero.class, "1");
		Assert.assertNotNull(g);
		entityManager.remove(g);
		g = entityManager.find(Genero.class, "1");
		Assert.assertNull(g);
	}

	/**
	 * Metodo para actualizar un Genero
	 */
	@Test
	@UsingDataSet({ "generos.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarGeneroTest() {
		Genero g = entityManager.find(Genero.class, "2");
		Assert.assertNotNull(g);
		g.setNombre("Impatiens");
		g = entityManager.find(Genero.class, "2");
		Assert.assertNotNull(g);

	}

	/**
	 * Metodo para buscar un Genero
	 */
	@Test
	@UsingDataSet({ "generos.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarGeneroTest() {
		Genero g = entityManager.find(Genero.class, "3");
		Assert.assertNotNull(g);
	}

	// --------------------------DIVIPOLA------------------------------------
	/**
	 * Metodo para agregar una Divipola
	 */
	@Test
	@UsingDataSet({ "divipolas.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarDivipolaTest() {
		Divipola divipola = new Divipola("4", "Quindio", "Cordoba");
		entityManager.persist(divipola);
		Divipola d = entityManager.find(Divipola.class, divipola.getId());
		Assert.assertNotNull(d);
	}

	/**
	 * Metodo para eliminar una Divipola
	 */
	@Test
	@UsingDataSet({ "divipolas.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarDivipolaTest() {
		Divipola di = entityManager.find(Divipola.class, "1");
		Assert.assertNotNull(di);
		entityManager.remove(di);
		di = entityManager.find(Divipola.class, "1");
		Assert.assertNull(di);
	}

	/**
	 * Metodo para actualizar una Divipola
	 */
	@Test
	@UsingDataSet({ "divipolas.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarDivipolaTest() {
		Divipola di = entityManager.find(Divipola.class, "2");
		Assert.assertNotNull(di);
		di.setMunicipio("Circacia");
		di = entityManager.find(Divipola.class, "2");
		Assert.assertNotNull(di);
	}

	/**
	 * Metodo para buscar una Divipola
	 */
	@Test
	@UsingDataSet({ "divipolas.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarDivipolaTest() {
		Divipola di = entityManager.find(Divipola.class, "3");
		Assert.assertNotNull(di);
	}

	// --------------------------REGISTRO------------------------------------
	/**
	 * @throws ParseException
	 * 
	 */
//	@Test
//	@UsingDataSet({ "registros.json" })
//	@Transactional(value = TransactionMode.ROLLBACK)
//	public void insertarRegistroTest() throws ParseException {
//		Registro registro = null;
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2019-02-18");
//		Especie especie = entityManager.find(Especie.class, "1");
//		Usuario usuario = entityManager.find(Usuario.class, "12345");
//		Opciones opcionEnum = Opciones.valueOf("AGREGADA");
//		registro = new Registro("4", "Cualquier cosa", usuario, especie, opcionEnum, createAt);
//		entityManager.persist(registro);
//		Registro r = entityManager.find(Registro.class, registro.getId());
//		Assert.assertNotNull(r);
//	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "registros.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarRegistroTest() {
		Registro r = entityManager.find(Registro.class, "1");
		Assert.assertNotNull(r);
		entityManager.remove(r);
		r = entityManager.find(Registro.class, "1");
		Assert.assertNull(r);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "registros.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarRegistroTest() {
		Registro r = entityManager.find(Registro.class, "2");
		Assert.assertNotNull(r);
		r.setDescripcion("Nueva descripción");
		r = entityManager.find(Registro.class, "2");
		Assert.assertNotNull(r);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "registros.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarRegistroTest() {
		Registro r = entityManager.find(Registro.class, "3");
		Assert.assertNotNull(r);
	}

	// --------------------------ESPECIE------------------------------------
	//CORREGIR ESTA PRUEBA Y EL JSON OJO!!!!
	/**
	 * @throws ParseException
	 * 
	 */
//	@Test
//	@UsingDataSet({ "especies.json", "usuarios.json", "divipolas.json", "generos.json" })
//	@Transactional(value = TransactionMode.ROLLBACK)
//	public void insertarEspecieTest() throws ParseException {
//		Registro registro = null;
//		Especie especie = null;
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2019-02-18");
//		Date fechaRecoleccion = formato.parse("2019-02-15");
//		Genero genero = entityManager.find(Genero.class, "1");
//		Divipola divipola = entityManager.find(Divipola.class, "1");
//		Usuario usuario = entityManager.find(Usuario.class, "12345");
//		Opciones opcionEnum = Opciones.valueOf("AGREGADA");
//		registro = new Registro("4", "Cualquier cosa", usuario, especie, opcionEnum, createAt);
//		especie = new Especie("4", "Orquiea", "4°33′45″N", "4°33′45″N", "Planta de prueba", fechaRecoleccion,
//				genero, divipola, createAt, registro);
//		entityManager.persist(especie);
//		Especie e = entityManager.find(Especie.class, especie.getId());
//		Assert.assertNotNull(e);
//	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "especies.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarEspecieTest() {
		Especie e = entityManager.find(Especie.class, "1");
		Assert.assertNotNull(e);
		entityManager.remove(e);
		e = entityManager.find(Especie.class, "1");
		Assert.assertNull(e);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "especies.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void actualizarEspecieTest() {
		Especie e = entityManager.find(Especie.class, "2");
		Assert.assertNotNull(e);
		e.setNombre("Garbanzo");
		e = entityManager.find(Especie.class, "2");
		Assert.assertNotNull(e);
	}

	/**
	 * 
	 */
	@Test
	@UsingDataSet({ "especies.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void buscarEspecieTest() {
		Especie e = entityManager.find(Especie.class, "3");
		Assert.assertNotNull(e);
	}

}