package espol.poo;

import java.net.http.HttpResponse;
import java.util.ArrayList;

import espol.poo.excepciones.ChoferNoEncontrado;
import espol.poo.modelo.Bus;

public class Main {
    public static void main(String[] args) {
        System.out.println("Examen 2E");
        //COMPLETAR de acuerdo a las instrucciones
        try {
            ArrayList<Bus> buses= Bus.cargarBuses("buses.txt");
            for (Bus b: buses){
                    b.start();
            }

        } catch (ChoferNoEncontrado ce){
                    throw new ChoferNoEncontrado("123");
                }

                
    }
}