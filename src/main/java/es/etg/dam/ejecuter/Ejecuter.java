package es.etg.dam.ejecuter;

public abstract interface Ejecuter{
    
    String[] readOut();
    void writeIn(String writeContent);
    void exec();
}
