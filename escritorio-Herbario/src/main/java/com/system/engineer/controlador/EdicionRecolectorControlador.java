/**
 * 
 */
package com.system.engineer.controlador;

import java.util.Date;


import org.persistenciaHerbario.Recolector;

import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class EdicionRecolectorControlador {
	
	/**
	 * campo para la cedula
	 */
	@FXML
	private TextField txtCedula;
	/**
	 * campo para la Nombre
	 */
	@FXML
	private TextField txtNombre;
	/**
	 * campo para la Apellido
	 */
	@FXML
	private TextField txtApellido;
	/**
	 * campo para la Email
	 */
	@FXML
	private TextField txtEmail;
	/**
	 * campo para la Clave
	 */
	@FXML
	private TextField passClave;
	
	@FXML
	private Button btnAgregar;
	
	@FXML
	private Button btnModificar;
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador de los escenario
	 */
	private ManejadorEscenarios manejador;
	

	/**
	 * 
	 */
	@FXML
	private void initialize() {
		
	}
	
	
	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarRecolector() {

		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String email = txtEmail.getText();
		String contrasenia = passClave.getText();
		Boolean activo = true;
		Date creacion = new Date();
		
		Recolector recolector = new Recolector(cedula, nombre, apellido, email, contrasenia, activo, creacion);

		if (manejador.registrarRecolector(recolector)) {

			manejador.agregarAListaRecolector(recolector);
			Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
			escenarioEditar.close();
		} else {
			Utilidades.mostrarMensaje("Registro", "Error en registro!!");
		}
	}

	/**
	 * permite editar la informacion de una persona
	 */
	@FXML
	private void modificarRecolector() {

		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String email = txtEmail.getText();
		String contrasenia = passClave.getText();
		Boolean activo = true;
		Date creacion = new Date();
		
		Recolector recolector = new Recolector(cedula, nombre, apellido, email, contrasenia, activo, creacion);

		if (manejador.modificarRecolector(recolector)) {
 
			//JOptionPane.showMessageDialog(null, persona.getCedula());
			//manejador.getEmpleadosObservables();
			Utilidades.mostrarMensaje("Registro modificado", "Registro modificado con exitoso!!");
			escenarioEditar.close();
		} else {
			//JOptionPane.showMessageDialog(null, persona.getCedula());
			Utilidades.mostrarMensaje("Registro modificado", "Error en modificar registro!!");
		}
	}
	
	
	/**
	 * permite cerrar la ventana de editar y crear
	 */
	@FXML
	private void cancelar() {
		escenarioEditar.close();
	}
	
	
	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}
	
	
	public void botones(boolean tipo) {
		if(tipo) {
			btnAgregar.setVisible(true);
			btnModificar.setVisible(false);
		}else {
			btnAgregar.setVisible(false);
			btnModificar.setVisible(true);
		}
		
	}
	
	/**
	 * permite cargar la informacion de un Recolector para realizar una edicion
	 * 
	 * @param empleado empleado a editar
	 */
	public void cargarRecolector(Recolector recolector) {

		txtCedula.setText(recolector.getCedula());
		txtNombre.setText(recolector.getNombre());
		txtApellido.setText(recolector.getApellido());
		txtEmail.setText(recolector.getEmail());
		passClave.setText(recolector.getContrasenia());

	}
	
	
	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

}
