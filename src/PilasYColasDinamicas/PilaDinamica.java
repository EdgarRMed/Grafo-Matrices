package PilasYColasDinamicas;

import Grafo.Vertice;

import java.io.Serializable;

public class PilaDinamica <T> implements Serializable { // Pila genérica para objetos
    Nodo raiz;
    public PilaDinamica(){raiz = null;}

    public boolean isEmpty(){
        if (raiz == null)
            return true;
        else
            return false;
    }

    public void push(T object){
        Nodo nuevo = new Nodo(object);
        if (raiz != null)
            nuevo.next = raiz;
        raiz = nuevo;
    }

    public T pop(){
        Nodo aux;
        T object = null;
        if (raiz == null)
            System.out.println("La pila está vacía");
        else {
            aux = raiz;
            object = (T) aux.object;
            raiz = raiz.next;
            aux.next = null;
        }
        return object;
    }

    public String toString(){
        String cad = "";
        for (Nodo i = raiz; i!=null; i = i.next){
            cad += i;
        }
        return cad;
    }

}
