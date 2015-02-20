package kruskal;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {

    private int[][] matrizAdyacencia;
    private int numAristas;
    private static ArrayList nodosSel = new ArrayList();

    public Grafo(int[][] matrizAdyacencia) {
        this.matrizAdyacencia = matrizAdyacencia;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                if (matrizAdyacencia[i][j] > -1) {
                    numAristas++;
                }
            }
        }
    }

    public Grafo(Arista[] aristas) {
        matrizAdyacencia = new int[aristas.length + 1][aristas.length + 1];
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                matrizAdyacencia[i][j] = -1;
            }
        }
        for (Arista arista : aristas) {
            matrizAdyacencia[arista.getU()][arista.getV()] = arista.getPeso();
            numAristas++;
        }
    }

    public Grafo(int tam, int max) {
        numAristas = 0;
        matrizAdyacencia = new int[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                matrizAdyacencia[i][j] = (41 * i + 43 * j * tam) % 100;
                if (i + 1 > j || matrizAdyacencia[i][j] > max) {
                    matrizAdyacencia[i][j] = -1;
                } else {
                    numAristas++;
                }
            }
        }
    }

    public Grafo(int tam) {
        this(tam, 50);
    }

    public Grafo() {
        this(8);
    }

    public int getSize() {
        return matrizAdyacencia.length;
    }
    public int[][] getMatriz (){
        return matrizAdyacencia;
    }
    public int getPesoMinimo() {
        Arista[] aristas = getAristas();
        int pesoMinimo = 0;
        for (int i = 0; i < matrizAdyacencia.length - 1; i++) {
            pesoMinimo += aristas[i].getPeso();
        }
        return pesoMinimo;
    }
    
    public int getPesoMaximo() {
        Arista[] aristas = getAristas();
        int pesoMax = 0;
        for (int i= aristas.length - matrizAdyacencia.length; i < aristas.length; i++) {
            pesoMax += aristas[i].getPeso();
        }
        return pesoMax;
    }
    
    public boolean contiene(Grafo grafo){
        int[][] matriz2 = grafo.getMatriz();
        if(matriz2.length>matrizAdyacencia.length) return false;
        for (int i = 0; i < matriz2.length; i++) {
            for (int j = 0; j < matriz2.length; j++) {
                if(matriz2[i][j] > 0 && matriz2[i][j] != matrizAdyacencia[i][j]) return false;
            }
        }
        return true;
    }

    public int getPesoTotal() {
        int res = 0;
        for (int[] matrizAdyacencia1 : matrizAdyacencia) {
            for (int n : matrizAdyacencia1) {
                if (n > 0) {
                    res += n;
                }
            }
        }
        return res;
    }

    public Arista[] getAristas() {
        int indice = 0;
        Arista[] aristas = new Arista[numAristas];
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = i + 1; j < matrizAdyacencia.length; j++) {
                if (matrizAdyacencia[i][j] != -1) {
                    aristas[indice++] = new Arista(i, j, matrizAdyacencia[i][j]);
                }
            }
        }
        torneo(aristas);

        return aristas;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                str += ((matrizAdyacencia[i][j] == -1) ? " " : matrizAdyacencia[i][j]) + ",";
            }
            str += "\n";
        }
        return str;
    }

    private void torneo(Arista[] vec) {
        int n = vec.length;
        if (n < 2) {
            return;
        }
        Arista[] arboldat = new Arista[2 * n - 1];
        int[] arbolind = new int[2 * n - 1];
        for (int i = n - 1; i < 2 * n - 1; i++) {
            arboldat[i] = vec[i - n + 1];
            arbolind[i] = i;
        }
        for (int i = n - 1; i >= 1; i--) {
            minimo(i, arboldat, arbolind);
        }
        for (int j = 0; j <= n - 2; j++) {
            vec[j] = arboldat[0];
            int i = arbolind[0];
            arboldat[i] = new Arista(arboldat[i].getU(), arboldat[i].getV(), (int) Float.POSITIVE_INFINITY);
            i = (int) (((float) i / 2) + 0.5);
            while (i > 0) {
                minimo(i, arboldat, arbolind);
                i = i / 2;
            }
        }
        vec[n - 1] = arboldat[0];
    }

    private void minimo(int padre, Arista[] arboldat, int[] arbolind) {
        int hijo = padre * 2;
        if (padre == 0) {
            hijo = 1;
        }
        if (arboldat[hijo - 1].getPeso() <= arboldat[hijo].getPeso()) {
            arboldat[padre - 1] = arboldat[hijo - 1];
            arbolind[padre - 1] = arbolind[hijo - 1];
        } else {
            arboldat[padre - 1] = arboldat[hijo];
            arbolind[padre - 1] = arbolind[hijo];
        }
    }

    private boolean sonTodosTrue(boolean[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (!vector[i]) {
                return false;
            }
        }
        return true;
    }
//    public boolean esConexo(){
//        if(numAristas < matrizAdyacencia.length - 1 ) return false;
//        if(matrizAdyacencia.length < 2 ) return true;
//        ArrayList<Integer> verticesCandidatos = new ArrayList<>();
//        boolean[] verticesAlcanzados = new boolean[matrizAdyacencia.length];    
//        verticesCandidatos.add(0);
//        int verticeActual = 0;
//        while(!verticesCandidatos.isEmpty()){
//            System.out.println(verticesCandidatos);
//            verticeActual = verticesCandidatos.get(0);
//            for (int i = 0; i<matrizAdyacencia.length; i++) {
//                if(matrizAdyacencia[verticeActual][i]>-1 || matrizAdyacencia[i][verticeActual]>-1){
//                    if(!verticesCandidatos.contains(i)  && !verticesAlcanzados[i]) verticesCandidatos.add(i);
//                    verticesAlcanzados[i] = true;
//                }
//            }
//            verticesCandidatos.remove((Integer)verticeActual);
//            verticesAlcanzados[verticeActual] = true;
//        }
//        return sonTodosTrue(verticesAlcanzados);
//    }

    public boolean esConexo() {
        Arista[] aristas = this.getAristas();
        ArrayList<Arista> list = getMov(aristas, 0);
        conexo(0, list, aristas);
        return (nodosSel.size() == this.getSize());
    }

    private static void conexo(int nodo, ArrayList<Arista> list, Arista[] aristas) {
        if (nodosSel.contains(nodo)) {
            return;
        }
        nodosSel.add(nodo);
        for (int i = 0; i < list.size(); i++) {
            if (!nodosSel.contains(list.get(i).getV())) {
                conexo(list.get(i).getV(), getMov(aristas, list.get(i).getV()), aristas);
            }
        }
    }

    public boolean ciclos2() {
        Arista[] aristas = this.getAristas();
        nodosSel.clear();
        for (int i = 0; i < aristas.length; i++) {

        }
        return false;
    }

    public boolean tieneCiclos() {
        Arista[] aristas = this.getAristas();
        ArrayList<Arista> list = getMov(aristas, 0);
        nodosSel.clear();
        return ciclos(0, 0, list, aristas);
    }

    private boolean ciclos(int nodo, int nodoPadre, ArrayList<Arista> hijos, Arista[] aristas) {
//        if (nodosSel.size() == this.getSize()) return ;
        nodosSel.add(nodo);
        boolean[] tienenCiclos = new boolean[hijos.size()];
        for (int i = 0; i < hijos.size(); i++) {
            if (nodosSel.contains(hijos.get(i).getV())) {
                if (hijos.get(i).getV() != nodoPadre) {
                    return false;
                }
                return true;
            }
            tienenCiclos[i] = ciclos(hijos.get(i).getV(), nodo, getMov(aristas, hijos.get(i).getV()), aristas);
        }
        for (boolean b : tienenCiclos) {
            if (b) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Arista> getMov(Arista[] list, int nodo) {
        ArrayList<Arista> result = new ArrayList<Arista>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].getU() == nodo) {
                result.add(list[i]);
            }
        }
        return result;
    }

}
