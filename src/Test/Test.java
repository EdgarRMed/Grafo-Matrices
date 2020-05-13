package Test;

import Exceptions.NoExisteElementoException;
import Grafo.Grafo;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main (String [] args) throws IOException, ClassNotFoundException, NoExisteElementoException {
        Grafo grafo = new Grafo(20);
        Scanner in = new Scanner(System.in);
        boolean showMenu = true;
        int option = 0;
        int weight;
        String nombre, origen, destino;
        while (option != 7) {
            System.out.println("Ingrese una opción");
            if (showMenu) {
                System.out.println("1.Abrir desde un archivo");
                System.out.println("2.Insertar un vertice");
                System.out.println("3.Eliminar un vertice");
                System.out.println("4.Insertar un arco");
                System.out.println("5.Eliminar un arco");
                System.out.println("6.Imprimir el grafo");
                System.out.println("7.Salir");
                System.out.println("8.Guardar como unico archivo Grafo3");
                System.out.println("9.Recorrido primero en anchura");
                System.out.println("10.Recorrido primero en profundidad");
                System.out.println("11. Imprimir el grado de entrada y salida de cada nodo");
                System.out.println("12.Agoritmo de Dijksta");
                showMenu = false;
            }
            System.out.print("> ");
            try {
                option = in.nextInt();
                in.nextLine();
            }
            catch (java.util.InputMismatchException e){}

            switch (option) {
                case 1:
                    try {
                        System.out.println("Ingrese el nombre de un archivo(Grafo,Grafo1,Grafo2)");
                        nombre = in.nextLine();
                        FileInputStream entrada = new FileInputStream("Grafos/" + nombre +".txt");
                        ObjectInputStream reader = new ObjectInputStream(entrada);
                        //Lectura de objeto
                        System.out.println("------------ Contenido de "+nombre+".txt ---------------");
                        grafo = (Grafo) reader.readObject();
                        System.out.println(grafo);
                    }
                    catch (java.io.InvalidClassException e){
                        System.out.println("\nNo hay archivos disponibles o el archivo se ha corrompido\n");
                    }
                    catch (java.io.FileNotFoundException ex){
                        System.out.println("No existe el archivo");
                    }
                    break;
                case 2:
                    System.out.println("Nombre vertice (Insertar): ");
                    nombre = in.nextLine();
                    try {
                        grafo.insertVertice(nombre);
                        System.out.println(grafo);
                    }
                    catch (NoExisteElementoException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Nombre vertice (Eliminar): ");
                    nombre = in.nextLine();
                    try {
                        grafo.deleteVertice(nombre);
                        System.out.println(grafo);
                    }
                    catch (NoExisteElementoException e){
                        System.out.println(e.getMessage());
                    }
                    break;


                case 4:
                    System.out.println("Vertice origen (Insertar)");
                    System.out.println("Nombre: ");
                    origen = in.nextLine();
                    System.out.println("Vertice destino (Insertar)");
                    System.out.println("Nombre: ");
                    destino = in.nextLine();
                    System.out.println("Ingrese el peso:");
                    weight = in.nextInt();
                    in.nextLine();
                    try {
                        grafo.insertArco(origen, destino, weight);
                        System.out.println(grafo);
                    }
                    catch (NoExisteElementoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Vertice origen (Eliminar)");
                    System.out.println("Nombre: ");
                    origen = in.nextLine();
                    System.out.println("Vertice destino (Eliminar)");
                    System.out.println("Nombre: ");
                    destino = in.nextLine();
                    try {
                        grafo.deleteArco(origen,destino);
                        System.out.println(grafo);
                    }
                    catch (NoExisteElementoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println(grafo);
                    break;

                case 7:
                    System.out.println("\n");
                    break;

                case 8:
                    File grafoTxt = new File("Grafos/"+"Grafo3.txt");
                    FileOutputStream fileOS = new FileOutputStream(grafoTxt); // Se pasa el archivo
                    ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
                    objectOS.writeObject(grafo); // Se esecribe
                    objectOS.close(); // Se cierra el archivo
                    System.out.println(grafo);
                    System.out.println("Se guardó correctamente\n");
                    break;

                case 9:
                    grafo.stringBFS = "";
                    System.out.println("En anchura: ");
                    grafo.BreadthFirstSearch();
                    System.out.println(grafo.stringBFS);
                    break;

                case 10:
                    grafo.stringDFS = "";
                    System.out.println("En profundidad: ");
                    grafo.depthFirstSearch(0);
                    System.out.println(grafo.stringDFS);
                    break;

                case 11:
                    System.out.println(grafo.gradoEntradaSalida());

                case 12:
                    System.out.println("Ingrese el vértice de inicio:");
                    origen = in.nextLine();
                    grafo.dijksta(origen);
                    break;
                //Punto de control para visualizar la matriz si así se requiere
                case 13:
                    grafo.printMatrix();
                    break;

                default:
                    System.out.println("Ingrese una opción válida");
                    break;

            }
        }
    }
}
