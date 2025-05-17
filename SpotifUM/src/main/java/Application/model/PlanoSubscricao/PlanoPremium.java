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

    public PlanoPremium(PlanoPremium outra){
        this.playlists = outra.getPlaylists();
        this.albuns = outra.getAlbuns();
    }

    /**
     * Guarda uma nova playlist na conta do utilizador.
     * 
     * @param playlist A playlist a guardar.
     */
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

    public boolean playlistGuardada(Playlist p) {
        return this.playlists.contains(p);

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

    /**
     * Devolve uma cópia da lista de playlists guardadas.
     *
     * @return Lista de playlists guardadas.
     */
    public List<Playlist> getPlaylists() {
        return new ArrayList<>(this.playlists);
    }

    /**
     * Devolve uma cópia da lista de álbuns guardados.
     *
     * @return Lista de álbuns guardados.
     */
    public List<Album> getAlbuns() {
        return new ArrayList<>(this.albuns);
    }

    /**
     * Define a lista de playlists guardadas pelo utilizador com plano premium.
     * Se o argumento for {@code null}, a lista é inicializada como vazia.
     *
     * @param playlists Lista de playlists a guardar.
     */
    public void setPlaylists(List<Playlist> playlists) {
        if (playlists == null) {
            this.playlists = new ArrayList<>();
        } else {
            this.playlists = new ArrayList<>(playlists);
        }
    }
    
    /**
     * Define a lista de álbuns guardados pelo utilizador com plano premium.
     * Se o argumento for {@code null}, a lista é inicializada como vazia.
     *
     * @param albuns Lista de álbuns a guardar.
     */
    public void setAlbuns(List<Album> albuns) {
        if (albuns == null) {
            this.albuns = new ArrayList<>();
        } else {
            this.albuns = new ArrayList<>(albuns);
        }
    }
    
    /**
     * Devolve uma representação textual do plano premium do utilizador,
     * incluindo as playlists e os álbuns guardados.
     *
     * @return Uma string formatada com o nome do plano e os conteúdos guardados.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plano Premium\n");
    
        sb.append("Playlists guardadas (").append(playlists.size()).append("):\n");
        for (int i = 0; i < playlists.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(playlists.get(i).getNome()).append("\n");
        }
    
        sb.append("Álbuns guardados (").append(albuns.size()).append("):\n");
        for (int i = 0; i < albuns.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(albuns.get(i).getNome()).append("\n");
        }
    
        return sb.toString();
    }
    

}
