package Application.model.PlanoSubscricao;

import java.io.Serializable;

import Application.model.Playlist.Playlist;
import Application.model.Album.Album;

/**
 * Classe abstrata que define a estrutura base de um plano de subscrição.
 * 
 * Cada plano concreto deve implementar os métodos abstratos definidos nesta classe,
 * como o cálculo de pontos, permissões de navegação nas músicas e número de playlists permitidas.
 */
public abstract class PlanoSubscricao implements Serializable {
    
    /**
     * Nome do tipo de plano de subscrição.
     */
    protected String nomePlano;

    /**
     * Calcula os pontos atribuídos com base no plano.
     * 
     * @param pontos Pontos base a considerar.
     * @return Pontos ajustados segundo as regras do plano.
     */
    public abstract double calculaPontos(double pontos);

    /**
     * Verifica se o utilizador pode avançar ou retroceder músicas neste plano.
     * 
     * @return true se for permitido, false caso contrário.
     */
    public abstract boolean podeAvancarRetroceder();

    /**
     * Devolve o número de playlists guardadas.
     * 
     * @return Número de playlists guardadas.
     */
    public abstract int numPlaylists();

    public abstract void guardarPlaylist(Playlist p) ;
   

    public abstract void guardarAlbum(Album a) ;
    

    /**
     * Obtém o nome do plano de subscrição.
     * 
     * @return Nome do plano.
     */
    public String getNome() {
        return this.nomePlano;
    }
}
