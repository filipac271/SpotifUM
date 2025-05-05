package Application.controller;

import java.time.LocalDate;
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
import Application.model.Playlist.PlaylistUser;
import Application.model.Song.Song;
import Application.model.Song.SongExplicit;
import Application.model.Song.SongMediaExplicit;
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
        if (musicas == null) {
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

    public int reproduzirPlaylist(String username, String nome, int index, String selecao) {
        Playlist playlist = getPlaylist(nome);
        if (selecao.equals("A")) {
            index += 2;
        } else if (selecao.equals("R")) {
            index--;
        } else if (selecao.equals("S")) {
            index++;
        }

        if (index < playlist.tamanho()) {

            Song musica = playlist.getNMusica(index);
            String letra = musica.getLetra();
            System.out.println(letra);
            int numRep = musica.getNumRep();
            musica.setNumRep(numRep + 1);
            User user = model.getUser(username);
            LocalDate data = LocalDate.now();
            user.addHistorico(musica, data);
            return index;
        } else {
            return -1;
        }

    }

    public void addToPlaylist(String nomeP, String nomeM) {
        model.addToPlaylist(nomeP, nomeM);
    }

    public void guardarPlaylist(String username, String nome) {
        Playlist playlist = model.getPlaylist(nome);
        User user = model.getUser(username);
        PlanoSubscricao plano = user.getPlano();
        plano.guardarPlaylist(playlist);
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

    public PlaylistRandom createPlaylistRandom() {
        return model.createPlaylistRandom();
    }

    public void createPlaylist(String username, String nomeP, String publicaS) {
        boolean publica;
        if (publicaS.equals("s")) {
            publica = true;
        } else {
            publica = false;
        }
        PlaylistUser playlist = model.createPlaylist(nomeP, publica);
        model.addPlaylist(nomeP, playlist);
    }

    // ALBUM

    public int reproduzirAlbum(String username, String nome, int index, String selecao) {
        Album album = getAlbum(nome);
        if (selecao.equals("A")) {
            index += 2;
        } else if (selecao.equals("R")) {
            index--;
        } else if (selecao.equals("S")) {
            index++;
        }

        if (index < album.tamanho()) {

            Song musica = album.getNMusica(index);
            String letra = musica.getLetra();
            System.out.println(letra);
            int numRep = musica.getNumRep();
            musica.setNumRep(numRep + 1);
            User user = model.getUser(username);
            LocalDate data = LocalDate.now();
            user.addHistorico(musica, data);
            return index;
        } else {
            return -1;
        }

    }

    public void guardarAlbum(String username, String nome) {
        Album album = model.getAlbum(nome);
        User user = model.getUser(username);
        PlanoSubscricao plano = user.getPlano();
        plano.guardarAlbum(album);
    }

    // SONG

    public void reproduzirMusica(String username, String nomeMusica) {
        Song song = getSong(nomeMusica);
        String letra = getReproducao(nomeMusica);
        System.out.println(letra);
        User user = model.getUser(username);
        LocalDate data = LocalDate.now();
        user.addHistorico(song, data);

    }

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

    public String getReproducao(String name) {
        Song musica = model.getSong(name);
        int numRep = musica.getNumRep();
        musica.setNumRep(numRep + 1);
        return musica.getLetra();
    }

    public String getReproducaoExplicita(String nameSong, int age) {
        Song musica = model.getSong(nameSong);
        if (musica.getClass() == SongExplicit.class || musica.getClass() == SongMediaExplicit.class) {
            if (age < 18) {
                return "Esta musica tem conteudo explicito daí não poder ser reproduzida";
            } else {
                int numRep = musica.getNumRep();
                musica.setNumRep(numRep + 1);
                return musica.getLetra();
            }

        }
        int numRep = musica.getNumRep();
        musica.setNumRep(numRep + 1);
        return musica.getLetra();

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

    public void addPlaylistToUser(String nomeP, String username) {
        User user = model.getUser(username);
        PlanoSubscricao plano = user.getPlano();
        plano.guardarPlaylist(getPlaylist(nomeP));
    }

}
