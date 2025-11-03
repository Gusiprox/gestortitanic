package es.etg.dam.ejecuter;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EjecuterTest {
    
    @Test
    public void commandTestSimple(){

        final String[] comando = {"echo", "hola"};

        final int SALIDA_CORRECTA = 0;
        final int SALIDA_ERRONEA = 1;

        Ejecuter process = new Command(comando);

        process.exec();

        String salidaCorrecta = process.readOut()[SALIDA_CORRECTA];
        String salidaErronea = process.readOut()[SALIDA_ERRONEA];

        assertEquals("hola", salidaCorrecta.trim());
        assertEquals("", salidaErronea.trim());

    }

}
