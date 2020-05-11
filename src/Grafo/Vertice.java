package Grafo;

import java.io.Serializable;

public class Vertice implements Serializable {
    String name;
    int pos;
    boolean wating;
    boolean processed;
    public Vertice(String name){
        this.name = name;
        pos = -1;
        wating = true;
        processed = false;
    }

    @Override
    public String toString() {
        return name + ":";
    }
}

