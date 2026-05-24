/**
 *
 * @author Jose Luis Segura Fernandez, Jorge Espejo Martinez
 */
import java.util.LinkedList;
public class Cola {
    //Atributos
    private LinkedList<Object> tCola;
    //Constructores
    public Cola() {
        //Entorno
        //Algoritmo
        this.tCola = new LinkedList<>();
    }
    //Métodos
    public void meterEnCola(Object obj) {
        //Entorno
        //Algoritmo
        this.tCola.addLast(obj);
    }
    public Object sacarDeCola() {
        //Entorno
        Object elemento;
        //Algoritmo
        elemento = null;
        if (!esColaVacia()) {
            elemento = tCola.removeFirst();
        }//Fin Si
        return elemento;
    }
    public boolean esColaVacia() {
        //Entorno
        //Algoritmo
        return (this.tCola == null || this.tCola.isEmpty());
    }
}
