package es.etg.dam;

import java.util.Random;

import es.etg.dam.datos.PersonasBote;

public class Bote {

    private static final int PERSONAS_MAXIMAS = 100, ID = 0;
    private static String id;
    private static int mujeres, hombres, ninos;
    private static Random random;
    private static PersonasBote personasBote;

    public static void main(String[] args) {
        id = args[ID];
        random = new Random();
        generarPersonas();
        personasBote = new PersonasBote(id, mujeres, hombres, ninos);
        System.out.println(personasBote.getData());
    }

    private static void generarPersonas() {
        int personasRestantes = PERSONAS_MAXIMAS;

        mujeres = generarNumeroAleatorio(personasRestantes);
        personasRestantes = personasRestantes - mujeres;

        hombres = generarNumeroAleatorio(personasRestantes);
        personasRestantes = personasRestantes - hombres;

        ninos = generarNumeroAleatorio(personasRestantes);
    }

    private static int generarNumeroAleatorio(int maximo) {
        if (maximo <= 0) {
            return 0;
        }
        return random.nextInt(maximo + 1);
    }
}
