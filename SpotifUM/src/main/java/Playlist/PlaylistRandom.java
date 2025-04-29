package Playlist;

import java.util.List;

import java.util.stream.Collectors;

import Song.Song;

public class PlaylistRandom extends Playlist {
  

    public PlaylistRandom(PlaylistRandom outra) {
        super(outra.getNome(), outra.getMusicas(), outra.getPublica());
    }
    
        
 @Override
    public void reproduzir() {
        for (Song s : super.getMusicas()) {
            System.out.println(s.getLetra());
            System.out.println("----");
        }
    }

    @Override
    public PlaylistRandom clone() {
        return new PlaylistRandom(this);
    }
  
}







  

