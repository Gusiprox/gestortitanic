package es.etg.dam.writers;

public abstract interface UniversalWriter{

    void setTitle(String title);
    void setContent(String content);
    void setFinal(String finalPart);
    void setFileFolder(String fileFolder);
    boolean generateFile(String fileNoExtension);
}