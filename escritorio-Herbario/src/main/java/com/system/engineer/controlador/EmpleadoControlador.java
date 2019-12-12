package com.system.engineer.controlador;

import java.util.Date;

import javax.swing.JOptionPane;

import org.netbeans.modules.dbschema.TableElement;
import org.persistenciaHerbario.Empleado;

import com.system.engineer.modelo.AdministradorDelegado;
import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.util.Utilidades;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class EmpleadoControlador {

	/**
	 * table donde se almacena la informacion de los empleados
	 */
	@FXML
	private TableView<EmpleadoObservable> tablaEmpleados;
	/**
	 * hace referencia a la columna con las cedulas
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> cedulaColumna;
	/**
	 * hace referencia a la columna de los nombres de los empleados
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> nombreColumna;
	/**
	 * etiqueta de cedula
	 */
	@FXML
	private Label txtCedula;
	/**
	 * etiqueta de nombre
	 */
	@FXML
	private Label txtNombre;
	/**
	 * etiqueta de apellido
	 */
	@FXML
	private Label txtApellido;
	/**
	 * etiqueta de email
	 */
	@FXML
	private Label txtEmail;
	/**
	 * etiqueta de clave
	 */
	@FXML
	private Label txtClave;
	/**
	 * etiqueta de salario
	 */
	@FXML
	private Label txtSalario;
	/**
	 * etiqueta de Activo
	 */
	@FXML
	private Label txtActivo;
	/**
	 * etiqueta de salario
	 */
	@FXML
	private Label txtCreacion;

	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios manejador;

	private EmpleadoObservable empleadoObservable;
	
	public EmpleadoControlador() {
	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		cedulaColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getCedula());
		nombreColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombre());

		mostrarDetalleEmpleado(null);

		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));

	}

	/**
	 * permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param empleado empleado al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleEmpleado(EmpleadoObservable empleado) {

		if (empleado != null) {
			empleadoObservable = empleado;
			txtCedula.setText(empleado.getCedula().getValue());
			txtNombre.setText(empleado.getNombre().getValue());
			txtApellido.setText(empleado.getApellido().getValue());
			txtEmail.setText(empleado.getEmail().getValue());
			txtClave.setText(empleado.getContrasenia().getValue());
			txtSalario.setText(empleado.getSalario().getValue() + "");
			txtActivo.setText(empleado.getActivo().getValue() + "");
			txtCreacion.setText(empleado.getCreacion().getValue().toString());
			// txtFechaNacimiento.setText(empleado.getFechaNacimiento().getValue().toString());
		} else {
			txtCedula.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtEmail.setText("");
			txtClave.setText("");
			txtSalario.setText("");
			txtActivo.setText("");
			txtCreacion.setText("");
			// txtFechaNacimiento.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar empleado
	 */
	@FXML
	public void agregarEmpleado() {
		manejador.cargarEscenarioCrearEmpleado();
		tablaEmpleados.refresh();
	}

	@FXML
	public void editarEmpleado() {
		int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();
		if (indice != -1) {

			Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

			// EmpleadoObservable empleado = tablaEmpleados.getItems().get(indice);

			System.out.println(empleado.getCedula());
			

			manejador.cargarEscenarioEditarEmpleado(empleado);
			tablaEmpleados.refresh();
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un Empleado");
		}
	}

	/**
	 * permite eliminar un empleado seleccionado
	 */
	@FXML
	public void desactivarEmpleado() {
		int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();

		if (indice != -1) {

			Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

			System.out.println(empleado.getCedula());

			if (manejador.eliminarEmpleado(empleado)) {
				
				tablaEmpleados.refresh();
				Utilidades.mostrarMensaje("Borrar", "El empleado ha sido desactivado");
			} else {
				Utilidades.mostrarMensaje("Error", "El empleado no pudo ser desactivado");
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un Empleado");
		}
	}

	/**
	 * permite eliminar un empleado seleccionado
	 */
	// @FXML
	// public void elimiarEmpleado() {

	// int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();

	// System.out.println(tablaEmpleados.getItems().size());

	// Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

	// if (escenarioInicial.eliminarEmpleado(empleado)) {
	// tablaEmpleados.getItems().remove(indice);
	// Utilidades.mostrarMensaje("Borrar", "El empleado ha sido eliminado con
	// exito");
	// } else {
	// Utilidades.mostrarMensaje("Error", "El empleado no pudo ser eliminado");
	// }

	// }

	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
		tablaEmpleados.setItems(manejador.getEmpleadosObservables());
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
