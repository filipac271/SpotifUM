package Application.model.Playlist;


import java.util.List;

import Application.model.Song.Song;

/**
 * Classe que representa uma playlist do tipo aleatório.
 * 
 * Esta playlist reproduz as músicas aleatorias, isto é imprime as letras de cada música no terminal.
 */
public class PlaylistRandom extends Playlist  {

    /**
     * Construtor de cópia.
     * 
     * @param outra Outra {@code PlaylistRandom} a copiar.
     */
    public PlaylistRandom(PlaylistRandom outra) {
        super(outra.getNome(), outra.getMusicas(), outra.getPublica());
    }

    public PlaylistRandom(String nome, List<Song> musicas, boolean publica ){
        super(nome, musicas, publica);
    }

    


    /**
     * Cria uma cópia/clone desta playlist.
     * 
     * @return Uma nova instância de {@code PlaylistRandom} com os mesmos dados.
     */
    @Override
    public PlaylistRandom clone() {
        return new PlaylistRandom(this);
    }
}



