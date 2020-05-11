package PilasYColasDinamicas;

import Grafo.Vertice;

import java.io.Serializable;

public class ColaDinamica implements Serializable { // Generic
    Nodo raiz;
    public  ColaDinamica(){
        raiz = null;
    }

    public boolean isEmpty(){
        if (raiz == null)
            return true;
        else
            return false;
    }

    public void encolar(Vertice vertice){
        Nodo nuevo = new Nodo(vertice);
        if(raiz != null)
            nuevo.next = raiz;
        raiz = nuevo;
    }

    public Vertice desencolar() {
        Vertice vertice = null;
        Nodo aux = raiz;
            while (aux != null) {
                if (aux.next == null) {
                    vertice = aux.vertice;
                    raiz = null;
                } else {
                    if (aux.next.next == null) {
                        vertice = aux.next.vertice;
                        aux.next = null;
                    }
                }
                aux = aux.next;
            }
        return vertice;
    }

    public String toString(){
        String cad = "";
        for (Nodo i = raiz; i!=null; i = i.next){
            cad += i;
        }
        return cad;
    }
}
