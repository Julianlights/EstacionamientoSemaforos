/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculo.concurrencia;

import java.util.Observer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;


/**
 *
 * @author Jorge Julian Sanchez
 */
public class CarrosTasks extends Task<Integer>{
    Observer obeserver;
    Semaphore mutex = new Semaphore(1);
    Semaphore entrada = new Semaphore(0);
    Semaphore salida = new Semaphore(0);
    Semaphore vacios = new Semaphore(20);
    Estacionamiento e = new Estacionamiento();
    
    public CarrosTasks(Observer observador){
        this.obeserver =observador;
    }

    @Override
    protected Integer call() throws Exception {
       int dormir = 0;
        for(int i=0; i<100; i++){
            try {                
                Vehiculo hilo = new Vehiculo(e, obeserver, mutex, vacios, salida, entrada);
                Thread auto = new Thread(hilo);
                auto.setDaemon(true);
                auto.start();       
                dormir=((int)(Math.random()*400))+100; 
                Thread.sleep(dormir);
            } catch (InterruptedException ex) {
                Logger.getLogger(CarrosTasks.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
    }
    
}
