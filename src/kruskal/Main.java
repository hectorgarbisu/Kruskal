package kruskal;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int TAM = 12;
        int maxAristas = 50;
        Grafo grafo = new Grafo(TAM,maxAristas);
        Arista[] aristas = grafo.getAristas();
        EstructuraParticion estructuraParticion = new EstructuraParticion(grafo);
        Kruskal kruskals = new Kruskal(grafo,estructuraParticion, aristas);
        int error = kruskals.run();
        Arista[] arbolOptimo = kruskals.getOptimalTree();
        //PRUEBAS
        System.out.println("Error: " + error);
        System.out.println("Inicio de las pruebas");
        System.out.println(grafo);
        for (Arista arista : aristas) {
            System.out.println(arista);
        }
        for (Arista arbolOptimo1 : arbolOptimo) {
            System.out.println(arbolOptimo1);
        }
        Grafo grafoArbol = new Grafo(arbolOptimo);
        System.out.println("grafoArbol :\n"+grafoArbol);
        System.out.println("El grafo de entrada "+(grafo.esConexo()?"es":"no es")+" conexo");
        if(!grafo.esConexo()) return;
        System.out.println("El árbol óptimo obtenido es "+ Arrays.toString(arbolOptimo));
        System.out.println("Es conexo arbol: "+ grafoArbol.esConexo());
        System.out.println("Peso total grafo:  "+grafo.getPesoTotal());
        System.out.println("Peso minimo arbol (n menores aristas del grafo) :" + grafo.getPesoMinimo());
        System.out.println("Peso maximo arbol (n mayores aristas del grafo) :" + grafo.getPesoMaximo());
        System.out.println("Peso total arbol:  "+grafoArbol.getPesoTotal());
        System.out.println("El grafo tiene ciclos: "+grafo.tieneCiclos());
        System.out.println("El árbol tiene ciclos: "+grafoArbol.tieneCiclos());
        System.out.println("El árbol óptimo está contenido dentro del grafo: "+grafo.contiene(grafoArbol));
        System.out.println("El grafo de entrada es su propio árbol óptimo: "+ (grafo.contiene(grafoArbol)&&grafoArbol.contiene(grafo)));
    }
}