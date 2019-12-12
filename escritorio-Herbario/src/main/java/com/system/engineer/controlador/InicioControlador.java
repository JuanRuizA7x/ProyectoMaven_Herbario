package com.system.engineer.controlador;

import javax.swing.JOptionPane;

import org.persistenciaHerbario.Administrador;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;

import com.system.engineer.modelo.UsuarioObservable;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class InicioControlador {

	@FXML
	private Menu menuOpciones;
	@FXML
	private MenuItem menuItemEmpleado;
	@FXML
	private MenuItem menuItemRecolector;
	@FXML
	private MenuItem menuItemFamilia;
	@FXML
	private MenuItem menuItemGenero;

	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;

	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	private LoginControlador loginControlador;

	/**
	 * 
	 */
	public InicioControlador() {

	}

	/**
	 * 
	 */
	@FXML
	private void initialize() {
		menuOpciones.setVisible(false);
//		menuItemEmpleado.setVisible(false);
//		menuItemRecolector.setVisible(false);
//		menuItemFamilia.setVisible(false);
//		menuItemGenero.setVisible(false);
	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}

	/**
	 * permite mostrar la ventana de agregar empleado
	 */
	@FXML
	public void ventanaDetalleEmpleado() {
		escenarioInicial.cargarEscenarioDetalleEmpleado();
	}

	/**
	 * permite mostrar la ventana de agregar familia
	 */
	@FXML
	public void ventanaDetalleFamilia() {
		escenarioInicial.cargarEscenarioDetalleFamilia();
	}

	/**
	 * permite mostrar la ventana de agregar familia
	 */
	@FXML
	public void ventanaDetalleRecolector() {
		escenarioInicial.cargarEscenarioDetalleRecolector();
	}

	/**
	 * permite mostrar la ventana de detalle genero
	 */
	@FXML
	public void ventanaDetalleGenero() {
		escenarioInicial.cargarEscenarioDetalleGenero();
	}

	/**
	 * permite mostrar la ventana de detalle genero
	 */
	@FXML
	public void ventanaLogin() {
		escenarioInicial.cargarEscenarioLogin();
	}

	/**
	 * @return the menuOpciones
	 */
	public Menu getMenuOpciones() {
		return menuOpciones;
	}

	/**
	 * @param menuOpciones the menuOpciones to set
	 */
	public void setMenuOpciones(Menu menuOpciones) {
		this.menuOpciones = menuOpciones;
	}

	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

	@FXML
	private void prueba() {
		// System.out.println("hola");
		if (loginControlador.usuarioObservableLogin == null) {
			menuOpciones.setVisible(false);
		} else if (loginControlador.usuarioObservableLogin.getTipo().equals(Administrador.class)) {
			menuOpciones.setVisible(true);
			menuItemEmpleado.setVisible(true);
			menuItemRecolector.setVisible(true);
		} else if (loginControlador.usuarioObservableLogin.getTipo().equals(Recolector.class)) {
			// menuOpciones.setVisible(true);
		} else if (loginControlador.usuarioObservableLogin.getTipo().equals(Empleado.class)) {
			// menuOpciones.setVisible(true);
		}

	}

}
