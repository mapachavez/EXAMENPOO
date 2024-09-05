package espol.poo.excepciones;

import java.io.IOException;

public class ChoferNoEncontrado extends IOException {
    public ChoferNoEncontrado(String placa){
        super("Bus con placa "+placa+ " no tiene asignado chofer. No puede iniciar su recorrido.");
    }
}
