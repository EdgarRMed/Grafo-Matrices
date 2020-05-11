package Grafo;

import Exceptions.NoExisteElementoException;

import java.io.Serializable;

public class Grafo implements Serializable {
    int MA [][];
    Vertice AV [];
    int numVertices;

    public Grafo (int n){
        MA = new int[n][n];
        AV = new Vertice[n];
        numVertices = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                MA[i][j] = 0;
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
            if (MA [i][j] == 1 && MA [j][i] == 1)
                throw new  NoExisteElementoException("Ya existe una conexion entre estos nodos");
            else {
                MA[i][j] = 1;
                MA[j][i] = 1;
            }
        }
        else
            throw new NoExisteElementoException();

    }

    public void deleteArco (String name1, String name2) throws NoExisteElementoException{
        int i = searchVertice(name1);
        int j = searchVertice(name2);
        if (i != -1 && j != -1){
            if (MA [i][j] == 0 && MA [j][i] == 0)
                throw new NoExisteElementoException("No hay conexion entre estos nodos");
            else {
                MA[i][j] = 0;
                MA[j][i] = 0;
            }
        }
        else
            throw new NoExisteElementoException();
    }

    public void deleteVertice(String name) throws NoExisteElementoException {
        int i = searchVertice(name);
        if (i != -1) {
            for (int k = 0; k < numVertices; k++) {
                MA[i][k] = 0;
                MA[k][i] = 0;
            }

            for (int j = i; j < numVertices-1; j++)
                    AV[j] = AV[j+1];
            numVertices --;
        }
        else
            throw new NoExisteElementoException();
    }

    @Override
    public String toString() {
        String grafo = "";

        for (int i = 0; i < numVertices; i++){
            grafo += AV [i];
            for (int j = 0; j < numVertices; j++){
                if (MA[i][j] == 1){
                    grafo += AV[j].name + ",";
                }
            }
            grafo += "\n";
        }
        return grafo;
    }
}


