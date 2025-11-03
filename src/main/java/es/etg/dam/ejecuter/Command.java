package es.etg.dam.ejecuter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Command implements Ejecuter {

    final String SALTO_DE_LINEA = "\n";
    final String DEFAULT_EXIT = "";

    private String exit = DEFAULT_EXIT;
    private String exitError = DEFAULT_EXIT;

    private boolean doWrite = false;
    private String writeContent;

    private final String[] COMANDO;

    private Process processLocal;

    public Command(String[] comando) {
        this.COMANDO = comando;
    }

    @Override
    public String[] readOut(){

        final String ERROR_VALUE = "";
        final Exception FAILED_PROCESS = new Exception("Comando no encontrado ");
        final String SUSCES_PROCESS = "Proceso ejecutado correctamente";


        try {
            String output;
            String errOutput;

            if (processLocal ==null) throw FAILED_PROCESS;

            errOutput = read(processLocal.getErrorStream());
            output = read(processLocal.getInputStream());

            int exitVal = processLocal.waitFor();
            if (exitVal == 0) {

                this.exit = output;

            } else {

                this.exitError = errOutput;
            }
            if (exit.trim().equals(DEFAULT_EXIT)) exit = SUSCES_PROCESS;

            return new String[]{exit, exitError};
            
        } catch (IOException | InterruptedException e) {
            return new String[]{ERROR_VALUE, exitError};
        } catch (Exception e){
            return new String[]{e.toString(), exitError};
        }

    }

    @Override
    public void writeIn(String writeContent) {
        doWrite = true;
        this.writeContent = writeContent;
    }

    @Override
    public void exec(){

        final String ERROR_VALUE = "Error al ejecutar el comando ";

        try {

            Process process = Runtime.getRuntime().exec(COMANDO);
            processLocal = process;

            if (doWrite) {
                write(process.getOutputStream(), writeContent);
            }

        } catch (IOException e) {

            this.exitError = ERROR_VALUE + e;

        }
    }

    private String read(InputStream is) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append(SALTO_DE_LINEA);
            }

            return output.toString();

        }
    }

    private void write(OutputStream os, String mensaje) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {

            writer.write(mensaje);
            writer.flush();
        }
    }
}
