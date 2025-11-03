package es.etg.dam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import es.etg.dam.datos.PersonasBote;
import es.etg.dam.ejecuter.Command;
import es.etg.dam.ejecuter.Ejecuter;

public class BoteTest {
    
    @Test
    public void LanzarBoteTest(){

        final String[] COMANDO_BOTE = {"java", "-cp", "target/classes", "es.etg.dam.Bote", "B02"};

        final int SALIDA_BUENA = 0;

        Ejecuter bote = new Command(COMANDO_BOTE);

        bote.exec();

        String[] salida = bote.readOut();

        PersonasBote persBote = new PersonasBote(salida[SALIDA_BUENA]);

        assertEquals("B02", persBote.getId());
        assertTrue(persBote.getTotal() < 100);

    }
}