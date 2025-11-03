package es.etg.dam;

import java.util.Random;

import es.etg.dam.datos.PersonasBote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bote {

    private static final int PERSONAS_MAXIMAS = 100;
    private String id;
    private int mujeres, hombres, ninos, total;
    private Random random;
    private PersonasBote personasBote;

    public Bote(String id, PersonasBote personasBote) {
        this.id = id;
        this.personasBote = personasBote;
        this.random = new Random();
        generarPersonas();
        enviarDatos();
    }

    private void generarPersonas() {
        int personasRestantes = PERSONAS_MAXIMAS;

        mujeres = generarNumeroAleatorio(personasRestantes);
        personasRestantes = personasRestantes - mujeres;

        hombres = generarNumeroAleatorio(personasRestantes);
        personasRestantes = personasRestantes - hombres;

        ninos = generarNumeroAleatorio(personasRestantes);

        total = mujeres + hombres + ninos;
    }

    private void enviarDatos() {
        personasBote.leerDatos(id, total, mujeres, hombres, ninos);
    }

    private int generarNumeroAleatorio(int maximo) {
        if (maximo <= 0) {
            return 0;
        }
        return random.nextInt(maximo + 1);
    }
}