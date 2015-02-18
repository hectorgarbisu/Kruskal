package kruskal;
public class Kruskal {

    EstructuraParticion estructuraParticion;
    Grafo grafo;
    Arista[] pesos;
    Arista[] rutaOptima;
    int numAristas;
    
    public Kruskal(Grafo grafo, EstructuraParticion estructuraParticion, Arista[] pesos) {
        this.estructuraParticion = estructuraParticion;
        this.grafo = grafo;
        this.pesos = pesos;
        this.numAristas = 0;
        this.rutaOptima = new Arista[grafo.getSize() -1];
    }
    
    public void run(){
        if(!grafo.esConexo()) return;
        int u,v,raizU,raizV,i = 0;
        do {
           //System.out.println("numaristas :" + numAristas +",i :" + i);
           u = pesos[i].getU();
           v = pesos[i].getV();
           raizU = estructuraParticion.busca(u);
           raizV = estructuraParticion.busca(v);
           if(raizU!=raizV){
               estructuraParticion.fusionar(raizU, raizV);
               rutaOptima[numAristas] = pesos[i]; 
               numAristas++;
           }
           i++;
        }
        while (numAristas < grafo.getSize()-1);
    }
   
    public Arista[] getPath(){
        return rutaOptima;
    }

}

