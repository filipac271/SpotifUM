package Application.model.PlanoSubscricao;

import java.util.List;

import Application.model.Album.Album;
import Application.model.Playlist.Playlist;

/**
 * Classe que representa o plano de subscrição gratuito ("Free").
 * Herda de {@link PlanoSubscricao}. 
 */
public class PlanoFree extends PlanoSubscricao {
    
    /**
     * Construtor por omissão.
     * Define o nome do plano como "Free".
     */
    public PlanoFree() {
        this.nomePlano = "PlanoFree";
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

    /**
     * Tenta guardar uma playlist, mas este plano não permite essa operação.
     *
     * @param p A playlist a guardar.
     * @throws UnsupportedOperationException Sempre que é chamado, pois este plano não permite guardar playlists.
     */
    public void guardarPlaylist(Playlist p) {
        throw new UnsupportedOperationException("Este plano não suporta guardar playlists.");
    }

    /**
     * Tenta guardar um álbum, mas este plano não permite essa operação.
     *
     * @param a O álbum a guardar.
     * @throws UnsupportedOperationException Sempre que é chamado, pois este plano não permite guardar álbuns.
     */
    public void guardarAlbum(Album a) {
        throw new UnsupportedOperationException("Este plano não suporta guardar playlists.");
    }

    /**
     * Verifica se uma playlist está guardada, mas este plano não suporta essa funcionalidade.
     *
     * @param p A playlist a verificar.
     * @return Nunca retorna, pois sempre lança exceção.
     * @throws UnsupportedOperationException Sempre que é chamado, pois este plano não permite guardar playlists.
     */
    public boolean playlistGuardada(Playlist p) {
        throw new UnsupportedOperationException("Este plano não suporta guardar playlists.");
    }

    /**
     * Cria e devolve uma nova instância de PlanoFree.
     *
     * @return Uma nova instância do plano gratuito.
     */
    @Override
    public PlanoFree clone() {
        return new PlanoFree();
    }


    /**
     * {@inheritDoc}
     * Apenas mostra o nome do plano, pois não tem conteúdos associados.
     */
    @Override
    public String toString() {
        return "Plano Free\n(Sem playlists ou álbuns guardados)";
    }

    /**
     * @brief Obtém a lista de playlists.
     * 
     * Método não suportado neste plano. Lança uma exceção ao ser invocado.
     *
     * @return Nunca retorna, pois lança uma exceção.
     * @throws UnsupportedOperationException Sempre que este método é chamado.
     */
    @Override
    public List<Playlist> getPlaylists() {
        throw new UnsupportedOperationException("Este plano não suporta playlists.");
    }

    /**
     * @brief Obtém a lista de álbuns.
     * 
     * Método não suportado neste plano. Lança uma exceção ao ser invocado.
     *
     * @return Nunca retorna, pois lança uma exceção.
     * @throws UnsupportedOperationException Sempre que este método é chamado.
     */
    @Override
    public List<Album> getAlbuns() {
        throw new UnsupportedOperationException("Este plano não suporta álbuns.");
    }

    /**
     * @brief Define a lista de playlists.
     * 
     * Método não suportado neste plano. Lança uma exceção ao ser invocado.
     *
     * @param playlists A lista de playlists (não utilizada).
     * @throws UnsupportedOperationException Sempre que este método é chamado.
     */
    @Override
    public void setPlaylists(List<Playlist> playlists) {
        throw new UnsupportedOperationException("Este plano não suporta playlists.");
    }

    /**
     * @brief Define a lista de álbuns.
     * 
     * Método não suportado neste plano. Lança uma exceção ao ser invocado.
     *
     * @param albuns A lista de álbuns (não utilizada).
     * @throws UnsupportedOperationException Sempre que este método é chamado.
     */
    @Override
    public void setAlbuns(List<Album> albuns) {
        throw new UnsupportedOperationException("Este plano não suporta álbuns.");
    }


}
