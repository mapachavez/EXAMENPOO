package espol.poo.modelo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiFunction;

import javax.imageio.IIOException;

import espol.poo.excepciones.ChoferNoEncontrado;
/**
 * 
 * @author Gladys
 */
public class Bus  extends Thread{
    private String placa;//placa del bus
    private double combustible;//cantidad de galones que tiene 
    private int numParadas;//número de paradas incluyendo el destino final
    private int capacidad;//número de pasajeros que puede llevar
    private Chofer chofer;//chofer que maneja el bus

    public Bus(String p, double cb, int np, int cp, Chofer ch) {
        this.placa = p;
        this.combustible = cb;
        this.numParadas = np;
        this.capacidad = cp;
        this.chofer =ch;
    }

    public String getplaca(){
        return this.placa;
    }
    public double getcombustible(){
        return this.combustible;
    }
    public int getcapacidad(){
        return this.capacidad;
    }
    public int getparadas(){
        return this.numParadas;
    }
    public void setcapacidad(int capacidad){
        this.capacidad=capacidad;
    }
    public void setcombustible(double capacidad){
        this.combustible=capacidad;
    }

    @Override
    public String toString() {
        return "Bus{" + "placa=" + placa + ", chofer=" + chofer + '}';
    }

    //COMPLETAR de acuerdo a las instrucciones

    public static ArrayList<Bus> cargarBuses(String buses)throws ChoferNoEncontrado{
        ArrayList<Bus> listadebuses=new ArrayList<>();
        try(BufferedReader br= new BufferedReader(new FileReader(buses))){
            String linea;
            linea=br.readLine();

                while ((linea=br.readLine())!= null){
                String[] arr= linea.split(";");
                String placa=arr[0];
                double combustible =Double.parseDouble(arr[1]);
                int paradas = Integer.parseInt(arr[2]);
                int capacidad=Integer.parseInt(arr[3]);
                String licencia=arr[4];
                   try{ String choferstring=arr[5];
                    Chofer chofercito=new Chofer(licencia, choferstring);
                    listadebuses.add(new Bus(placa,combustible,paradas,capacidad,chofercito));
                   }catch (ChoferNoEncontrado ce){
                  throw new ChoferNoEncontrado(placa);
                }

            }

        }catch(FileNotFoundException e){
            System.out.println("error de no encontrar archivo");
        }finally{
            return listadebuses;
        }
}

    public void run(){
        Random r= new Random();
        int cantpasajero=this.getcapacidad();
        int pasajerosFuera=r.nextInt(cantpasajero);
        int paradaActual=0;

        try(BufferedWriter bf= new BufferedWriter(new FileWriter("recorrido-"+this.getplaca()+".txt"))){
            bf.write("Chofer"+ chofer.getLicencia() + chofer.getNombre() );

        while(this.getcombustible()!= 0.0 || this.getcapacidad() != 0 || this.getparadas()==paradaActual){
            System.out.println("Avanzando a la siguiente parada con "+ this.getcapacidad() +"pasajeros. Combustible restante: "+ this.getcombustible()+ "\nLLegando a parada "+ paradaActual+", dejando a "+ pasajerosFuera + "pasajeros.");
            
            this.setcapacidad(cantpasajero-pasajerosFuera);
            double valorcombustible= this.getcombustible()-3.6;
            this.setcombustible(valorcombustible);

            paradaActual+=1;

            bf.write("Parada "+ paradaActual+":"+pasajerosFuera+"pasajeros\n");


        Thread.sleep(2000);
        }
        System.out.println("Bus"+this.getplaca()+"ha terminado su recorrido.");
    
    } catch (InterruptedException e) {
        e.printStackTrace();
    }catch(IOException e){
        System.out.println("error");
    
}
}
}