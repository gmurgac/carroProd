package cl.inacap.carroprod.dto;

import java.io.Serializable;

public class Lista implements Serializable {

    private String nombreLista;

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }
}
