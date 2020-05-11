package Grafo;

import java.io.Serializable;

public class Vertice implements Serializable {
    String name;
    int pos;
    public Vertice(String name){
        this.name = name;
        pos = -1;
    }

    @Override
    public String toString() {
        return name + ":";
    }
}

