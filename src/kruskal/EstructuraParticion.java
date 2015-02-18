package kruskal;
public class EstructuraParticion {
    int[] conjunto;
   
    public EstructuraParticion(Grafo grafo) {
        this(grafo.getSize());
    }

    public EstructuraParticion(int tam) {
        this.conjunto = new int[tam];
        for (int i = 0; i < tam; i++) {
            conjunto[i] = 0;
        }
    }
    
    public int busca(int elem){
        int i = elem;
        while(conjunto[i]>0) i = conjunto[i];
        return i;
    }
    
    public void fusionar(int a, int b){
        if(conjunto[a] == conjunto[b]){
            conjunto[a] = conjunto[a] - 1;
            conjunto[b] = a;
        }
        else{
            if(conjunto[a]<conjunto[b]) conjunto[b] = a;
            else conjunto[a] = b;
        }
    }
}