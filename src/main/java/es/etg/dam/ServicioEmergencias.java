package es.etg.dam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.datos.PersonasBote;
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

            final int LAST_UNIQUE_NUMBER = 9;

            StringBuilder bote = new StringBuilder();
            bote.append(LETRA_BOTE);
            if (i <= LAST_UNIQUE_NUMBER) bote.append(RELLENO);

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

        final int SALIDA_CORRECTA = 0;

        String[] salidaBote = bote.readOut();

        return salidaBote[SALIDA_CORRECTA];
    }

    private static void generarInforme(String[] datos){

        final String FILE_FOLDER = "informe";
        final String FILE_NAME = "informe";
        final String TITLE = "Informe Botes Del Titanic";
        final String FINAL_MESSEGE = "Hecho por Erik De La Cruz y Aitor Rebato";

        final String MSG_TOTAL_GLOBAL = "Total ";

        final String MSG_TOTAL = "Total salvados ";
        final String MSG_MUJERES = "Mujeres ";
        final String MSG_VARONES = "Varones ";
        final String MSG_NiNOS = "Niños ";

        final int INDEX_TOTAL = 0;
        final int INDEX_MUJERES = 1;
        final int INDEX_HOMBRES = 2;
        final int INDEX_NINOS = 3;

        LocalDate diaHoy = LocalDate.now();
        DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalTime horaHoy = LocalTime.now();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringBuilder mensajeFechaYHora = new StringBuilder();
        mensajeFechaYHora.append("Ejecución realizada el día ").append(diaHoy.format(formatoDia)).append(" a las ").append(horaHoy.format(formatoHora));


        List<PersonasBote> personasBotes = new ArrayList<>();

        for (String dato: datos) {
            personasBotes.add(new PersonasBote(dato));
        }

        UniversalWriter writer = new MarkdownWriter();
        writer.setTitle(TITLE);
        writer.setFileFolder(FILE_FOLDER);
        writer.setFinal(FINAL_MESSEGE);

        writer.addContent(mensajeFechaYHora.toString());

        //En esta parte es necesaria una función que añada al writer los datos de las listas para no repetir codigo
        for (PersonasBote personaBote : personasBotes) {
            writer.addSectionTitle(personaBote.getId());
            writer.addListElement(MSG_TOTAL+ personaBote.getTotal());
            writer.addListSubElement1(MSG_MUJERES + personaBote.getMujeres());
            writer.addListSubElement1(MSG_VARONES + personaBote.getHombres());
            writer.addListSubElement1(MSG_NiNOS + personaBote.getNinos());
        }

        int[] recuentoTotal = getTotalPersonas(personasBotes);

        writer.addSectionTitle(MSG_TOTAL_GLOBAL);
        writer.addListElement(MSG_TOTAL+ recuentoTotal[INDEX_TOTAL]);
        writer.addListSubElement1(MSG_MUJERES + recuentoTotal[INDEX_MUJERES]);
        writer.addListSubElement1(MSG_VARONES + recuentoTotal[INDEX_HOMBRES]);
        writer.addListSubElement1(MSG_NiNOS + recuentoTotal[INDEX_NINOS]);

        writer.generateFile(FILE_NAME);
    }
    

    private static int[] getTotalPersonas(List<PersonasBote> personas){

        int totalPersonas = 0;
        int totalMujeres = 0;
        int totalHombres = 0;
        int totalNinos = 0;

        for (PersonasBote persona: personas) {
            
            totalPersonas += persona.getTotal();
            totalMujeres += persona.getMujeres();
            totalHombres += persona.getHombres();
            totalNinos += persona.getNinos();
        }

        int[] numerosTotales = {totalPersonas, totalMujeres, totalHombres, totalNinos};

        return numerosTotales;
    }

}