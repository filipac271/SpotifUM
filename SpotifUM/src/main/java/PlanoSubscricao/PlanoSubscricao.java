package PlanoSubscricao;

public abstract class PlanoSubscricao {
    
    protected String nomePlano;

    public abstract double calculaPontos(double pontos);
    public abstract boolean podeAvancarRetroceder();
    public abstract int numPlaylists();
    public String getNome(PlanoSubscricao plano)
    {
        return plano.nomePlano;
    }

} 