/**
 *Autor: Axel Jordano Morales Utrera
 *Fecha de creacion: 09/06/2022
 *Ultima modificacion: 10/06/2022
 *Nombre modificador: Daniel Eduardo Anota Paxtian
 */

package tutSys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutSys extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("vistas/InicioSesionVista.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
