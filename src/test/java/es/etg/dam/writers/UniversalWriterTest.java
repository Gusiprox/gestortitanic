package es.etg.dam.writers;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UniversalWriterTest {
    
    @Test
    public void MarkdownWriterTest(){
        
        UniversalWriter writer = new MarkdownWriter();

        writer.setTitle("Titulo de prueba");

        writer.addSectionTitle("Titulo de la primera parte");
        writer.addContent("Contenido de la primera parte");

        writer.addSectionTitle("Titulo para ejemplo de lista");
        writer.addListElement("Primer dato de la lista");
        writer.addListElement("Segundo dato de la lista");
        writer.addListSubElement1("Dato interno de la lista");

        writer.setFinal("Agradecimientos a Junit");

        writer.setFileFolder("informe");

        assertTrue(writer.generateFile("Archivo_de_prueba"));
    }
}
