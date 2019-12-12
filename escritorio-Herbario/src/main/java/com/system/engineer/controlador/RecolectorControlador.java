package com.system.engineer.controlador;

import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Recolector;

import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.modelo.RecolectorObservable;
import com.system.engineer.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author Shonny
 *
 */
public class RecolectorControlador {
	/**
	 * table donde se almacena la informacion de los empleados
	 */
	@FXML
	private TableView<RecolectorObservable> tablaRecolectores;
	/**
	 * hace referencia a la columna con las cedulas
	 */
	@FXML
	private TableColumn<RecolectorObservable, String> cedulaColumna;
	/**
	 * hace referencia a la columna de los nombres de los empleados
	 */
	@FXML
	private TableColumn<RecolectorObservable, String> nombreColumna;
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

	private RecolectorObservable recolectorObservable;
	
	public RecolectorControlador() {
	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		cedulaColumna.setCellValueFactory(recolectorCelda -> recolectorCelda.getValue().getCedula());
		nombreColumna.setCellValueFactory(recolectorCelda -> recolectorCelda.getValue().getNombre());

		mostrarDetalleRecolector(null);

		tablaRecolectores.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleRecolector(newValue));

	}
	
	/**
	 * permite mostrar la informacion del recolector seleccionado
	 * 
	 * @param empleado empleado al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleRecolector(RecolectorObservable recolector) {

		if (recolector != null) {
			recolectorObservable = recolector;
			txtCedula.setText(recolector.getCedula().getValue());
			txtNombre.setText(recolector.getNombre().getValue());
			txtApellido.setText(recolector.getApellido().getValue());
			txtEmail.setText(recolector.getEmail().getValue());
			txtClave.setText(recolector.getContrasenia().getValue());
			txtActivo.setText(recolector.getActivo().getValue() + "");
			txtCreacion.setText(recolector.getCreacion().getValue().toString());
			// txtFechaNacimiento.setText(empleado.getFechaNacimiento().getValue().toString());
		} else {
			txtCedula.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtEmail.setText("");
			txtClave.setText("");
			txtActivo.setText("");
			txtCreacion.setText("");
			// txtFechaNacimiento.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar recolector
	 */
	@FXML
	public void agregarRecolector() {
		manejador.cargarEscenarioCrearRecolector();
		tablaRecolectores.refresh();
	}

	/**
	 * permite editar un recolector seleccionado
	 */
	@FXML
	public void editarRecolector() {
		int indice = tablaRecolectores.getSelectionModel().getSelectedIndex();
		if (indice != -1) {

			Recolector recolector = tablaRecolectores.getItems().get(indice).getRecolector();

			// EmpleadoObservable empleado = tablaEmpleados.getItems().get(indice);
			

			manejador.cargarEscenarioEditarRecolector(recolector);
			tablaRecolectores.refresh();
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un Recolector");
		}
	}

	/**
	 * permite eliminar un recolector seleccionado
	 */
	@FXML
	public void desactivarRecolector() {
		int indice = tablaRecolectores.getSelectionModel().getSelectedIndex();

		if (indice != -1) {

			Recolector recolector =tablaRecolectores.getItems().get(indice).getRecolector();

			if (manejador.eliminarRecolector(recolector)) {
				
				tablaRecolectores.refresh();
				Utilidades.mostrarMensaje("Borrar", "El recolector ha sido desactivado");
			} else {
				Utilidades.mostrarMensaje("Error", "El recolector no pudo ser desactivado");
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar un recolector");
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
		tablaRecolectores.setItems(manejador.getRecolectoresObservable());
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
