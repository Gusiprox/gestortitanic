package es.etg.dam.datos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonasBote {

    private String id;
    private int total, mujeres, hombres, ninos;

    public void leerDatos(String id, int total, int mujeres, int hombres, int ninos) {
        this.id = id;
        this.total = total;
        this.mujeres = mujeres;
        this.hombres = hombres;
        this.ninos = ninos;
    }
}