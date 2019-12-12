package org.pruebaHerbario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
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
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.FamiliaEJB;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

@RunWith(Arquillian.class)
public class TestFamiliaEJB {

	@EJB
	private FamiliaEJB familiaEJB;
	
	/**
	 * medio por el cual se realizan las transaciones
	 */
	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(FamiliaEJB.class)

				.addPackage(Usuario.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * Permite realizar pruebas a insertarFamilia en la base de datos
	 * 
	 * @throws ParseException
	 */


	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "familias.json" })
	//public void insertarFamiliaTest() throws ParseException {

	//	SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
	//	Date createAt = formato.parse("2012-04-19");
	//	Familia familia = new Familia("123", "Ebenaceae",
	//			"Familia de plantas arbóreas o arbustivas perteneciente al orden Ericales", true, createAt);

	//	try {
	//		Assert.assertNotNull(familiaEJB.insertarFamilia(familia));
	//	} catch (ElementoRepetidoException e) {
	//		Assert.fail(e.getMessage());
	//	} catch (Exception e) {
	//		Assert.fail(String.format("Error: %s", e.getMessage()));
	//	}
	//}

	/**
	 * Permite realizar pruebas a modificarFamilia en la base de datos
	 * 
	 * @throws ParseException
	 */
//	@Test
//	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "familias.json" })
//	public void modificarFamiliaTest() throws ParseException {
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2012-08-23");
//		Familia fam = new Familia("3", "Ebenaceae",
//				"forma un grupo de plantas de flor con alrededor de 25 géneros y más de 200 especies", true, createAt);
//
//		try {
//			Assert.assertNotNull(familiaEJB.modificarFamilia(fam));
//			// System.out.println(adminEJB.modificarEmpleado(em).getNombre());
//		} catch (ElementoRepetidoException e) {
//			Assert.fail(e.getMessage());
//		} catch (Exception e) {
//			Assert.fail(String.format("Error: %s", e.getMessage()));
//		}
//	}

	/**
	 * Permite realizar pruebas a insertarGenero en la base de datos
	 * @throws ParseException
	 */
//	@Test
//	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "familias.json", "generos.json" })
//	public void insertarGeneroTest() throws ParseException {
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2012-04-19");
//		Familia familia = entityManager.find(Familia.class, "3");
//		Genero genero = new Genero("1234", "Acranthera",
//				"Plantas con flores perteneciente a la familia de las rubiáceas. "
//				+ "Es originario de la India, sur de China y Malasia.", true, familia, createAt);
//
//		try {
//			Assert.assertNotNull(familiaEJB.insertarGenero(genero));
//		} catch (ElementoRepetidoException e) {
//			Assert.fail(e.getMessage());
//		} catch (Exception e) {
//			Assert.fail(String.format("Error: %s", e.getMessage()));
//		}
//	}
	
	/**
	 * Permite realizar pruebas a modificarGenero en la base de datos
	 * @throws ParseException
	 */
//	@Test
//	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "familias.json", "generos.json" })
//	public void modificarGeneroTest() throws ParseException {
//
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
//		Date createAt = formato.parse("2015-08-13");
//		Familia familia = entityManager.find(Familia.class, "3");
//		Genero genero = new Genero("2", "Geophila", "Género Geophila", true, familia, createAt);
//
//		try {
//			Assert.assertNotNull(familiaEJB.modificarGenero(genero));
//		} catch (ElementoRepetidoException e) {
//			Assert.fail(e.getMessage());
//		} catch (Exception e) {
//			Assert.fail(String.format("Error: %s", e.getMessage()));
//		}
//	}
}
