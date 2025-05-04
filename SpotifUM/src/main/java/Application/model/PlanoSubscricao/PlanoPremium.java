package Application.model.PlanoSubscricao;

import java.util.ArrayList;
import java.util.List;
import Application.model.Album.Album;
import Application.model.Playlist.Playlist;

/**
 * Classe abstrata que representa um plano de subscrição do tipo Premium.
 * 
 * Um plano Premium permite guardar playlists e álbuns, além de permitir
 * avançar e retroceder músicas. É um plano inferior ao {@link PlanoPremiumTop}.
 */
public abstract class PlanoPremium extends PlanoSubscricao {

    /**
     * Lista de playlists guardadas pelo utilizador.
     */
    protected List<Playlist> playlists;

    /**
     * Lista de álbuns guardados pelo utilizador.
     */
    protected List<Album> albuns;

    /**
     * Construtor por omissão.
     * Inicializa as listas de playlists e álbuns.
     */
    public PlanoPremium() {
        this.playlists = new ArrayList<>();
        this.albuns = new ArrayList<>();
    }

    /**
     * Guarda uma nova playlist na conta do utilizador.
     * 
     * @param playlist A playlist a guardar.
     */
    @Override
    public void guardarPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    /**
     * Guarda um novo álbum na conta do utilizador.
     * 
     * @param album O álbum a guardar.
     */
    public void guardarAlbum(Album album) {
        this.albuns.add(album);
    }

    /**
     * Verifica se o utilizador pode avançar ou retroceder músicas.
     * 
     * @return true, pois todos os planos Premium permitem essa funcionalidade.
     */
    public boolean podeAvancarRetroceder() {
        return true;
    }

    /**
     * Devolve o número de playlists atualmente guardadas.
     * 
     * @return Número de playlists guardadas.
     */
    public int numPlaylists() {
        return this.playlists.size();
    }
}
