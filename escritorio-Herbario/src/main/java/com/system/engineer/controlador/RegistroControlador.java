package com.system.engineer.controlador;

import java.util.Date;

import javax.swing.JOptionPane;

import org.netbeans.modules.dbschema.TableElement;
import org.persistenciaHerbario.Empleado;
import org.persistenciaHerbario.Registro;

import com.system.engineer.modelo.AdministradorDelegado;
import com.system.engineer.modelo.EmpleadoObservable;
import com.system.engineer.modelo.EspecieObservable;
import com.system.engineer.modelo.RegistroObservable;
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
public class RegistroControlador {

	/**
	 * table donde se almacena la informacion de los registros
	 */
	@FXML
	private TableView<RegistroObservable> tablaRegistros;
	/**
	 * hace referencia a la columna con las cedulas
	 */
	@FXML
	private TableColumn<RegistroObservable, String> idColumna;
	/**
	 * hace referencia a la columna de los nombres de los empleados
	 */
	@FXML
	private TableColumn<RegistroObservable, String> nombreColumna;
	/**
	 * etiqueta de id
	 */
	@FXML
	private Label txtId;
	/**
	 * etiqueta de nombre
	 */
	@FXML
	private Label txtNombre;

	/**
	 * etiqueta de latitud
	 */
	@FXML
	private Label txtLatitud;
	/**
	 * etiqueta de longitud
	 */
	@FXML
	private Label txtLongitud;

	/**
	 * etiqueta de colector
	 */
	@FXML
	private Label txtColector;
	/**
	 * etiqueta de coleccion
	 */
	@FXML
	private Label txtColeccion;

	/**
	 * etiqueta de genero
	 */
	@FXML
	private Label txtGenero;
	/**
	 * etiqueta de familia
	 */
	@FXML
	private Label txtFamilia;

	/**
	 * etiqueta de observaciones
	 */
	@FXML
	private Label txtObservaciones;
	/**
	 * etiqueta de Municipio
	 */
	@FXML
	private Label txtMunicipio;

	/**
	 * etiqueta de Departamento
	 */
	@FXML
	private Label txtDepartamento;

	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios manejador;

	private RegistroObservable registroObservable;
	
	private EspecieObservable especieObservable;
	
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditarEspecie;

	public RegistroControlador() {
	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

//		idColumna.setCellValueFactory(registroCelda ->
//		registroCelda.getValue().getId());
//		nombreColumna.setCellValueFactory(registroCelda ->
//		registroCelda.getValue().getEspecie().getNombre());
//
//		mostrarDetalleRegistro(null);
//
//		tablaRegistros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
//		mostrarDetalleRegistro(newValue));
	}

	/**
	 * permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param registro empleado al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleRegistro(RegistroObservable registro) {

		if (registro != null) {
			registroObservable = registro;
			txtColector.setText(registro.getUsuario().getNombre().get());
			txtColeccion.setText(registro.getCreacion().get().toString());
			txtId.setText(registro.getEspecie().getId().get());
			txtNombre.setText(registro.getEspecie().getNombre().get());
			txtGenero.setText(registro.getEspecie().getGenero().getNombre().get());
			txtFamilia.setText(registro.getEspecie().getGenero().getFamilia().get());
			txtObservaciones.setText(registro.getEspecie().getObservaciones().get());
			txtMunicipio.setText(registro.getEspecie().getDivipola().getMunicipio().get());
			txtDepartamento.setText(registro.getEspecie().getDivipola().getDepartamento().get());
			txtLatitud.setText(registro.getEspecie().getLatitud().get());
			txtLongitud.setText(registro.getEspecie().getLongitud().get());

		} else {
			txtId.setText("");
			txtNombre.setText("");
			txtColector.setText("");
			txtColeccion.setText("");
			txtGenero.setText("");
			txtFamilia.setText("");
			txtObservaciones.setText("");
			txtMunicipio.setText("");
			txtDepartamento.setText("");
			txtLatitud.setText("");
			txtLongitud.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar especie
	 */
	@FXML
	public void agregarEspecie() {
		manejador.cargarEscenarioCrearEspecie();
		tablaRegistros.refresh();
	}

	@FXML
	public void editarEspecie() {
		int indice = tablaRegistros.getSelectionModel().getSelectedIndex();
		if (indice != -1) {

			Registro registro = tablaRegistros.getItems().get(indice).getRegistro();
			// EmpleadoObservable empleado = tablaEmpleados.getItems().get(indice);

			System.out.println(registro.getDescripcion());

			manejador.cargarEscenarioEditarEspecie(registro);
			tablaRegistros.refresh();
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar una especie");
		}
	}

	/**
	 * permite eliminar un empleado seleccionado
	 */
	@FXML
	public void desactivarEspecie() {
		int indice = tablaRegistros.getSelectionModel().getSelectedIndex();

		if (indice != -1) {

			Registro registro = tablaRegistros.getItems().get(indice).getRegistro();

			System.out.println(registro.getDescripcion());

			if (manejador.eliminarEspecie(registro)) {

				tablaRegistros.refresh();
				Utilidades.mostrarMensaje("Borrar", "La especie ha sido desactivada");
			} else {
				Utilidades.mostrarMensaje("Error", "La especie no pudo ser desactivada");
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar una especie");
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
		tablaRegistros.setItems(manejador.getRegistrosObservables());
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
