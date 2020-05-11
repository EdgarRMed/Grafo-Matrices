package Grafo;

import Exceptions.NoExisteElementoException;
import PilasYColasDinamicas.ColaDinamica;
import PilasYColasDinamicas.PilaDinamica;

import java.io.Serializable;

public class Grafo implements Serializable {
    PilaDinamica pila;
    ColaDinamica cola;
    int MTX[][]; // Matriz de adyacencia
    Vertice AV []; // Lista que guarda los vértices
    int numVertices;
    public String stringDFS = "";
    public String stringBFS = "";

    public Grafo (int n){
        pila = new PilaDinamica();
        cola = new ColaDinamica();
        MTX = new int[n][n];
        AV = new Vertice[n];
        numVertices = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                MTX[i][j] = 0;// se llena la matriz de 0s por default
            }
        }
    }

    public void insertVertice (String name) throws NoExisteElementoException{
        Vertice v  = new Vertice(name);
        int i = searchVertice(name);
        if (i == -1){
            v.pos = numVertices;
            AV[numVertices++] = v;
        }
        else {
            throw new NoExisteElementoException("Ya existe el elemento");
        }

    }

    public int searchVertice (String name){
        int i;
        for (i = 0; i < numVertices; i++){
            if (AV[i].name.equals(name))
                return i;
        }
        return -1;
    }

    public void insertArco (String name1, String name2) throws NoExisteElementoException{
        int i = searchVertice(name1);
        int j = searchVertice(name2);
        if (i != -1 && j != -1){
            if (MTX [i][j] == 1 && MTX [j][i] == 1)
                throw new  NoExisteElementoException("Ya existe una conexion entre estos nodos");
            else {
                MTX[i][j] = 1;
                MTX[j][i] = 1;
            }
        }
        else
            throw new NoExisteElementoException();

    }

    public void deleteArco (String name1, String name2) throws NoExisteElementoException{
        int i = searchVertice(name1);
        int j = searchVertice(name2);
        if (i != -1 && j != -1){
            if (MTX [i][j] == 0 && MTX[j][i] == 0)
                throw new NoExisteElementoException("No hay conexion entre estos nodos");
            else {
                MTX[i][j] = 0;
                MTX[j][i] = 0;
            }
        }
        else
            throw new NoExisteElementoException();
    }

    public void deleteConextions(int position) throws NoExisteElementoException {
        for (int i = 0; i < numVertices; i++){
                MTX[position][i] = -1;
                MTX[i][position] = -1;
        }
    }

    // Dado que la matriz de adycencnia no es dinamica solo se pueden eliminar vértices con conexiones ubicados al final
    public void deleteVertice(String name) throws NoExisteElementoException {
        int i = searchVertice(name);
        if (i != -1) {
            deleteConextions(i); // Se eliminan todas las conexiones con el vértice
            for (int j = i; j < numVertices-1; j++)
                    AV[j] = AV[j+1];
            numVertices --;
        }
        else
            throw new NoExisteElementoException();
    }

    // Algoritmos implementados al grafo

    public void BreadthFirstSearch (){
        ColaDinamica cola = new ColaDinamica();
        Vertice aux = AV[0];
        if (aux.wating){
            cola.encolar(aux);
            aux.wating = false;
            while (!cola.isEmpty()){
                aux = cola.desencolar();
                stringBFS += aux;
                for (int i = aux.pos; i < numVertices; i++){
                    if (MTX[aux.pos][i] == 1 && AV[i].wating){
                        cola.encolar(AV[i]);
                        AV[i].wating = false;
                    }
                }


            }
        }

    }

    public void depthFirstSearch(int position) { // Algoritmo recursivo
        AV[position].processed = true;
        stringDFS += AV[position];
        for (int i = 0;i < numVertices; i++){
            if (MTX[position][i] == 1 && !AV[i].processed){
                depthFirstSearch(i);
            }
        }
    }


    public String gradoEntradaSalida (){ // Regresa el grado de salida y entrada de cada nodo
        String cad = "";
        int contadorEntradas = 0;
        int contadorSalidas = 0;
        for (int i = 0; i < numVertices; i++ ) {
            for (int j = 0; j < numVertices; j++ ) {
                if (MTX[i][j] == 1)
                    contadorSalidas ++;
                if (MTX[j][i] == 1)
                   contadorEntradas ++;
            }
            cad += ("Entrada "+AV[i]+contadorEntradas);
            cad += ("Salida "+": "+contadorSalidas);
            cad += "\n";
            contadorEntradas = 0;
            contadorSalidas = 0;
        }
        return cad;
    }

    public String dijksta(){

    return null;
    }

    // Fin algoritmos de implementación
    @Override
    public String toString() {
        String grafo = "";

        for (int i = 0; i < numVertices; i++){
            grafo += AV [i];
            for (int j = 0; j < numVertices; j++){
                if (MTX[i][j] == 1){
                    grafo += AV[j].name + ",";
                }
            }
            grafo += "\n";
        }
        return grafo;
    }
}


