package model.Playlist;


import model.Song.Song;

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
    public PlaylistRandom clone() {
        return new PlaylistRandom(this);
    }
}



