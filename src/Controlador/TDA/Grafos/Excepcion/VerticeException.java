
package Controlador.TDA.Grafos.Excepcion;

/**
 *
 * @author Victor
 */
public class VerticeException extends Exception {

    public VerticeException(String msg) {
        super(msg);
    }

    public VerticeException() {
        super("Vertice fuera de rango");
    }
    
}
