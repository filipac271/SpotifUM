package Playlist;

import java.util.List;

public class PlaylistRandom extends Playlist {

    public PlaylistRandom(PlaylistRandom outra) {
        super(outra.getNome(), outra.getMusicas(), outra.getPublica());
    }


    @Override
    public Playlist clone() {
        return new PlaylistRandom(this);
    }

    @Override
    public void reproduzir() {
        // Implementação do método reproduzir para PlaylistRandom
        System.out.println("Reproduzindo Playlist Random...");
    }
}