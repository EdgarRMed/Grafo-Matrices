package PilasYColasDinamicas;
import Grafo.Vertice;
import java.io.Serializable;

public class ColaDinamica <T> implements Serializable { // Cola genérica para objetos
    public Nodo raiz;
    public  ColaDinamica(){
        raiz = null;
    }

    public boolean isEmpty(){
        if (raiz == null)
            return true;
        else
            return false;
    }

    public void encolar(T object){
        Nodo nuevo = new Nodo(object);
        if(raiz != null)
            nuevo.next = raiz;
        raiz = nuevo;
    }

    public T desencolar() {
        T object = null;
        Nodo aux = raiz;
            while (aux != null) {
                if (aux.next == null) {
                    object = (T) aux.object;
                    raiz = null;
                } else {
                    if (aux.next.next == null) {
                        object = (T) aux.next.object;
                        aux.next = null;
                    }
                }
                aux = aux.next;
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
