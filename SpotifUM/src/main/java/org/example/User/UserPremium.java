package org.example.User;
import org.example.Playlist.Playlist;
import org.example.Album.Album;

import java.util.List;


public abstract class UserPremium extends User {
    private List<Playlist> playlists;
    private List<Album> albuns;

     public void guardarPlaylist(Playlist playlist)
    {
        this.playlists.add(playlist);
    }
    public void guardarAlbum(Album album)
    {
        this.albuns.add(album);
    }

}
