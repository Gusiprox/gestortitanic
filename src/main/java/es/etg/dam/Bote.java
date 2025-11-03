package es.etg.dam;

import java.util.Random;

import es.etg.dam.datos.PersonasBote;

public class Bote {

    private static final int ID = 0;
    private static String id;
    private static int mujeres, hombres, ninos;
    private static Random random;
    private static PersonasBote personasBote;

    public static void main(String[] args) throws InterruptedException{
        id = args[ID];
        random = new Random();
        generarPersonas();
        personasBote = new PersonasBote(id, mujeres, hombres, ninos);

        Thread.sleep(getTiempoEspera());

        System.out.print(personasBote.getData());
    }

    private static int getTiempoEspera(){

        final int TIEMPO_ESPERA_MIN = 2000;
        final int TIEMPO_ESPERA_MAX = 6000;

        int tiempoEspera = random.nextInt(TIEMPO_ESPERA_MAX - TIEMPO_ESPERA_MIN + 1) + TIEMPO_ESPERA_MIN;

        return tiempoEspera;
    }

    private static void generarPersonas() {
        final int PERSONAS_MAXIMAS = 100;
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
