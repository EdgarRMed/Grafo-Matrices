package Grafo;

import java.io.Serializable;

// Clase para cada una de las entradas de la matriz de adyacencia
public class MatrixIn implements Serializable {
    public int state;
    public int weight;
    public boolean processed;
    public int row,col;

    MatrixIn(int state, int weight){
        this.state = state; // Indica si tiene conexiones o no
        this.weight = weight;
        this.processed = false;
    }
    public MatrixIn(int state){
        this.state = state;
        this.weight = 0;
        this.processed = false;
    }

    public String toString (){
        return Integer.toString(row) + "," + Integer.toString(col);
    }
}
