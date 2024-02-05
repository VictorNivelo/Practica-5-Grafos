
package Controlador.TDA.Grafos;

/**
 *
 * @author Victor
 */
public class Adyacencia {
    private Integer Destino;
    private Double Peso;

    public Adyacencia() {
        
    }

    public Adyacencia(Integer Destino, Double Peso) {
        this.Destino = Destino;
        this.Peso = Peso;
    }    

    public Integer getDestino() {
        return Destino;
    }

    public void setDestino(Integer Destino) {
        this.Destino = Destino;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double Peso) {
        this.Peso = Peso;
    }
    
}
