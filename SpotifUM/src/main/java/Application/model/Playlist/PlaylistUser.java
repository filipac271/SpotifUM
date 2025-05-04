package Application.model.Playlist;


import java.util.List;


import Application.model.Song.Song;

public class PlaylistUser extends Playlist {

/**
     * Construtor com parâmetros.
     * 
     * @param nome Nome da playlist.
     * @param musicas Lista de músicas.
     * @param publica Indica se a playlist é pública.
     */
    public PlaylistUser(String nome, List<Song> musicas, boolean publica) {
       super(nome,musicas,publica);
    }

    /**
     * Construtor por omissão.
     * Cria uma playlist vazia, privada e sem nome.
     */
    public PlaylistUser() {
       super();
    }

    /**
     * Construtor de cópia.
     * 
     * @param outra Playlist a copiar.
     */
    public PlaylistUser(Playlist outra) {
       super(outra);
    }

      /**
     * Reproduz todas as músicas da playlist.
     * Neste caso, imprime as letras das músicas no terminal.
     */
    @Override
    public void reproduzir() {
        for (Song s : super.getMusicas()) {
            System.out.println(s.getLetra());
            System.out.println("----");
        }
    }

    /**
     * Cria uma cópia/clone desta playlist.
     * 
     * @return Uma nova instância de {@code PlaylistRandom} com os mesmos dados.
     */
    @Override
    public PlaylistUser clone() {
        return new PlaylistUser(this);
    }
    
 }
