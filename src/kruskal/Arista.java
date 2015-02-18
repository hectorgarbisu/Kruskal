package kruskal;
public class Arista {
    private int u;
    private int v;
    private int peso;
    
    public Arista(int u, int v, int peso){
        this.u = u;
        this.v = v;
        this.peso = peso;
    }
    
    public int getU(){
        return this.u;
    }
    
    public int getV(){
        return this.v;
    }
    public int getPeso(){
        return this.peso;
    }
    
   public void setU(int u){
        this.u = u;
   }
   
   public void setV(int v){
        this.v = v;
   }
   
   public void setPeso(int peso){
        this.peso = peso;
   }
   public String toString (){
       return u + "-" + v + ":" +peso;
   }
}