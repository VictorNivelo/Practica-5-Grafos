
package Controlador.TDA.Grafos;

import Controlador.TDA.ListaDinamica.ListaDinamica;
import java.io.FileWriter;

/**
 *
 * @author Victor
 */
public class DibujarGrafo {

    String URL = "d3/grafo.js";

    public void updateFile(Grafo graph) throws Exception {
        StringBuilder paint = new StringBuilder();

        paint.append("var nodes = [");
        
        for (int i = 1; i <= graph.num_vertice(); i++) {
            paint.append("{id: ").append(i).append(", label: 'V").append(i).append("'},");
        }
        
        paint.append("];\n");

        paint.append("var edges = [");
        
        for (int i = 1; i <= graph.num_vertice(); i++) {
            try {
                ListaDinamica<Adyacencia> list = graph.adycentes(i);
                for (int j = 0; j < list.getLongitud(); j++) {
                    Adyacencia a = list.getInfo(j);
                    paint.append("{from: ").append(i).append(", to: ").append(a.getDestino());
                    if (!Double.isNaN(a.getPeso())) {
                        paint.append(", label: '").append(a.getPeso()).append("'");
                    }
                    paint.append("},");
                }
            } 
            catch (Exception e) {
                
            }
        }
        
        paint.append("];\n");

        paint.append("var container = document.getElementById(\"mynetwork\");\n");
        paint.append("var data = {\n");
        paint.append("  nodes: nodes,\n");
        paint.append("  edges: edges,\n");
        paint.append("};\n");
        paint.append("var options = {};\n");
        paint.append("var network = new vis.Network(container, data, options);");

        FileWriter load = new FileWriter(URL);
        load.write(paint.toString());
        load.close();
    }
    
}

