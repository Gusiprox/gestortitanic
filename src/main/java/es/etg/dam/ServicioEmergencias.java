package es.etg.dam;

import java.util.ArrayList;
import java.util.List;

import es.etg.dam.ejecuter.Command;
import es.etg.dam.ejecuter.Ejecuter;
import es.etg.dam.writers.MarkdownWriter;
import es.etg.dam.writers.UniversalWriter;

public class ServicioEmergencias {

    public static void main(String[] args) {

        final String[] BOTES_LISTA = getBotesList();

        List<Ejecuter> botes = new ArrayList<>();
        List<String> botes_resultado = new ArrayList<>();

        for (String bote : BOTES_LISTA) {
            
            botes.add(enviarBote(bote));
        }

        for (Ejecuter bote : botes) {
            botes_resultado.add(leerBote(bote));
        }

        generarInforme(botes_resultado.toArray(String[]::new));
    }

    private static String[] getBotesList(){

        final String RELLENO = "0";

        final String LETRA_BOTE = "B";
        final int NUMERO_BOTES = 20; //Tiene que ser menor o igual a 99

        List<String> botes = new ArrayList<>();
        

        for (int i = 1; i < NUMERO_BOTES +1; i++) {
            StringBuilder bote = new StringBuilder();
            bote.append(LETRA_BOTE);
            if (i <= 9) bote.append(RELLENO);

            bote.append(i);

            botes.add(bote.toString());
        }

        return botes.toArray(String[]::new);
    }

    private static Ejecuter enviarBote(String numBote){

        String[] COMANDO_BOTE = {"java", "-cp", "target/classes", "es.etg.dam.Bote", numBote};

        Ejecuter bote = new Command(COMANDO_BOTE);

        bote.exec();

        return bote;
    }

    private static String leerBote(Ejecuter bote){

        final int SALIDA_CORRECTA = 1;

        String[] salidaBote = bote.readOut();

        return salidaBote[SALIDA_CORRECTA];
    }

    private static void generarInforme(String[] datos){

        final String FILE_FOLDER = "informe";
        final String FILE_NAME = "informe";

        //Falta saber los datos exactos que se mandan aqui
        UniversalWriter writer = new MarkdownWriter();



        writer.setFileFolder(FILE_FOLDER);
        writer.generateFile(FILE_NAME);
    }
    

}