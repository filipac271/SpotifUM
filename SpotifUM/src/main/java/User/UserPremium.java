package User;
import Album.Album;
import Playlist.Playlist;

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
