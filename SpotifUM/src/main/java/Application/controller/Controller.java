package Application.controller;

import java.util.ArrayList;
import java.util.List;


import Application.model.Model;
import Application.model.Album.Album;
import Application.model.PlanoSubscricao.PlanoFree;
import Application.model.PlanoSubscricao.PlanoPremiumBase;
import Application.model.PlanoSubscricao.PlanoPremiumTop;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.Playlist.Playlist;
import Application.model.Playlist.PlaylistRandom;
import Application.model.Song.Song;
import Application.model.User.User;

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
        if(musicas == null){
            musicas = new ArrayList<>();
        }
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

    public void addPlaylist( String nome, List<Song> musicas, boolean publica, String tipo) {
        model.addPlaylist( nome, musicas, publica,tipo);
    }

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

    public PlaylistRandom createPlaylistRandom(){
        return model.createPlaylistRandom();
    }

    // SONG

    public void addSong(String nomeMusica, String interprete, String editora, String letra, String pauta, String genero,
            int duracao) {
        model.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao);
    }

    public boolean removeSong(String name) {
        if (model.songExists(name)) {
            model.removeSong(name);
            return true;
        }
        return false;
    }

    public Song getSong(String name) {
        return model.getSong(name);
    }

    public boolean songExists(String name) {
        return model.songExists(name);
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
