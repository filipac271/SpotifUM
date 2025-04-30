package PlanoSubscricao;


public class PlanoFree  extends PlanoSubscricao {
    
    public PlanoFree()
    {
        this.nomePlano="Free";
    }
    public double calculaPontos(double pontos){
        return pontos+5;
    }
    public boolean podeAvancarRetroceder()
    {
        return false;
    }
    public  int numPlaylists()
    {
        return 0;
    }
   
    
}
