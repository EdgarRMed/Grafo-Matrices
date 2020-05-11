package PilasYColasDinamicas;

import Grafo.Vertice;

import java.io.Serializable;

public class PilaDinamica implements Serializable {
    Nodo raiz;
    public PilaDinamica(){raiz = null;}

    public boolean isEmpty(){
        if (raiz == null)
            return true;
        else
            return false;
    }

    public void push(Vertice vertice){
        Nodo nuevo = new Nodo(vertice);
        if (raiz != null)
            nuevo.next = raiz;
        raiz = nuevo;
    }

    public Vertice pop(){
        Nodo aux;
        Vertice vertice = null;
        if (raiz == null)
            System.out.println("La pila está vacía");
        else {
            aux = raiz;
            vertice = aux.vertice;
            raiz = raiz.next;
            aux.next = null;
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
