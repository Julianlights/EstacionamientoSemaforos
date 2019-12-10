/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiculo.concurrencia;

import java.util.ArrayList;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class Estacionamiento {
    ArrayList<Vehiculo> caja;
    int[] cajas;
    int eEntrada=0;
    int eSalida=0;
    boolean autodentro = false;
    int indexAutos = 0;
    
    public Estacionamiento(){
        this.caja = new ArrayList<>();
        cajas = new int[20];
    }
    
    public void llenar(int lugar,int espacio){
        cajas[lugar]=espacio;
    }
    
    public void quitar(int lugar){
        cajas[lugar]=0;
    }
    
    public int getlugar(int lugar){
        System.out.println(cajas[lugar]);
        return cajas[lugar];
    }
    
    public int[] getCajas(){
        return cajas;
    }
    
    
}
