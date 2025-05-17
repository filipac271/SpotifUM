package Application.model.PlanoSubscricao;

import java.io.Serializable;
import java.util.List;

import Application.model.Album.Album;
import Application.model.Playlist.Playlist;

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
     * @brief Obtém o número de playlists guardadas.
     *
     * @return Número de playlists.
     */
    public abstract int numPlaylists();

    /**
     * @brief Guarda uma playlist.
     *
     * Adiciona uma playlist à lista de playlists do plano.
     *
     * @param p Playlist a guardar.
     */
    public abstract void guardarPlaylist(Playlist p);

    /**
     * @brief Verifica se uma playlist está guardada.
     *
     * @param p Playlist a verificar.
     * @return true se a playlist estiver guardada, false caso contrário.
     */
    public abstract boolean playlistGuardada(Playlist p);

    /**
     * @brief Guarda um álbum.
     *
     * Adiciona um álbum à lista de álbuns do plano.
     *
     * @param a Álbum a guardar.
     */
    public abstract void guardarAlbum(Album a);

    /**
     * @brief Obtém a lista de playlists.
     *
     * @return Lista de playlists guardadas.
     */
    public abstract List<Playlist> getPlaylists();

    /**
     * @brief Obtém a lista de álbuns.
     *
     * @return Lista de álbuns guardados.
     */
    public abstract List<Album> getAlbuns();

    /**
     * @brief Define a lista de playlists.
     *
     * Substitui a lista atual de playlists pela fornecida.
     *
     * @param playlists Nova lista de playlists.
     */
    public abstract void setPlaylists(List<Playlist> playlists);

    /**
     * @brief Define a lista de álbuns.
     *
     * Substitui a lista atual de álbuns pela fornecida.
     *
     * @param albuns Nova lista de álbuns.
     */
    public abstract void setAlbuns(List<Album> albuns);

    /**
     * @brief Retorna a representação textual do plano.
     *
     * @return String com a representação do plano.
     */
    @Override
    public abstract String toString();

    /**
     * Obtém o nome do plano de subscrição.
     * 
     * @return Nome do plano.
     */
    public String getNome() {
        return this.nomePlano;
    }

    /**
     * @brief Cria uma cópia do objeto.
     * 
     * Método abstrato que deve ser implementado pelas subclasses para devolver uma cópia do plano de subscrição.
     *
     * @return Uma nova instância de PlanoSubscricao, cópia do objeto atual.
     */
    public abstract PlanoSubscricao clone();
}
