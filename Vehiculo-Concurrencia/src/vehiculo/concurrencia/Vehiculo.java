/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculo.concurrencia;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class Vehiculo extends Observable implements Runnable {

    private Estacionamiento e;
    private Semaphore mutex;
    private Semaphore vacios;
    private Semaphore puertaS;
    private Semaphore puertaE;
    private static int carroEntrada;
    private int carroSalida;
    private boolean aux;

    public Vehiculo(Estacionamiento e, Observer observer, Semaphore mutex, Semaphore vacios, Semaphore salida, Semaphore entrada) {
        this.e = e;
        this.addObserver(observer);
        this.mutex = mutex;
        this.vacios = vacios;
        this.puertaS = salida;
        this.puertaE = entrada;
        carroEntrada = 0;
        carroSalida = 0;
        aux = false;
    }

    @Override
    public void run() {
        try {

            mutex.acquire();
            carroEntrada = e.indexAutos++;            
            System.out.println("Carro en espera: " + carroEntrada);
            mutex.release();
            vacios.acquire();

            mutex.acquire();
            if (e.eSalida > 0 || e.autodentro) {
                e.eEntrada++;
                System.out.println(carroEntrada + ": esperando en entrada");
                mutex.release();
                puertaE.acquire();
                e.eEntrada--;
            }

            e.autodentro = true;
            mutex.release();

            mutex.acquire();
            this.setChanged();
            this.notifyObservers(String.valueOf("entrada;" + carroEntrada));
            e.autodentro = false;
            if (e.eEntrada > 0) {
                puertaE.release();
            } else if (e.eSalida > 0) {
                puertaS.release();
            } else {
                mutex.release();
            }

            /* if(e.caja.isEmpty()){
                e.caja.add(this);
                System.out.println(carroEntrada+" Carro entrando en: "+ e.caja.indexOf(this));
                this.setChanged();
                this.notifyObservers(String.valueOf(e.caja.indexOf(this)+";"+carroEntrada));
               // aux=false;
            }
            else{*/
            
            mutex.acquire();
            for (Vehiculo v : e.caja) {

                if (v == null && !aux) {
                    e.caja.set(e.caja.indexOf(v), this);
                    System.out.println(carroEntrada + "  Carro entrando en: " + e.caja.indexOf(v));
                    this.setChanged();
                    this.notifyObservers(String.valueOf("carroentrando;"+e.caja.indexOf(this) + ";" + carroEntrada));
                    aux = true;
                }

            }

            if (!aux) {
                e.caja.add(this);
                System.out.println(carroEntrada + " add Carro entrando en: " + e.caja.indexOf(this));
                this.setChanged();
                this.notifyObservers(String.valueOf("carroentrando;"+e.caja.indexOf(this) + ";" + carroEntrada));
                aux = false;
            }
            carroSalida = carroEntrada;   
            mutex.release();
            //  }        
            
            Thread.sleep(((long) (Math.random() * 6000)) + 1000);
            
            mutex.acquire();
            System.out.println(carroSalida + "Carro saliendo en: " + (e.caja.indexOf(this) + 1));
            this.setChanged();
            this.notifyObservers(String.valueOf("carrosalida;" + e.caja.indexOf(this)));
            e.caja.set(e.caja.indexOf(this), null);
            mutex.release();

            mutex.acquire();
                    
            System.out.println("Carro en espera salida: " + carroSalida);
            mutex.release();
            vacios.release();

            mutex.acquire();
            if (e.eEntrada > 0 || e.autodentro) {
                e.eSalida++;
                System.out.println(carroSalida + ": esperando en salida");
                mutex.release();
                puertaS.acquire();
                e.eSalida--;

            }

            e.autodentro = true;
            mutex.release();

            mutex.acquire();
            this.setChanged();
            this.notifyObservers(String.valueOf("salida;" + carroSalida));
            e.autodentro = false;
            if (e.eSalida > 0) {
                puertaS.release();
            } else if (e.eEntrada > 0) {
                puertaE.release();
            } else {
                mutex.release();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
