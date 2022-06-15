package tutSys.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
<<<<<<< HEAD
import javafx.stage.Modality;
import javafx.stage.Stage;
import tutSys.modelo.pojo.ProblematicaAcademica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
=======
import javafx.scene.control.cell.PropertyValueFactory;
import tutSys.modelo.dao.ProblematicaAcademicaDAO;
import tutSys.modelo.pojo.ProblematicaAcademicaAux;
import tutSys.modelo.pojo.TutorAcademico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
>>>>>>> solucionConflictos

/**
 *
 * @author Axel Utrera
 * fecha de creacion: 13 / 06 /2022
 * Ultima modificacion: 13 / 06 / 2022
 */


public class FXMLProblematicaAcademicaControlador implements Initializable {

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
    void clicBuscar(ActionEvent event) {

    }

    @FXML
    void clicEliminar(ActionEvent event) {

    }

    @FXML
    void clicModificar(ActionEvent event) {
        //FXMLEdicionProblematicaAcademicaControlador.problematicaAEditar = tableViewProblematicas.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader cargador =  new FXMLLoader(getClass().getResource("EdicionProblematicaAcademicaVista.fxml"));
            Parent root = cargador.load();
            Scene escena = new Scene(root);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Edicion de problematica academica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }

    @FXML
    void clicSalir(ActionEvent event) {
        Stage escenario = (Stage) comboBoxBusqueda.getScene().getWindow();
        escenario.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
