
package Controlador.TDA.Grafos;

import Controlador.TDA.Grafos.Excepcion.EtiquetaNoEstablecida;
import Controlador.TDA.Grafos.Excepcion.VerticeException;
import Controlador.TDA.ListaDinamica.ListaDinamica;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 *
 * @author Victor
 * @param <E>
 */
public class GradoDirigidoEtiquetado<E> extends GrafoDirigido{
    
    protected E etiquetas[];
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;
    
    public GradoDirigidoEtiquetado(Integer numVert, Class clazz) {
        super(numVert);
        this.clazz = clazz;
        etiquetas = (E[]) Array.newInstance(clazz, numVert);
        dicVertices = new HashMap(numVert);
    }
    
    private Integer getIndiceE(E etiqueta) throws Exception {
        Integer aux = dicVertices.get(etiqueta);
        if (aux != null) {
            return aux;
        } 
        else {
            throw new VerticeException("No se encuentra asociado a esta etiqueta");
        }
    }

    public E getEtiquetaE(Integer codigo) throws Exception {
        if (codigo <= num_vertice()) {
            return etiquetas[codigo];
        } 
        else {
            throw new EtiquetaNoEstablecida();
        }
//        return etiquetas[codigo - 1];
    }

    public Boolean isEdgeE(E o, E d) throws Exception {
        if (isAllLabelsGraph()) {
            return existe_arista(getIndiceE(o), getIndiceE(d));
        } 
        else {
            throw new EtiquetaNoEstablecida();
        }
    }
    
    public void insertEdgeE(E o, E d) throws Exception {
        if (isAllLabelsGraph()) {
            insertar_arista(getIndiceE(o), getIndiceE(d), Double.NaN);
        } 
        else {
            throw new EtiquetaNoEstablecida();
        }
    }

    public void insertEdgeE(E o, E d, Double weight) throws Exception {
        if (isAllLabelsGraph()) {
            insertar_arista(getIndiceE(o), getIndiceE(d), weight);
        } 
        else {
            throw new EtiquetaNoEstablecida();
        }
    }

    public ListaDinamica<Adyacencia> ListaAdyacencias(E etiqueta) throws Exception {
        if (isAllLabelsGraph()) {
            return adycentes(getIndiceE(etiqueta));
        } 
        else {
            throw new EtiquetaNoEstablecida();
        }
    }
    
    public void etiquetarVertice(Integer vertice, E etiqueta) throws Exception {
        etiquetas[vertice] = etiqueta;
        dicVertices.put(etiqueta, vertice);
    }
    
    public Boolean isAllLabelsGraph() {
        Boolean band = true;
        for (int i = 1; i < etiquetas.length; i++) {
            if (etiquetas[i] == null) {
                band = false;
                break;
            }
        }
        return band;
    }
    
    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFO").append("\n");
        try {
            for (int i = 1; i <= num_vertice(); i++) {
                grafo.append("[").append(i).append("] = ").append(getEtiquetaE(i)).append("\n");
                ListaDinamica<Adyacencia> list = adycentes(i);
                for (int j = 0; j < list.getLongitud(); j++) {
                    Adyacencia a = list.getInfo(j);
                    grafo.append("ady [").append(a.getDestino()).append("] - ").append(getEtiquetaE(a.getDestino())).append(" peso ").append(a.getPeso()).append("\n");
                }
            }
        } 
        catch (Exception e) {
            
        }
        return grafo.toString(); 
    }
    
}
