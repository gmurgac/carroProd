package cl.inacap.carroprod.dto;

import java.io.Serializable;

public class Lista implements Serializable {

    private String nombreLista;
    private String fechaCreacion;

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
