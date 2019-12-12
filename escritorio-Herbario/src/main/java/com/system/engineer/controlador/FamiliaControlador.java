package com.system.engineer.controlador;

import java.util.Date;

import org.netbeans.modules.dbschema.TableElement;
import org.persistenciaHerbario.Familia;

import com.system.engineer.modelo.FamiliaDelegado;
import com.system.engineer.modelo.FamiliaObservable;
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
public class FamiliaControlador {

	/**
	 * table donde se almacena la informacion de las familias
	 */
	@FXML
	private TableView<FamiliaObservable> tablaFamilias;
	/**
	 * hace referencia a la columna con los id
	 */
	@FXML
	private TableColumn<FamiliaObservable, String> idColumna;
	/**
	 * hace referencia a la columna de los nombres de las familias
	 */
	@FXML
	private TableColumn<FamiliaObservable, String> nombreColumna;
	/**
	 * hace referencia a la columna de la fecha de creación de las familias
	 */
	@FXML
	private TableColumn<FamiliaObservable, Date> creacionColumna;
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
	 * etiqueta de descripcion
	 */
	@FXML
	private Label txtDescripcion;
	/**
	 * etiqueta de estado
	 */
	@FXML
	private Label txtEstado;
	/**
	 * etiqueta de fecha
	 */
	@FXML
	private Label txtCreacion;
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditarFamilia;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios manejador;

	private FamiliaObservable familiaObservable;

	public FamiliaControlador() {
	}

	/**
	 * permite cargar la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		idColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getId());//El Id es un Integer
		nombreColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getNombre());
		creacionColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getCreacion());
		
		mostrarDetalleFamilia(null);

		tablaFamilias.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleFamilia(newValue));

	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	//public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
	//	this.manejador = escenarioInicial;
		//tablaEmpleados.setItems(escenarioInicial.getEmpleadosObservables());
	//}

	/**
	 * permite mostrar la informacion de la familia seleccionada
	 * 
	 * @param familia familia a la que se le desea mostrar el detalle
	 */
	public void mostrarDetalleFamilia(FamiliaObservable familia) {

		if (familia != null) {
			familiaObservable = familia;
			txtId.setText(familia.getId().getValue().toString());//Ojo el Id es un Integer
			txtNombre.setText(familia.getNombre().getValue());
			txtEstado.setText(familia.getActivo().getValue().toString());//Ojo activo es un Boolean
			txtDescripcion.setText(familia.getDescripcion().getValue());
			txtCreacion.setText(familia.getCreacion().getValue().toString());
		} else {
			txtId.setText("");
			txtNombre.setText("");
			txtEstado.setText("");
			txtDescripcion.setText("");
			txtCreacion.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar familia
	 */
	@FXML
	public void agregarFamilia() {
		manejador.cargarEscenarioCrearFamilia(); 
		tablaFamilias.refresh();
	}
	
	/**
	 * permite cargar la ventana de editar familia
	 */
	@FXML
	public void editarFamilia() {
		int indice = tablaFamilias.getSelectionModel().getSelectedIndex();
		if (indice != -1) {

			Familia familia = tablaFamilias.getItems().get(indice).getFamilia();

			manejador.cargarEscenarioEditarFamilia(familia);
			tablaFamilias.refresh();
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar una familia");
		}
	}

	/**
	 * permite desactivar una familia seleccionada
	 */
	@FXML
	public void desactivarFamilia() {
		int indice = tablaFamilias.getSelectionModel().getSelectedIndex();

		if (indice != -1) {

			Familia familia = tablaFamilias.getItems().get(indice).getFamilia();

			if (manejador.eliminarFamilia(familia)) {
				
				tablaFamilias.refresh();
				Utilidades.mostrarMensaje("Borrar", "La familia ha sido desactivada");
			} else {
				Utilidades.mostrarMensaje("Error", "La familia no pudo ser desactivada");
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Debe seleccionar una Familia");
		}
	}
	
	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
		tablaFamilias.setItems(manejador.getFamiliasObservables());
	}

	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditarFamilia(Stage escenarioEditarFamilia) {
		this.escenarioEditarFamilia = escenarioEditarFamilia;
	}

}
