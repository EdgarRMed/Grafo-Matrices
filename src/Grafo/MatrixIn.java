package Grafo;

import java.io.Serializable;

// Clase para cada una de las entradas de la matriz de adyacencia
public class MatrixIn implements Serializable {
    public int state;
    public int weight;

    MatrixIn(int state, int weight){
        this.state = state; // Indica si tiene conexiones o no
        this.weight = weight;
    }
    MatrixIn(int state){
        this.state = state;
        this.weight = 0;
    }
}
