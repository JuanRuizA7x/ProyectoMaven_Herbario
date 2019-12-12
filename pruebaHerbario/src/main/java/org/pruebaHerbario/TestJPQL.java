package org.pruebaHerbario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
import org.persistenciaHerbario.Persona;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Registro;
import org.persistenciaHerbario.Usuario;

import DTO.ConsultaPunto10DTO;
import DTO.ConsultaPunto4DTO;

@RunWith(Arquillian.class)
public class TestJPQL {

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
	
	
	//---------------------------------USUARIOS-----------------------------------------
	
	/*
	 * Test para Listar todos los Usuarios
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void listarUsuariosTest() {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.LISTAR_TODO_USUARIO, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		Assert.assertEquals(4, usuarios.size());
	}
	
	/*
	 * Test para Listar Usuario por cedula
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void buscarPorCedulaUsuarioTest() {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.LISTAR_POR_ID_USUARIO, Usuario.class);
		query.setParameter("cedula", "22222");
		List<Usuario> usuarios = query.getResultList();
		Assert.assertEquals(1, usuarios.size());
	}
	
	//---------------------------------ADMINISTRADOR-----------------------------------------
	/*
	 * Test para Listar todos los Administradores
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void listarAdministradorTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_TODO_ADMIN, Administrador.class);
		List<Administrador> administradores = query.getResultList();
		Assert.assertEquals(1, administradores.size());
	}
	
	/*
	 * Test para Listar Administrador por cedula
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void buscarPorCedulaAdministradorTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_POR_ID_ADMIN, Administrador.class);
		query.setParameter("cedula", "12345");
		List<Administrador> administradores = query.getResultList();
		Assert.assertEquals(1, administradores.size());
	}
	
	//---------------------------------EMPLEADO-----------------------------------------
	/*
	 * Test para Listar todos los Empleados
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void listarEmpleadoTest() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_TODO_EMPLEADO, Empleado.class);
		List<Empleado> empleados = query.getResultList();
		Assert.assertEquals(2, empleados.size());

	}
	/*
	 * Test para Listar Empleados por cedula
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void buscarPorCedulaEmpleadoTest() {
		TypedQuery<Empleado> query  = entityManager.createNamedQuery(Empleado.LISTAR_POR_ID_EMPLEADO, Empleado.class);
		query.setParameter("cedula", "22222");
		List<Empleado> empleados = query.getResultList();
		Assert.assertEquals(1, empleados.size());
		
	}
	
	//---------------------------------RECOLECTOR-----------------------------------------
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void listarRecolectorTest() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_TODO_RECOLECTOR, Recolector.class);
		List<Recolector> recolectores = query.getResultList();
		Assert.assertEquals(2, recolectores.size());
	}
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"usuarios.json"})
	public void buscarPorCedulaRecolectorTest() {
		TypedQuery<Recolector> query  = entityManager.createNamedQuery(Recolector.LISTAR_POR_ID_RECOLECTOR, Recolector.class);
		query.setParameter("cedula", "33333");
		List<Recolector> recolectores = query.getResultList();
		Assert.assertEquals(1, recolectores.size());
		
	}
	
	
	//---------------------------------DEPARTAMENTO-----------------------------------------
	/**
	 * Test para Listar las especies por Departamento
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "especies.json", "divipolas.json" })
	//public void ListarEspeciesPorDepartamento() {
	//	TypedQuery<Especie> query = entityManager.createNamedQuery(Divipola.LISTAR_ESPECIES_DEPARTAMENTO,
	//			Especie.class);
	//	query.setParameter("departamento", "Quindio");
	//	List<Especie> especies = query.getResultList();
	//	Assert.assertEquals(6, especies.size());
	//}
	
	//---------------------------------MUNICIPIO-----------------------------------------
	/**
	 * Test para Listar las especies por Municipio
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "especies.json", "divipolas.json" })
	//public void ListarEspeciesPorMunicipio() {
	//	TypedQuery<Especie> query = entityManager.createNamedQuery(Divipola.LISTAR_ESPECIES_MINICIPIO,
	//			Especie.class);
	//	query.setParameter("municipio", "Calarca");
	//	List<Especie> especies = query.getResultList();

	//	Assert.assertEquals(1, especies.size());
	//}
	
	//---------------------------------FAMILIAS-----------------------------------------
	/**
	 * Test para Listar todas las familias
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"familias.json"})
	public void listarTodasLasFamiliasTest() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_TODO_FAMILIA, Familia.class);
		List<Familia> usuarios = query.getResultList();
		Assert.assertEquals(3, usuarios.size());
	}
	/**
	 * Test para familia por nombre
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"familias.json"})
	public void listarFamiliaPorNombre() {
		TypedQuery<Familia> query  = entityManager.createNamedQuery(Familia.LISTAR_POR_NOMBRE_FAMILIA, Familia.class);
		query.setParameter("nombre", "Rubiaceas");
		List<Familia> recolectores = query.getResultList();
		Assert.assertEquals(1, recolectores.size());
		
	}
	
	//---------------------------------GENEROS-----------------------------------------
	/**
	 * Test para Listar todos los generos
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"generos.json"})
	public void listarTodosLosGenerosTest() {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_TODO_GENERO, Genero.class);
		List<Genero> usuarios = query.getResultList();
		Assert.assertEquals(3, usuarios.size());
	}
	
	/**
	 * Test para listar genero por nombre
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet ({"generos.json"})
	public void listarGeneroPorNombre() {
		TypedQuery<Genero> query  = entityManager.createNamedQuery(Genero.LISTAR_GENERO_POR_NOMBRE, Genero.class);
		query.setParameter("nombre", "Leguminosas");
		List<Genero> recolectores = query.getResultList();
		Assert.assertEquals(1, recolectores.size());
		
	}
	
	/**
	 * Test para listar especies por Familia
	 */
	//@Test
	//@Transactional(value=TransactionMode.ROLLBACK)
	//@UsingDataSet ({"generos.json","especies.json", "familias.json"})
	//public void listarEspeciesPorFamilia() {
	//	TypedQuery<Especie> query  = entityManager.createNamedQuery(Familia.LISTAR_ESPECIES_POR_FAMILIA, Especie.class);
	//	query.setParameter("id", "2");
	//	List<Especie> especie = query.getResultList();
	//	Assert.assertEquals(1, especie.size());
	//}
	
	// -----------------------------TALLER-------------------------------------------
	/**
	 * Punto 4
	 * Test para listar las familias por especie
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familias.json", "especies.json", "generos.json" })
	public void ListarFamiliasPorEspecie() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Especie.LISTAR_FAMILIA_ESPECIE, Familia.class);
		query.setParameter("idEspecie", "1");
		List<Familia> familias = query.getResultList();

		Assert.assertEquals(1, familias.size());
	}
	
	/**
	 * Punto 5
	 * Test para listar especies por Genero
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "generos.json", "especies.json" })
	//public void listarEspeciesPorGenero() {
	//	TypedQuery<Especie> query = entityManager.createNamedQuery(Genero.LISTAR_ESPECIES_POR_GENERO, Especie.class);
	//	query.setParameter("id", "1");
	//	List<Especie> especie = query.getResultList();
	//	Assert.assertEquals(1, especie.size());

	//}
	
	/**
	 * Punto 6
	 * Test para listar registros por usuario
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "usuarios.json", "registros.json" })
	//public void listarRegistroPorUsuario() {
	//	TypedQuery<Registro> query = entityManager.createNamedQuery(Usuario.LISTAR_REGISTROS_USUARIO, Registro.class);
	//	query.setParameter("cedula", "22222");
	//	List<Registro> especie = query.getResultList();
	//	Assert.assertEquals(1, especie.size());

	//}
	/**
	 * Punto 7
	 * Test para listar todos los usuarios que hicieron y no hicieron un registro
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "usuarios.json", "registros.json" })
	//public void listarUsuarioPorRegistro() {
	//	TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNamedQuery(Registro.LISTAR_USUARIOS_POR_REGISTROS);
	//	List<Object[]> registros = query.getResultList();
	//	Assert.assertEquals(7, registros.size());
	//	Iterator it=registros.iterator();
	//	while(it.hasNext())
	//	{
	//		Object rows[] = (Object[])it.next();
	//		System.out.println(rows[0]+ " -- " +rows[1]);
	//	}

	//}
	
	/**
	 * Punto 8
	 *  Test para listar registros
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json", "registros.json" })
	public void listarRecolectoresRegistro() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Registro.LISTA_RECOLECTORES_REGISTRO, Recolector.class);
		List<Recolector> registros = query.getResultList();
		Assert.assertEquals(1, registros.size());
	}
	
	/**
	 * Punto 9
	 *  Test para listar recolectores por fecha
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json", "registros.json", "generos.json", "especies.json" })
	public void listarRecolectoresFecha() {
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNamedQuery(Registro.LISTA_REGISTRO_FECHA);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date creacion = formato.parse("2012-12-30 00:00:00");
			query.setParameter("creacionRegistro", creacion);
			List<Object[]> registros = query.getResultList();
			Assert.assertEquals(4, registros.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Punto 10
	 *  Test para listar recolectores por fecha dto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json", "registros.json", "generos.json", "especies.json" })
	public void listarRecolectoresFechaDTO() {
		//System.out.println("----------------------------DTO----------------------------------");
		TypedQuery<ConsultaPunto10DTO> query = entityManager.createNamedQuery(Registro.LISTA_REGISTRO_FECHA_DTO, ConsultaPunto10DTO.class);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date creacion = formato.parse("2012-12-30 00:00:00");
			query.setParameter("creacionRegistro", creacion);
			List<ConsultaPunto10DTO> registros = query.getResultList();
			Assert.assertEquals(4, registros.size());
			//for (ConsultaPunto10DTO consultaPunto10DTO : registros) {
				//System.out.println(consultaPunto10DTO);
			//}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Punto 1 segundo archivo
	 *  Test para contar el numero de familias registradas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familias.json"})
	public void contarNumeroFamilias() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Familia.CONTAR_FAMILIAS, Long.class);
		List<Long> registros = query.getResultList();
		Assert.assertEquals(1, registros.size());
	}
	
	/**
	 * Punto 2 segundo archivo
	 *  Test que permite contar el número de personas a las que le han aceptado un registro
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json", "registros.json"})
	public void contarUsuariosAceptadasPorFecha() {
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNamedQuery(Registro.CONTAR_USUARIO_REG_ACEPTADO);
	
		List<Object[]> registros = query.getResultList();
		Assert.assertEquals(2, registros.size());


	}
	
	/**
	 * Punto 3 segundo archivo
	 * Test para determinar que personas no han realizado registros.
	 */
	//@Test
	//@Transactional(value = TransactionMode.ROLLBACK)
	//@UsingDataSet({ "usuarios.json", "registros.json" })
	//public void listarUsuarioSinRegistro() {
	//	TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.USUARIO_SIN_REGISTRO, Usuario.class);
	//	List<Usuario> usuarios = query.getResultList();
	//	Assert.assertEquals(1, usuarios.size());
	//}
	
	/**
	 * Punto 4 segundo archivo
	 * Test para determinar cuantos registros ha realizado cada empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "usuarios.json", "registros.json" })
	public void listarNumeroRegistrosEmpleado() {
		TypedQuery<ConsultaPunto4DTO> query = entityManager.createNamedQuery(Registro.LISTAR_NUMERO_REG_EMPLEADO, ConsultaPunto4DTO.class);
		List<ConsultaPunto4DTO> usuarios = query.getResultList();
		//Assert.assertEquals(1, usuarios.size());
		//for (ConsultaPunto4DTO consultaPunto4DTO : usuarios) {
			//System.out.println(consultaPunto4DTO);
		//}
	}
	
	/**
	 * Punto 6 segundo archivo
	 * Test permita determinar cual es la familia que más especies tiene registradas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "especies.json", "generos.json", "familias.json" })
	public void familiaConMasEspecieRegistradas() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Especie.LISTAR_FAMILIA_MAYOR_ESPECIE, Long.class);
		List<Long> registros = query.getResultList();
		Assert.assertEquals(1, registros.size());
		System.out.println(registros.get(0));
	}
}
