package tutSys.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tutSys.modelo.dao.ProblematicaAcademicaDAO;
import tutSys.modelo.pojo.ProblematicaAcademicaAux;
import tutSys.modelo.pojo.TutorAcademico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Axel Utrera
 */
public class FXMLProblematicaAcademicaControlador {

    @FXML
    private ComboBox<String> comboBoxBusqueda;

    @FXML
    private Label labelFechaHoy;

    @FXML
    private TableColumn tableColumnDescripcion;

    @FXML
    private TableColumn tableColumnExperienciaEducativa;

    @FXML
    private TableColumn tableColumnNumeroReportes;

    @FXML
    private TableColumn tableColumnProfesor;

    @FXML
    private TableView<ProblematicaAcademicaAux> tableViewProblematicas;

    @FXML
    private TextField textFieldBusqueda;

    public static TutorAcademico tutorAcademico;

    @FXML
    void initialize() {
        colocarFechaActual();
        configurarComboBoxBusqueda();
        llenarTablaProblematica();
    }

    @FXML
    void clicBuscar(ActionEvent event) {

    }

    @FXML
    void clicEliminar(ActionEvent event) {

    }

    @FXML
    void clicModificar(ActionEvent event) {

    }

    @FXML
    void clicSalir(ActionEvent event) {

    }


    private void llenarTablaProblematica() {
        ArrayList<ProblematicaAcademicaAux> problematicasObtenidas = ProblematicaAcademicaDAO.obtenerProblematicasAcademicas(tutorAcademico.getIdTutorAcademico());
        ObservableList<ProblematicaAcademicaAux> infoProblematica = FXCollections.observableArrayList();
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tableColumnNumeroReportes.setCellValueFactory(new PropertyValueFactory("numeroReportes"));
        tableColumnExperienciaEducativa.setCellValueFactory(new PropertyValueFactory("nombreExperienciaEducativa"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory("nombreProfesor"));
        infoProblematica.addAll(problematicasObtenidas);
        tableViewProblematicas.setItems(infoProblematica);
    }

    private void colocarFechaActual(){
        Date fechaActual = new Date();
        labelFechaHoy.setText("Hoy es: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
    }

    private void configurarComboBoxBusqueda(){
        ObservableList<String> tiposBusqueda = FXCollections.observableArrayList();
        tiposBusqueda.addAll("Buscar por: EE", "Buscar por: Profesor");
        comboBoxBusqueda.setItems(tiposBusqueda);
    }

}
