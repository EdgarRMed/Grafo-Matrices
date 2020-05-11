package PilasYColasDinamicas;

import Grafo.Vertice;

import java.io.Serializable;

public class Nodo implements Serializable {
    Vertice vertice;
    Nodo next;
    public Nodo(Vertice vertice){
        this.vertice = vertice;
        next = null;
    }

    public String toString(){
        String cad = "";
        cad+=" ->";
        cad+= vertice;
        return cad;
    }
}
