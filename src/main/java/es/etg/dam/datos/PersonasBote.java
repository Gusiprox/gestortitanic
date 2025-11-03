package es.etg.dam.datos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonasBote {

    private static final String ESPACIO = " ";

    private String id;
    private int total, mujeres, hombres, ninos;

    public PersonasBote(String id, int mujeres, int hombres, int ninos) {
        this.id = id;
        this.mujeres = mujeres;
        this.hombres = hombres;
        this.ninos = ninos;
        total = mujeres + hombres + ninos;
    }

    public PersonasBote(String datos) {
        final int ID = 0, TOTAL = 1, MUJERES = 2, HOMBRES = 3, NINOS = 4;
        String[] listaDatos = datos.split(ESPACIO);

        id = listaDatos[ID];
        total = Integer.parseInt(listaDatos[TOTAL]);
        mujeres = Integer.parseInt(listaDatos[MUJERES]);
        hombres = Integer.parseInt(listaDatos[HOMBRES]);
        ninos = Integer.parseInt(listaDatos[NINOS].trim());
    }

    public String getData() {
        StringBuilder lector = new StringBuilder();

        lector.append(id.trim()).append(ESPACIO)
                .append(total).append(ESPACIO)
                .append(mujeres).append(ESPACIO)
                .append(hombres).append(ESPACIO)
                .append(ninos);

        return lector.toString();
    }
}
