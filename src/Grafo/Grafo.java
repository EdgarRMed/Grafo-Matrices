package Grafo;

import Exceptions.NoExisteElementoException;
import ObjetosParaDijkstra.ElementoColaPriorizada;
import PilasYColasDinamicas.ColaDinamica;
import ObjetosParaDijkstra.ColaPriorizada;
import PilasYColasDinamicas.PilaDinamica;

import java.io.Serializable;

public class Grafo implements Serializable {
    PilaDinamica pila;
    ColaDinamica cola;
    MatrixIn mtx[][]; // Matriz de adyacencia
    Vertice AV []; // Lista que guarda los vértices
    int numVertices;
    public String stringDFS = "";
    public String stringBFS = "";

    public Grafo (int n){
        pila = new PilaDinamica();
        cola = new ColaDinamica();
        mtx = new MatrixIn[n][n];
        AV = new Vertice[n];
        numVertices =  0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                // se llena la matriz de 0s y un peso de 0 por default
                mtx[i][j] = new MatrixIn(0);
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

    public void setWeight(int row, int col, int weight){
        mtx[row][col].weight = weight;
        mtx[col][row].weight = weight;
    }

    public void insertArco (String name1, String name2, int weight) throws NoExisteElementoException{
        int i = searchVertice(name1);
        int j = searchVertice(name2);
        if (i != -1 && j != -1){
            if (mtx[i][j].state == 1 && mtx[j][i].state == 1)
                throw new  NoExisteElementoException("Ya existe una conexion entre estos nodos");
            else {
                mtx[i][j].state = 1;
                mtx[j][i].state = 1;
                setWeight(i,j,weight);
            }
        }
        else
            throw new NoExisteElementoException();

    }

    public void deleteArco (String name1, String name2) throws NoExisteElementoException{
        int i = searchVertice(name1);
        int j = searchVertice(name2);
        if (i != -1 && j != -1){
            if (mtx[i][j].state == 0 && mtx[j][i].state == 0)
                throw new NoExisteElementoException("No hay conexion entre estos nodos");
            else {
                mtx[i][j].state = 0;
                mtx[j][i].state = 0;
                setWeight(i,j,0);
            }
        }
        else
            throw new NoExisteElementoException();
    }

    public void deleteConextions(int position) throws NoExisteElementoException {
        for (int i = 0; i < numVertices; i++){ // -1 indica que se ha eliminado el vértice
                mtx[position][i].state = -1;
                mtx[i][position].state = -1;
        }
        // Una vez eliminado se procede a recorrer posiciones
        for (int i = 0; i < numVertices; i++){
            for (int j = 0; j < numVertices; j++) {
                if (mtx[i][j].state == -1){
                    for (int column = j; column < numVertices -1; column ++){ // Aquí se recorren columnas
                            mtx[i][column].state = mtx[i][column+1].state;
                    }
                }
                if (j == numVertices-1)
                    mtx[i][j].state = 0;
            }
        }
        for (int i = 0; i < numVertices; i++){
            for (int j = 0; j < numVertices; j++) {
                if (mtx[i][j].state == -1){
                    for (int row = i; row < numVertices -1; row++){ // Aquí se recorren filas
                        mtx[row][j].state = mtx[row+1][j].state;
                    }
                }
                if (i == numVertices-1)
                    mtx[i][j].state = 0;
            }
        }
    }

    public void deleteVertice(String name) throws NoExisteElementoException {
        int i = searchVertice(name);
        if (i != -1) {
            deleteConextions(i); // Se eliminan todas las conexiones con el vértice
            for (int j = i; j < numVertices-1; j++) // Se elimina el vertice de la lista
                    AV[j] = AV[j+1];
            numVertices --;
        }
        else
            throw new NoExisteElementoException();
    }

    // Algoritmos implementados al grafo................................................................................

    public void BreadthFirstSearch (){
        ColaDinamica <Vertice> cola = new ColaDinamica();
        Vertice aux = AV[0];
        if (aux.waiting){
            cola.encolar(aux);
            aux.waiting = false;
            while (!cola.isEmpty()){
                aux = cola.desencolar();
                stringBFS += aux;
                for (int i = aux.pos; i < numVertices; i++){
                    if (mtx[aux.pos][i].state == 1 && AV[i].waiting){
                        cola.encolar(AV[i]);
                        AV[i].waiting = false;
                    }
                }


            }
        }

    }

    public void depthFirstSearch(int position) { // Algoritmo recursivo
        AV[position].processed = true;
        stringDFS += AV[position];
        for (int i = 0;i < numVertices; i++){
            if (mtx[position][i].state == 1 && !AV[i].processed){
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
                if (mtx[i][j].state == 1)
                    contadorSalidas ++;
                if (mtx[j][i].state == 1)
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

    public void dijksta(String vertice) throws NoExisteElementoException {
        // Elementos a usar en el algoritmo
        Grafo arbolDelCaminoMasCorto = new Grafo(20);
        ColaPriorizada <ElementoColaPriorizada> colaPriorizada = new ColaPriorizada<>();
        int posicionVertice = searchVertice(vertice);
        ElementoColaPriorizada elementoColaPriorizada;

        // Comienza el algoritmo
        // Se inserta el vértice de inicio en el arbol y sus caminos en la cola
        arbolDelCaminoMasCorto.insertVertice(vertice);
        AV[posicionVertice].processed = true; // Se marca como procesado
        for (int columna = 0; columna < numVertices; columna++){
            // Si existe conexion entonces se agregan a la cola priorizada
            if (mtx[posicionVertice][columna].state == 1){
                elementoColaPriorizada = new ElementoColaPriorizada(posicionVertice,columna, mtx[posicionVertice][columna].weight, mtx[posicionVertice][columna].weight,0);
                colaPriorizada.encolar(elementoColaPriorizada);
            }
        }
        System.out.println(colaPriorizada);
        while (!colaPriorizada.isEmpty()){
            // Mientras la cola no esté vacía se busca al elemento con el menor peso y se elimina
            elementoColaPriorizada = colaPriorizada.buscarYEliminarElMenor();
            System.out.println(elementoColaPriorizada);
            // Se marca como ya recorrido
            if (!AV[elementoColaPriorizada.actual].processed) {
                AV[elementoColaPriorizada.actual].processed = true;
                // Se inseta en el arbol y se crea la conexion
                arbolDelCaminoMasCorto.insertVertice(AV[elementoColaPriorizada.actual].name);
                arbolDelCaminoMasCorto.insertArco(AV[elementoColaPriorizada.predecesor].name, AV[elementoColaPriorizada.actual].name, elementoColaPriorizada.peso);
                // Se busca sus caminos y se insertan en la cola
                int fila = elementoColaPriorizada.actual;
                for (int columna = 0; columna < numVertices; columna++) {
                    // Si existe conexion entonces se agregan a la cola priorizada
                    if (mtx[fila][columna].state == 1 && !AV[columna].processed) {
                        elementoColaPriorizada = new ElementoColaPriorizada(fila, columna, mtx[fila][columna].weight, mtx[fila][columna].weight, 0);
                        colaPriorizada.encolar(elementoColaPriorizada);
                    }
                }
            }
            System.out.println("\n"+arbolDelCaminoMasCorto);
        }
    }



    // algoritmo de control que imprime la matriz de adyacencia
    public void printMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(mtx[i][j].state + ","+mtx[i][j].weight+"   ");
            }
            System.out.println();
        }
    }

    // Fin algoritmos de implementación.................................................................................
    @Override
    public String toString() {
        String grafo = "";

        for (int i = 0; i < numVertices; i++){
            grafo += AV [i];
            for (int j = 0; j < numVertices; j++){
                if (mtx[i][j].state == 1){
                    grafo += AV[j].name + ",";
                }
            }
            grafo += "\n";
        }
        return grafo;
    }
}


