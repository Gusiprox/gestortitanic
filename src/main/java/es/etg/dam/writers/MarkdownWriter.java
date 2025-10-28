package es.etg.dam.writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MarkdownWriter implements UniversalWriter{

    private final static String TITLE_ANOTATION = "# ";
    private final static String BLOCKQUOTE_ANOTATION = "> ";
    private final static String MD_EXTENSION = ".md";
    private final static String FOLDER_SEPARATOR = "/";
    private final static String SALTO_DE_LINEA = "\n";


    private String title;
    private String content;
    private String finalPart;
    private String fileFolder = "files/";



    @Override
    public void setTitle(String title){
        this.title = TITLE_ANOTATION + title;
    }

    @Override
    public void setContent(String content){
        this.content = content;
    }
    
    @Override
    public void setFinal(String finalPart){
        this.finalPart = BLOCKQUOTE_ANOTATION + finalPart;
    }

    @Override
    public void setFileFolder(String fileFolder){
        this.fileFolder = fileFolder;
    }

    @Override
    public boolean generateFile(String fileNoExtension){

        final boolean ERROR_VALUE = false;
        final boolean SUSCES_VALUE = true;

        final String FILE_CONTENT_ERROR_VALUE = "";

        String fileContent = joinAll();
        if (fileContent.equals(FILE_CONTENT_ERROR_VALUE)) return ERROR_VALUE;

        StringBuilder filePath = new StringBuilder();

        filePath.append(fileFolder).append(FOLDER_SEPARATOR).append(fileNoExtension.trim()).append(MD_EXTENSION);

        //Me pueden devolver valores raros en el String, solucionarlo a la proxima
        File file = new File(filePath.toString());
        System.out.println(file.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()))){

            writer.write(fileContent);
            return SUSCES_VALUE;

        } catch (Exception e) {
            //System.err.println("e");
            return ERROR_VALUE;
        }

    }

    private String joinAll(){

        final String ERROR_VALUE = "";

        StringBuilder fileContent = new StringBuilder();

        if (title != null) {
            fileContent.append(title).append(SALTO_DE_LINEA).append(SALTO_DE_LINEA);
        }

        if (content != null) {
            fileContent.append(content).append(SALTO_DE_LINEA);
        }

        if (finalPart != null) {
            fileContent.append(finalPart).append(SALTO_DE_LINEA);
        }

        if (fileContent.isEmpty()) {
            return ERROR_VALUE;
        }

        return fileContent.toString();

    }
}
