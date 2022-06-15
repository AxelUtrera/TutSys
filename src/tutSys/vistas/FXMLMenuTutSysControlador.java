/*
 *Autor: Daniel Eduardo Anota Paxtian
 *Fecha de creacion: 10/06/2022
 *Ultima modificacion: 10/06/2022
 *Nombre modificador: Daniel Eduardo Anota Paxtian
 */

package tutSys.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tutSys.TutSys;
import tutSys.modelo.pojo.TutorAcademico;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class FXMLMenuTutSysControlador implements Initializable {

    public static TutorAcademico tutorAcademico;
    @FXML
    public Label labelNombreUsuario;
    @FXML
    public Label labelFecha;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colocarFechaActual();
        colocarNombreUsuario();
    }

    @FXML
    public void invocarLlenarReporteTutoria(ActionEvent actionEvent) {
        try{
            FXMLRegistroProblematicaAcademicaControlador.reporteExtendido = null;
            FXMLRegistroReporteTutoriaControlador.tutorAcademico = tutorAcademico;
            FXMLLoader cargador =  new FXMLLoader(getClass().getResource("RegistroReporteTutoriaVista.fxml"));
            Parent root = cargador.load();
            Scene escena = new Scene(root);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }

    @FXML
    public void invocarRegistroProblematicaAcademica(ActionEvent actionEvent) {
        try{
            FXMLLoader cargador =  new FXMLLoader(getClass().getResource("RegistroProblematicaAcademicaVista.fxml"));
            Parent root = cargador.load();
            Scene escena = new Scene(root);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }

    @FXML
    public void invocarConsultarProblematicasAcademicas(ActionEvent actionEvent) {
        try{
            Stage escenarioPrincipal = (Stage) labelNombreUsuario.getScene().getWindow();
            Scene consultaProblematicaAcademica = new Scene(FXMLLoader.load(getClass().getResource("ProblematicaAcademicaVista.fxml")));
            escenarioPrincipal.setScene(consultaProblematicaAcademica);
            escenarioPrincipal.setTitle("TutSys - Consultar Problematica Académica");
            escenarioPrincipal.show();
        } catch (IOException excepcion){
            excepcion.printStackTrace();
        }
    }

    private void colocarFechaActual(){
        Date fechaActual = new Date();
        labelFecha.setText("Hoy es: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaActual));
    }

    private void colocarNombreUsuario(){
        labelNombreUsuario.setText("Bienvenida/o!: " + tutorAcademico.getNombre());
    }
}
