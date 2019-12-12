package org.pruebaHerbario;

import java.util.Date;

import javax.ejb.EJB;

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
import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.ejb.AdminEJB;
import com.system.engineer.ejb.exepciones.ElementoRepetidoException;

@RunWith(Arquillian.class)
public class TestAdminEJB {

	@EJB
	private AdminEJB adminEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class)

				.addPackage(Usuario.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * Permite realizar pruebas a insertar empleado en la base de datos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json" })
	public void insertarEmpleadoTest() {
		Empleado empleado = new Empleado("123456789", "rambo", "Garcia", "rambo@rambo.com", "123456789", true, new Date(), 77777.0);

		try {
			Assert.assertNotNull(adminEJB.insertarEmpleado(empleado));
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}
	
	/**
	 * Permite modificar empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json" })
	public void modificarEmpleadoTest() {
		Empleado em = new Empleado("22222", "Homero",  "Campo", "Homero@homero.com", "123456789", true, new Date(), 9999999.0);
		
		try {
			Assert.assertNotNull(adminEJB.modificarEmpleado(em));
			//System.out.println(adminEJB.modificarEmpleado(em).getNombre());
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());
		}catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
		
	}
	/**
	 * Permite buscar empleado por la cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json" })
	public void buscarEmpleadoId() {
		try {
			Assert.assertNotNull(adminEJB.buscarEmpleadoId("22222"));
			//System.out.println(adminEJB.buscarEmpleadoId("22222").getNombre());
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());
		}catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Permite realizar pruebas a insertar empleado en la base de datos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json" })
	public void insertarAdministradorTest() {
		
		Administrador administrador= new Administrador("5556666", "Arnold", "Villas", "arnold@arnold.com", "123456789", true, new Date(), 12342134.0);

		try {
			Assert.assertNotNull(adminEJB.insertarAdministrador(administrador));
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}
	
	
	/**
	 * Permite realizar pruebas a insertar recolector en la base de datos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json" })
	public void insertarRecolectorTest() {
		Recolector recolector = new Recolector("999988888", "Sandra", "Rios", "sandra@sandra.com", "123456789", true, new Date());
	

		try {
			Assert.assertNotNull(adminEJB.insertarRecolector(recolector));
		} catch (ElementoRepetidoException e) {
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

}
