
package Modelo;

/**
 *
 * @author Victor
 */
public class Pozo {
    private Integer IdPozo;
    private String Nombre;
    private String Duenio;
    private String Logo;
    private String Portada;
    private Ubicacion ubicacionPozo;

    public Pozo() {
        
    }

    public Integer getIdPozo() {
        return IdPozo;
    }

    public void setIdPozo(Integer IdPozo) {
        this.IdPozo = IdPozo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDuenio() {
        return Duenio;
    }

    public void setDuenio(String Duenio) {
        this.Duenio = Duenio;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String Portada) {
        this.Portada = Portada;
    }

    public Ubicacion getUbicacionPozo() {
        return ubicacionPozo;
    }

    public void setUbicacionPozo(Ubicacion ubicacionPozo) {
        this.ubicacionPozo = ubicacionPozo;
    }

    @Override
    public String toString() {
        return Nombre;
    }
    
    
}
