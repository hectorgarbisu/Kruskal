package kruskal;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int TAM = 10;
        int maxAristas = 50;
        Grafo grafo = new Grafo(TAM,maxAristas);
        Arista[] aristas = grafo.getAristas();
        EstructuraParticion estructuraParticion = new EstructuraParticion(grafo);
        Kruskal kruskals = new Kruskal(grafo,estructuraParticion, aristas);
        kruskals.run();
        Arista[] ruta = kruskals.getPath();
        
        
        //PRUEBAS
        System.out.println("Inicio de las pruebas");
        System.out.println(grafo);
        for (Arista arista : aristas) {
            System.out.println(arista);
        }    
        System.out.println("es conexo grafo: "+grafo.esConexo());
        System.out.println("La ruta optima obtenida es "+ Arrays.toString(ruta));
//        int[][] otraMatriz = new int[][]{{1,1,1,-1},{1,1,1,-1},{1,1,1,-1},{-1,-1,-1,-1}};
//        Grafo otroGrafo = new Grafo(otraMatriz);
//        System.out.println("otroGrafo :\n"+otroGrafo);
//        System.out.println("es conexo otroGrafo: "+ otroGrafo.esConexo());
        Grafo grafoRuta = new Grafo(ruta);
        System.out.println("grafoRuta :\n"+grafoRuta);
        System.out.println("es conexo ruta: "+ grafoRuta.esConexo());
        System.out.println("Peso total grafo:  "+grafo.getPesoTotal());
        System.out.println("Peso minimo grafo (n menores aristas) :" + grafo.getPesoMinimo());
        System.out.println("Peso total grafoRuta:  "+grafoRuta.getPesoTotal());
    }
}