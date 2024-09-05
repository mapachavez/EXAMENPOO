package espol.poo.modelo;

public class Chofer {
    private String licencia;
    private String nombre;

    public Chofer(String licencia, String nombre) {
        this.licencia = licencia;
        this.nombre = nombre;
    }

    public String getLicencia(){
        return this.licencia;
    }
    public String getNombre(){
        return this.nombre;
    }
    @Override
    public String toString() {
        return "Chofer " + licencia + " - " + nombre ;
    }
    
}
