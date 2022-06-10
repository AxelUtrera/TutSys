/*
*Autor: Daniel Eduardo Anota Paxtian
*Fecha de creacion: 09/06/2022
*Ultima modificacion: 09/06/2022
*Nombre modificador: Daniel Eduardo Anota Paxtian
*/

package tutSys.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RegistroReporteTutoriaControlador implements Initializable {

    @FXML
    private Label labelPeriodoEscolar;
    @FXML
    private Label labelFecha;
    @FXML
    private ComboBox<?> comboBoxTutoriaAcademica;
    @FXML
    private TableView<?> tableViewAlumnos;
    @FXML
    private TableColumn<?, ?> tableColumnMatricula;
    @FXML
    private TableColumn<?, ?> tableColumnNombre;
    @FXML
    private TableColumn<?, ?> tableColumnAsistio;
    @FXML
    private TableColumn<?, ?> tableColumnRiesgo;
    @FXML
    private Label labelTutorAcademico;
    @FXML
    private Label labelFechaTutoria;
    @FXML
    private Label labelFechaCierreReporte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicRegistrarReporteTutoria(ActionEvent event) {
    }

    @FXML
    private void clicCancelarRegistroReporteTutoria(ActionEvent event) {
    }
    
}
