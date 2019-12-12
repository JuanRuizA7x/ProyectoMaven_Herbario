package com.system.engineer.controlador;

import java.io.IOException;
import java.util.List;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Especie;
import org.persistenciaHerbario.Familia;
import org.persistenciaHerbario.Genero;
import org.persistenciaHerbario.Recolector;
import org.persistenciaHerbario.Registro;
import org.persistenciaHerbario.Usuario;

import com.system.engineer.Main;
import com.system.engineer.ejb.exepciones.ElementoNoEncontradoException;
import com.system.engineer.modelo.AdministradorDelegado;
import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.modelo.EspecieDelegado;
import com.system.engineer.modelo.EspecieObservable;
import com.system.engineer.modelo.FamiliaDelegado;
import com.system.engineer.modelo.FamiliaObservable;
import com.system.engineer.modelo.GeneroObservable;
import com.system.engineer.modelo.RecolectorObservable;
import com.system.engineer.modelo.RegistroDelegado;
import com.system.engineer.modelo.RegistroObservable;
import com.system.engineer.modelo.UsuarioObservable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class ManejadorEscenarios {
	/**
	 * contenedor prinpipal de la aplicacion
	 */
	private Stage escenario;
	/**
	 * tipo de panel inicial
	 */
	private BorderPane bordePanel;
	/**
	 * para almacenar empleados observables
	 */
	private ObservableList<EmpleadoObservable> empleadosObservables;
	/**
	 * para almacenar registros observables
	 */
	private ObservableList<RegistroObservable> registrosObservables;
	/**
	 * para almacenar registros observables
	 */
	private ObservableList<EspecieObservable> especiesObservables;
	/**
	 * para almacenar empleados observables
	 */
	private ObservableList<RecolectorObservable> recolectoresObservable;
	/**
	 * para almacenar familias observables
	 */
	private ObservableList<FamiliaObservable> familiasObservables;
	/**
	 * para almacenar familias observables
	 */
	private ObservableList<GeneroObservable> generosObservables;
	/**
	 * conexion con capa de negocio
	 */
	private AdministradorDelegado administradorDelegado;
	/**
	 * conexion con capa de negocio
	 */
	private FamiliaDelegado familiaDelegado;
	
	/**
	 * conexion con capa de negocio
	 */
	private RegistroDelegado registroDelegado;
	
	/**
	 * conexion con capa de negocio
	 */
	private EspecieDelegado especieDelegado;


	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;

		administradorDelegado = AdministradorDelegado.administradorDelegado;
		familiaDelegado=FamiliaDelegado.familiaDelegado;
		registroDelegado=RegistroDelegado.registroDelegado;
		empleadosObservables = FXCollections.observableArrayList();
		generosObservables = FXCollections.observableArrayList();

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/inicio.fxml"));

			bordePanel = (BorderPane) loader.load();
			

			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.setScene(scene);
			escenario.show();

			// se carga el controlador
			InicioControlador controlador = loader.getController();
			controlador.setEscenarioInicial(this);

			cargarEscena();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscena() {

		try {

			// empleadosObservables = administradorDelegado.listarEmpleadosObservables();

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("vista/datos_especie.fxml"));
			AnchorPane panelAncho = (AnchorPane) loader2.load();
			bordePanel.setCenter(panelAncho);

			// EmpleadoControlador controlador = loader2.getController();
			// controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Muestra el escenario para mostrar los detalles de un empleado
	 */
	public void cargarEscenarioDetalleEmpleado() {

		try {

			empleadosObservables = administradorDelegado.listarEmpleadosObservables();

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/detalle_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EmpleadoControlador controlador = loader.getController();
			controlador.setEscenarioEditar(escenarioCrear);
			controlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * muestra el escenario para crear una especie y un registro nuevo
	 */
	public void cargarEscenarioDetalleEspecie() {

		try {

			//registrosObservables = registroDelegado.listarRegistrosObservables();

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/datos_especie.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EmpleadoControlador controlador = loader.getController();
			controlador.setEscenarioEditar(escenarioCrear);
			controlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearEmpleado() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear);
			empleadoControlador.botones(true);
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * muestra el escenario para editar un genero
	 */
	public void cargarEscenarioCrearEspecie() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_especie.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Especie");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEspecieControlador especieControlador = loader.getController();
			especieControlador.setEscenarioEditar(escenarioCrear);
			especieControlador.botones(true);
			especieControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioEditarEmpleado(Empleado empleado) {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear);
			empleadoControlador.cargarPersona(empleado);
			empleadoControlador.botones(false);
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * muestra el escenario para crear un registro nuevo
	 */
	public void cargarEscenarioEditarEspecie(Registro registro) {

		try {

			//se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_especie.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			//se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Editar especie");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEspecieControlador especieControlador = loader.getController();
			especieControlador.setEscenarioEditar(escenarioCrear);
			especieControlador.cargarEspecie(registro.getEspecie());;
			especieControlador.botones(false);
			especieControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Muestra el escenario para mostrar los detalles de una familia
	 */
	public void cargarEscenarioDetalleFamilia() {

		try {

			familiasObservables = familiaDelegado.listarFamiliasObservables();

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/detalle_familia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear3");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			FamiliaControlador controlador = loader.getController();
			controlador.setEscenarioEditarFamilia(escenarioCrear);
			controlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * muestra el escenario para crear una familia nueva
	 */
	public void cargarEscenarioCrearFamilia() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_familia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear2");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionFamiliaControlador familiaControlador = loader.getController();
			familiaControlador.setEscenarioEditar(escenarioCrear);
			familiaControlador.botones(true);
			familiaControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * muestra el escenario para editar una familia
	 */
	public void cargarEscenarioEditarFamilia(Familia familia) {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_familia.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionFamiliaControlador familiaControlador = loader.getController();
			familiaControlador.setEscenarioEditar(escenarioCrear);
			familiaControlador.cargarFamilia(familia);
			familiaControlador.botones(false);
			familiaControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * permite registrar una familia en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarFamilia(Familia familia) {
		try {
			return familiaDelegado.registrarFamilia(familia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * permite modificar una familia en la base de datos
	 * 
	 * @param familia a modificar
	 * @return true si quedo modificada
	 */
	public boolean modificarFamilia(Familia familia) {
		try {
			return familiaDelegado.modificarFamilia(familia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un empleado
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarFamilia(Familia familia) {
		try {
			return familiaDelegado.eliminarFamilia(familia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Muestra el escenario para mostrar los detalles de un genero
	 */
	public void cargarEscenarioDetalleGenero() {

		try {

			generosObservables = familiaDelegado.listarGenerosObservables();

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/detalle_genero.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Tabla de g�neros");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			GeneroControlador controlador = loader.getController();
			controlador.setEscenarioEditarGenero(escenarioCrear);
			controlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * muestra el escenario para crear un genero nuevo
	 */
	public void cargarEscenarioCrearGenero() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_genero.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear G�nero");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionGeneroControlador generoControlador = loader.getController();
			generoControlador.llenarComboBox(getFamilias());
			generoControlador.setEscenarioEditar(escenarioCrear);
			generoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * muestra el escenario para editar un genero
	 */
	public void cargarEscenarioEditarGenero(Genero genero) {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_genero.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Edital G�nero");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionGeneroControlador generoControlador = loader.getController();
			generoControlador.llenarComboBox(getFamilias());
			generoControlador.setEscenarioEditar(escenarioCrear);
			generoControlador.cargarGenero(genero);
			generoControlador.botones(false);
			generoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * permite registrar un genero en la base de datos
	 * 
	 * @param genero a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarGenero(Genero genero) {
		
		try {
			return familiaDelegado.registrarGenero(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * permite modificar un genero en la base de datos
	 * 
	 * @param genero a modificar
	 * @return true si quedo modificado
	 */
	public boolean modificarGenero(Genero genero) {
		try {
			return familiaDelegado.modificarGenero(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un genero
	 * 
	 * @param genero a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarGenero(Genero genero) {
		try {
			return familiaDelegado.eliminarGenero(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioDetalleRecolector() {

		try {

			recolectoresObservable = administradorDelegado.listarRecolectoresObservables();

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/detalle_recolector.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Detalle");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			RecolectorControlador controlador = loader.getController();
			controlador.setEscenarioEditar(escenarioCrear);
			controlador.setManejador(this);


			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearRecolector() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_recolector.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);
			
			//se carga el controlador
			EdicionRecolectorControlador recolectorControlador = loader.getController();
			recolectorControlador.setEscenarioEditar(escenarioCrear);
			recolectorControlador.botones(true);
			recolectorControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioEditarRecolector(Recolector recolector) {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_recolector.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Editar");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);
			
			EdicionRecolectorControlador recolectorControlador = loader.getController();
			recolectorControlador.setEscenarioEditar(escenarioCrear);
			recolectorControlador.cargarRecolector(recolector);
			recolectorControlador.botones(false);
			recolectorControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioLogin() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/login.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Login");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);
			
			//se carga el controlador
			LoginControlador loginControlador = loader.getController();
			loginControlador.setEscenarioEditar(escenarioCrear);
			loginControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * 
	 * @return empleados observables
	 */
	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}
	
	/**
	 * @return the recolectoresObservable
	 */
	public ObservableList<RecolectorObservable> getRecolectoresObservable() {
		return recolectoresObservable;
	}

	/**
	 * 
	 * @return familias observables
	 */
	public ObservableList<FamiliaObservable> getFamiliasObservables() {
		return familiasObservables;
	}
	/**
	 * 
	 * @return registros observables
	 */
	public ObservableList<RegistroObservable> getRegistrosObservables() {
		return registrosObservables;
	}
	/**
	 * 
	 * @return generos observables
	 */
	public ObservableList<GeneroObservable> getGenerosObservables() {
		return generosObservables;
	}
	
	/**
	 * 
	 * @return familias 
	 */
	public List<Familia> getFamilias() {
		List<Familia> familias = familiaDelegado.listarFamilias();
		return familias;
	}
	
	/**
	 * 
	 * @return generos
	 */
	public List<Genero> getGeneros() {
		List<Genero> generos = familiaDelegado.listarGeneros();
		return generos;
	}
	
	/**
	 * Devuelve la familia con el nombre se�alado
	 * @param nombre
	 * @return
	 */
	public Familia buscarFamiliaPorNombre(String nombre) {
		return familiaDelegado.buscarFamiliaPorNombre(nombre);
	}
	
	/**
	 * Devuelve el genero con el nombre se�alado
	 * @param nombre
	 * @return
	 */
	public Genero buscarGeneroPorNombre(String nombre) {
		return familiaDelegado.buscarGeneroPorNombre(nombre);
	}

	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarALista(Empleado empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}
	
	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param genero
	 */
	public void agregarAListaGenero(Genero genero) {
		generosObservables.add(new GeneroObservable(genero));
	}
	
	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarAListaRecolector(Recolector recolector) {
		recolectoresObservable.add(new RecolectorObservable(recolector));
	}

	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarAListaFamilia(Familia familia) {
		familiasObservables.add(new FamiliaObservable(familia));
	}
	
	/**
	 * permite agrega una especie a la lista observable
	 * 
	 * @param especie
	 */
	public void agregarAListaEspecie(Especie especie) {
		especiesObservables.add(new EspecieObservable(especie));
	}
	/**
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

	/**
	 * permite registrar una persona en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		try {
			return administradorDelegado.registrarEmpleado(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean modificarEmpleado(Empleado empleado) {
		try {
			return administradorDelegado.modificarEmpleado(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un empleado
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		try {
			return administradorDelegado.eliminarEmpleado(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public UsuarioObservable login(String cedula) {
		try {
			return administradorDelegado.login(cedula);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * permite registrar una persona en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarRecolector(Recolector recolector) {
		try {
			return administradorDelegado.registrarRecolector(recolector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean modificarRecolector(Recolector recolector) {
		try {
			return administradorDelegado.modificarRecolector(recolector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un empleado
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarRecolector(Recolector recolector) {
		try {
			return administradorDelegado.eliminarRecolector(recolector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * permite registrar una especie en la base de datos
	 * 
	 * @param especie a registrar
	 * @return true si quedo registrada
	 */
	public boolean registrarEspecie(Especie especie) {
		try {
			return especieDelegado.registrarEspecie(especie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * permite editar una especie en la base de datos
	 * 
	 * @param especie a editar
	 * @return true si quedo editada
	 */
	public boolean modificarEspecie(Especie especie) {
		try {
			return especieDelegado.modificarEspecie(especie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * permite eliminar un registro
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarEspecie(Registro registro) {
		try {
			return registroDelegado.eliminarRegistro(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
