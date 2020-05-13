package ObjetosParaDijkstra;

import java.io.Serializable;

public class ElementoColaPriorizada implements Serializable {
    public int predecesor;
    public int actual;
    public int peso;
    int pesoAcumulado;
    public int numIteraciones;

    public ElementoColaPriorizada(){}

    public ElementoColaPriorizada(int predecesor, int actual, int peso, int pesoAcumulado, int numIteraciones){
        this.predecesor = predecesor;
        this.actual = actual;
        this.peso = peso;
        this.pesoAcumulado += pesoAcumulado;
        this.numIteraciones = numIteraciones;
    }

    public String toString(){
        return "[" + Integer.toString(peso) + ","+ "(" + Integer.toString(predecesor)+ "," + Integer.toString(actual) + ")" + "]" + Integer.toString(numIteraciones);
    }

}
