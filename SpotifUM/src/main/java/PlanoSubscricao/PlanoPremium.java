package PlanoSubscricao;

import java.util.ArrayList;
import java.util.List;
import Album.Album;
import Playlist.Playlist;

public abstract class PlanoPremium extends PlanoSubscricao {

    protected List<Playlist> playlists;
    protected List<Album> albuns;

    public PlanoPremium()
    {
        this.playlists=new ArrayList<>();
        this.albuns=new ArrayList<>();
    }
    public void guardarPlaylist(Playlist playlist)
    {
        this.playlists.add(playlist);
    }
    public void guardarAlbum(Album album)
    {
        this.albuns.add(album);
    }
    public boolean podeAvancarRetroceder()
    {
        return true;
    }
    public int numPlaylists()
    {
       return this.playlists.size();
    }


}