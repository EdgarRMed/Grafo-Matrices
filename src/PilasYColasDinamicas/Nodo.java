package PilasYColasDinamicas;

import Grafo.Vertice;

import java.io.Serializable;

public class Nodo <T> implements Serializable { // Nodo genérico para objetos
    public T object;
    public Nodo next;
    public Nodo(T object){
        this.object = object;
        next = null;
    }

    public String toString(){
        String cad = "";
        cad+=" ->";
        cad+= object;
        return cad;
    }
}
