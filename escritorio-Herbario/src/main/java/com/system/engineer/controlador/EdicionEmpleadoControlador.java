/**
 * 
 */
package com.system.engineer.controlador;

import java.util.Date;

import javax.swing.JOptionPane;

import org.persistenciaHerbario.Empleado;

import com.system.engineer.modelo.AdministradorDelegado;
import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.util.Utilidades;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class EdicionEmpleadoControlador {

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
	/**
	 * campo para la Salario
	 */
	@FXML
	private TextField txtSalario;
	
	/**
	 * Boton agregar
	 */
	@FXML
	private Button btnAgregar;
	
	/**
	 * Boton modificar
	 */
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
	 * permite cargar la informacion de un persona para realizar una edicion
	 * 
	 * @param empleado empleado a editar
	 */
	public void cargarPersona(Empleado empleado) {

		txtCedula.setText(empleado.getCedula());
		txtNombre.setText(empleado.getNombre());
		txtApellido.setText(empleado.getApellido());
		txtEmail.setText(empleado.getEmail());
		passClave.setText(empleado.getContrasenia());
		txtSalario.setText(empleado.getSalario() + "");
		// cmpFechaNacimiento.setValue(Utilidades.pasarALocalDate(empleado.getFechaNacimiento().getValue()));

	}

	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarPersona() {

		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String email = txtEmail.getText();
		String contrasenia = passClave.getText();
		Double salario = Double.parseDouble(txtSalario.getText());
		Boolean activo = true;
		Date creacion = new Date();

		Empleado persona = new Empleado(cedula, nombre, apellido, email, contrasenia, activo, creacion, salario);

		// Empleado persona = new Empleado();
		// persona.setCedula(txtCedula.getText());
		// persona.setNombre(txtNombre.getText());
		// persona.setApellido(txtApellido.getText());
		// persona.setContrasenia(passClave.getText());
		// persona.setEmail(txtEmail.getText());
		// persona.setCreacion(new Date());
		// persona.setSalario(11111.0);
		// persona.setActivo(true);

		if (manejador.registrarEmpleado(persona)) {

			manejador.agregarALista(persona);
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
	private void modificarEmpleado() {

		String cedula = txtCedula.getText();
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String email = txtEmail.getText();
		String contrasenia = passClave.getText();
		Double salario = Double.parseDouble(txtSalario.getText());
		Boolean activo = true;
		Date creacion = new Date();

		Empleado persona = new Empleado(cedula, nombre, apellido, email, contrasenia, activo, creacion, salario);

		if (manejador.modificarEmpleado(persona)) {
 
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
	
	/**
	 * Oculta o muestra un boton
	 * 
	 * @param tipo
	 */
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
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

}
