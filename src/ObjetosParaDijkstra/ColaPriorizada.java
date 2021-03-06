package ObjetosParaDijkstra;

import PilasYColasDinamicas.ColaDinamica;
import PilasYColasDinamicas.Nodo;

public class ColaPriorizada <T> extends ColaDinamica {

    public ElementoColaPriorizada buscarYEliminarElMenor() {
        ElementoColaPriorizada menor, auxParaGuardarDatos;
        Nodo auxParaRecorrerCola = raiz; // Este auxiliar recorre la cola
        menor = new ElementoColaPriorizada();
        auxParaGuardarDatos = (ElementoColaPriorizada) raiz.object;
        menor = auxParaGuardarDatos;

        // Se encuentra el menor de los elementos
        while (auxParaRecorrerCola != null) {
            auxParaGuardarDatos = (ElementoColaPriorizada) auxParaRecorrerCola.object;
            if (auxParaGuardarDatos.pesoAcumulado < menor.pesoAcumulado)
                menor = auxParaGuardarDatos;
            auxParaRecorrerCola = auxParaRecorrerCola.next;
        }
        // si el menor es la raiz se elimina
        auxParaRecorrerCola = raiz;
        auxParaGuardarDatos = (ElementoColaPriorizada) auxParaRecorrerCola.object;
        if (auxParaGuardarDatos.pesoAcumulado == menor.pesoAcumulado)
            raiz = auxParaRecorrerCola.next;
        else { // Si no, se busca y se elimina
            while (auxParaRecorrerCola != null) {
                // Se gurdan los datos del siguiente nodo
                    auxParaGuardarDatos = (ElementoColaPriorizada) auxParaRecorrerCola.next.object;
                if (auxParaGuardarDatos.pesoAcumulado == menor.pesoAcumulado) { // Se encontró el menor
                    auxParaRecorrerCola.next = auxParaRecorrerCola.next.next; // Se reconectan
                    break;
                }
                auxParaRecorrerCola = auxParaRecorrerCola.next;
            }
        }
            return menor;

    }

}
