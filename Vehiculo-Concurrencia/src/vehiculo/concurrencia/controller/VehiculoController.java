/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculo.concurrencia.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import vehiculo.concurrencia.CarrosTasks;
import vehiculo.concurrencia.Estacionamiento;
import vehiculo.concurrencia.Vehiculo;

/**
 *
 * @author ayax9
 */
public class VehiculoController implements Initializable, Observer {

    Semaphore mutex = new Semaphore(1);
    Semaphore entrada = new Semaphore(0);
    Semaphore salida = new Semaphore(0);
    Semaphore vacios = new Semaphore(20);
    Estacionamiento e = new Estacionamiento();

    @FXML
    private Label lbentrada;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private Label lb6;
    @FXML
    private Label lb7;
    @FXML
    private Label lb8;
    @FXML
    private Label lb9;
    @FXML
    private Label lb10;
    @FXML
    private Label lb11;
    @FXML
    private Label lb14;
    @FXML
    private Label lb13;
    @FXML
    private Label lb12;
    @FXML
    private Label lb20;
    @FXML
    private Label lb16;
    @FXML
    private Label lb15;
    @FXML
    private Label lb18;
    @FXML
    private Label lb17;
    @FXML
    private Label lb19;
    @FXML
    public Button Boton;

    @FXML
    void comenzar(MouseEvent event) {
        CarrosTasks task = new CarrosTasks(this);
        Thread proceso = new Thread(task);
        proceso.setDaemon(true);
        proceso.start();
        Boton.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String) arg);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String[] info = ((String) arg).split(";");
                switch (info[0]) {
                    case "carroentrando":
                        switch (info[1]) {
                            case "0":
                                lb1.setText(info[2]);
                                break;
                            case "1":
                                lb2.setText(info[2]);
                                break;
                            case "2":
                                lb3.setText(info[2]);
                                break;
                            case "3":
                                lb4.setText(info[2]);
                                break;
                            case "4":
                                lb5.setText(info[2]);
                                break;
                            case "5":
                                lb6.setText(info[2]);
                                break;
                            case "6":
                                lb7.setText(info[2]);
                                break;
                            case "7":
                                lb8.setText(info[2]);
                                break;
                            case "8":
                                lb9.setText(info[2]);
                                break;
                            case "9":
                                lb10.setText(info[2]);
                                break;
                            case "10":
                                lb11.setText(info[2]);
                                break;
                            case "11":
                                lb12.setText(info[2]);
                                break;
                            case "12":
                                lb13.setText(info[2]);
                                break;
                            case "13":
                                lb14.setText(info[2]);
                                break;
                            case "14":
                                lb15.setText(info[2]);
                                break;
                            case "15":
                                lb16.setText(info[2]);
                                break;
                            case "16":
                                lb17.setText(info[2]);
                                break;
                            case "17":
                                lb18.setText(info[2]);
                                break;
                            case "18":
                                lb19.setText(info[2]);
                                break;
                            case "19":
                                lb20.setText(info[2]);
                                break;

                        }
                        break;

                    case "carrosalida":
                        switch (info[1]) {
                            case "0":
                                lb1.setText("vacio");
                                break;
                            case "1":
                                lb2.setText("vacio");
                                break;
                            case "2":
                                lb3.setText("vacio");
                                break;
                            case "3":
                                lb4.setText("vacio");
                                break;
                            case "4":
                                lb5.setText("vacio");
                                break;
                            case "5":
                                lb6.setText("vacio");
                                break;
                            case "6":
                                lb7.setText("vacio");
                                break;
                            case "7":
                                lb8.setText("vacio");
                                break;
                            case "8":
                                lb9.setText("vacio");
                                break;
                            case "9":
                                lb10.setText("vacio");
                                break;
                            case "10":
                                lb11.setText("vacio");
                                break;
                            case "11":
                                lb12.setText("vacio");
                                break;
                            case "12":
                                lb13.setText("vacio");
                                break;
                            case "13":
                                lb14.setText("vacio");
                                break;
                            case "14":
                                lb15.setText("vacio");
                                break;
                            case "15":
                                lb16.setText("vacio");
                                break;
                            case "16":
                                lb17.setText("vacio");
                                break;
                            case "17":
                                lb18.setText("vacio");
                                break;
                            case "18":
                                lb19.setText("vacio");
                                break;
                            case "19":
                                lb20.setText("vacio");
                                break;
                        }
                        break;

                    case "entrada":
                        lbentrada.setTextFill(Color.GREEN);
                        lbentrada.setText(info[1]);
                        break;
                    case "salida":
                        lbentrada.setTextFill(Color.RED);
                        lbentrada.setText(info[1]);
                        break;

                }
            }

        });
    }

}
