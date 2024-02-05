
package Controlador.TDA.Grafos;

import Controlador.TDA.Grafos.Excepcion.VerticeException;
import Controlador.TDA.ListaDinamica.ListaDinamica;

/**
 *
 * @author Victor
 */
public class GrafoDirigido extends Grafo{
    private Integer numeroVertices;
    private Integer numeroAristas;
    private ListaDinamica<Adyacencia> ListaAdyacencia[];
    
    public GrafoDirigido(Integer num_vertices) {
        this.numeroVertices = num_vertices;
        this.numeroAristas = 0;
        ListaAdyacencia = new ListaDinamica[num_vertices + 1];
        for (int i = 1; i <= this.numeroVertices; i++) {
            ListaAdyacencia[i] = new ListaDinamica<>();
        }
    }
    
    @Override
    public Integer num_vertice() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return numeroVertices;
    }

    @Override
    public Integer num_aristas() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return numeroAristas;
    }

    @Override
    public Boolean existe_arista(Integer v1, Integer v2) throws Exception{
        Boolean band = false;
        if(v1.intValue() <= numeroVertices && v2.intValue() <= numeroVertices){
            ListaDinamica<Adyacencia> listaA = ListaAdyacencia[v1];
            for(int i = 0; i < listaA.getLongitud(); i++){
                Adyacencia a = listaA.getInfo(i);
                if(a.getDestino().intValue() == v2.intValue()){
                    band = true;
                    break;
                }
            }
                
        }
        else
            throw new VerticeException();
        
        return band;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Double peso_arista(Integer v1, Integer v2) throws Exception {
        Double peso = Double.NaN;
        if (existe_arista(v1, v2)) {
            ListaDinamica<Adyacencia> listaA = ListaAdyacencia[v1];
            for (int i = 0; i < listaA.getLongitud(); i++) {
                Adyacencia a = listaA.getInfo(i);
                if (a.getDestino().intValue() == v2.intValue()) {
                    peso = a.getPeso();
                    break;
                }
            }
        }
        return peso;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2, Double peso) throws Exception {
        if (v1.intValue() <= numeroVertices && v2.intValue() <= numeroVertices) {
            if (!existe_arista(v1, v2)) {
                numeroAristas++;
                ListaAdyacencia[v1].Agregar(new Adyacencia(v2, peso));
            }
        } 
        else 
            throw new VerticeException();
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertar_arista(Integer v1, Integer v2) throws Exception{
        insertar_arista(v1, v2, Double.NaN);
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListaDinamica<Adyacencia> adycentes(Integer v1) throws Exception{
        return ListaAdyacencia[v1];
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ListaDinamica<Adyacencia>[] getListaAdyacencia() {
        return ListaAdyacencia;
    }

    public void setListaAdyacencia(ListaDinamica<Adyacencia>[] ListaAdyacencia) {
        this.ListaAdyacencia = ListaAdyacencia;
    }

    public Integer getNumeroVertices() {
        return numeroVertices;
    }

    public void setNumeroVertices(Integer numeroVertices) {
        this.numeroVertices = numeroVertices;
    }

    public Integer getNumeroAristas() {
        return numeroAristas;
    }

    public void setNumeroAristas(Integer numeroAristas) {
        this.numeroAristas = numeroAristas;
    }
    
}

