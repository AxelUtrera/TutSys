package tutSys.vistas;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import tutSys.modelo.pojo.ProblematicaAcademica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Axel Utrera
 * fecha de creacion: 13 / 06 /2022
 * Ultima modificacion: 13 / 06 / 2022
 */


public class FXMLProblematicaAcademicaControlador implements Initializable {

    @FXML
    private ComboBox<?> comboBoxBusqueda;

    @FXML
    private Label labelFechaHoy;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcion;

    @FXML
    private TableColumn<?, ?> tableColumnExperienciaEducativa;

    @FXML
    private TableColumn<?, ?> tableColumnNumeroReportes;

    @FXML
    private TableColumn<?, ?> tableColumnProfesor;

    @FXML
    private TableView<ProblematicaAcademica> tableViewProblematicas;

    @FXML
    private TextField textFieldBusqueda;

    @FXML
    void clicBuscar(ActionEvent event) {

    }

    @FXML
    void clicEliminar(ActionEvent event) {

    }

    @FXML
    void clicModificar(ActionEvent event) {
        FXMLEdicionProblematicaAcademicaControlador.problematicaAEditar = tableViewProblematicas.getSelectionModel().getSelectedItem();
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

    }
}
