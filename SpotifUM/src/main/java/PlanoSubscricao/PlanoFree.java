package PlanoSubscricao;

/**
 * Classe que representa o plano de subscrição gratuito ("Free").
 * Herda de {@link PlanoSubscricao}. CONFIRMAR ISTO
 */
public class PlanoFree extends PlanoSubscricao {
    
    /**
     * Construtor por omissão.
     * Define o nome do plano como "Free".
     */
    public PlanoFree() {
        this.nomePlano = "Free";
    }

    /**
     * Calcula os pontos atribuídos a um utilizador deste plano.
     * 
     * @param pontos Pontos base a considerar.
     * @return Pontos atualizados com pontos + 5.
     */
    public double calculaPontos(double pontos) {
        return pontos + 5;
    }

    /**
     * Verifica se o utilizador pode avançar ou retroceder músicas neste plano.
     * 
     * @return false, pois este plano não permite avançar ou retroceder.
     */
    public boolean podeAvancarRetroceder() {
        return false;
    }

    /**
     * Indica o número de playlists que o utilizador pode ter neste plano.
     * 
     * @return 0, pois o plano gratuito não permite playlists.
     */
    public int numPlaylists() {
        return 0;
    }
}
