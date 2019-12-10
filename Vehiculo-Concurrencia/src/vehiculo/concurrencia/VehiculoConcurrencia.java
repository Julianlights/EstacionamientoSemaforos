/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculo.concurrencia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class VehiculoConcurrencia extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent proyecto = FXMLLoader.load(getClass().getResource("VistaEstacionamiento.fxml"));
        Scene estacionamiento = new Scene(proyecto);
        
        primaryStage.setScene(estacionamiento);
      
        primaryStage.show();
    }
    
}
