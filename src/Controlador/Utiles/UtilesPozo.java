
package Controlador.Utiles;

import Controlador.Dao.Modelo.PozoDao;
import Controlador.TDA.Grafos.Excepcion.VerticeException;
import Controlador.TDA.Grafos.GrafoDirigidoEtiquetado;
import Controlador.TDA.ListaDinamica.ListaDinamica;
import Modelo.Pozo;
import java.io.FileWriter;
import javax.swing.JComboBox;

/**
 *
 * @author Victor
 */
public class UtilesPozo {
    public static void crearMapaEscuela(GrafoDirigidoEtiquetado<Pozo> ge) throws VerticeException , Exception {
         String maps = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                + "\n"
                + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                + "\n"
                + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                + "            }).addTo(map);" + "\n";
        for (int i = 1; i <= ge.num_vertice(); i++) {
            Pozo ec = ge.getEtiquetaE(i);
            maps += "L.marker([" + ec.getUbicacionPozo().getLongitud() + ", " + ec.getUbicacionPozo().getLatitud() + "]).addTo(map)" + "\n";
            maps += ".bindPopup(\"" + ec.toString() + "\")" + "\n";
            maps += ".openPopup();" + "\n";
        }
        FileWriter file = new FileWriter("mapas/mapa.js");
        file.write(maps);
        file.close();

    }

    public static void cargarComboEscuela(JComboBox cbx) throws Exception {
        cbx.removeAllItems();
        ListaDinamica<Pozo> list = new PozoDao().getListaPozo();
        for (int i = 0; i < list.getLongitud(); i++) {
            cbx.addItem(list.getInfo(i));
        }
    }

    public static Double calcularDistanciaEscuelas(Pozo o, Pozo d) {
        Double dist = UtilesControlador.coordGpsToKm(
                o.getUbicacionPozo().getLatitud(),
                o.getUbicacionPozo().getLongitud(),
                d.getUbicacionPozo().getLatitud(), 
                d.getUbicacionPozo().getLongitud());
        return redondear(dist);
    }
    
    public static Double redondear(Double x){
        Double d = Math.round(x *100.0)/100.0;
        return d;
    }
}
