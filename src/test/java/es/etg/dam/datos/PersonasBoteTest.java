package es.etg.dam.datos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PersonasBoteTest {

    @Test
    void testConstructorConParametros() {
        String id = "B00";
        int mujeres = 50, hombres = 30, ninos = 20;
        PersonasBote personasBote = new PersonasBote(id, mujeres, hombres, ninos);

        assertEquals(id, personasBote.getId());
        assertEquals(100, personasBote.getTotal());
        assertEquals(mujeres, personasBote.getMujeres());
        assertEquals(hombres, personasBote.getHombres());
        assertEquals(ninos, personasBote.getNinos());
    }

    @Test
    void testConstructorConString() {
        String datos = "B00 100 50 30 20";
        PersonasBote personasBote = new PersonasBote(datos);

        assertEquals("B00", personasBote.getId());
        assertEquals(100, personasBote.getTotal());
        assertEquals(50, personasBote.getMujeres());
        assertEquals(30, personasBote.getHombres());
        assertEquals(20, personasBote.getNinos());
    }

    @Test
    void testGetData() {
        String id = "B00";
        int mujeres = 50, hombres = 30, ninos = 20;
        PersonasBote personasBote = new PersonasBote(id, mujeres, hombres, ninos);
        String resultadoEsperado = "B00 100 50 30 20";
        
        assertEquals(resultadoEsperado, personasBote.getData());
    }

    @Test
    void testGetPutData() {
        String datos = "B00 100 50 30 20";
        PersonasBote personasBote = new PersonasBote(datos);
        String salida = personasBote.getData();
        PersonasBote personasBote2 = new PersonasBote(salida);

        assertEquals(datos, personasBote2.getData());
    }
}
