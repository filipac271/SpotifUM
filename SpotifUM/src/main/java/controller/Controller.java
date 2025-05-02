package controller;

import java.util.List;

import model.Model;
import model.Album.Album;
import model.PlanoSubscricao.PlanoFree;
import model.PlanoSubscricao.PlanoPremiumBase;
import model.PlanoSubscricao.PlanoPremiumTop;
import model.PlanoSubscricao.PlanoSubscricao;
import model.Playlist.Playlist;
import model.Song.Song;
import model.User.User;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void saveAll() {
        model.saveAll();
    }

    // ALBUNS

    public void addAlbum(String name, String artista, List<Song> musicas) {
        model.addAlbum(name, artista, musicas);
    }

    public boolean removeAlbum(String name) {
        if (model.albumExists(name)) {
            model.removeAlbum(name);
            return true;
        }
        return false;
    }

    public Album getAlbum(String name) {
        return model.getAlbum(name);
    }

    public boolean albumExists(String name) {
        return model.albumExists(name);
    }

    // PLAYLIST
    //Fazer um if para ver que tipo de playlist é que é
    // public void addPlaylist(String name, String nome, List<Song> musicas, boolean
    // publica) {
    // model.addPlaylist(name, nome, musicas, publica);
    // }

    public boolean removePlaylist(String name) {
        if (model.playlistExists(name)) {
            model.removePlaylist(name);
            return true;
        }
        return false;
    }

    public Playlist getPlaylist(String name) {
        return model.getPlaylist(name);
    }

    public boolean playlistExists(String name) {
        return model.playlistExists(name);
    }

    // SONG

    public void addSong(String nomeMusica, String interprete, String editora, String letra, String pauta, String genero,
            int duracao) {
        model.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao);
    }

    public boolean removeSong(String title) {
        if (model.songExists(title)) {
            model.removeSong(title);
            return true;
        }
        return false;
    }

    public Song getSong(String title) {
        return model.getSong(title);
    }

    public boolean songExists(String title) {
        return model.songExists(title);
    }

    // USER

    private PlanoSubscricao getPlanoByOption(int planoOption) {
        switch (planoOption) {
            case 1:
                return new PlanoFree();
            case 2:
                return new PlanoPremiumBase();
            case 3:
                return new PlanoPremiumTop();
            default:
                return null;
        }
    }

    public void addUser(String nome, String username, String password, String email, String morada, int planoOption) {
        PlanoSubscricao plano = getPlanoByOption(planoOption);
        model.addUser(username, nome, password, email, morada, plano);
    }

    public boolean removeUser(String username) {
        if (model.userExists(username)) {
            model.removeUser(username);
            return true;
        }
        return false;
    }

    public boolean authenticUser(String username, String password) {
        User user = model.getUser(username);
        return user != null && password.equals(user.getPassword());
    }

    public User getUser(String username) {
        return model.getUser(username);
    }

    public boolean userExists(String username) {
        return model.userExists(username);
    }

}
