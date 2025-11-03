package es.etg.dam.writers;

public abstract interface UniversalWriter{

    void setTitle(String title);
    void setContent(String content);
    void addContent(String content);
    void addSectionTitle(String title);
    void addListElement(String listFirst);
    void addListSubElement1(String listFirst);
    void setFinal(String finalPart);
    void setFileFolder(String fileFolder);
    boolean generateFile(String fileNoExtension);
}