package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;

import model.Playlist.Playlist;
import model.Playlist.PlaylistRandom;
import model.Song.Song;
import model.User.User;
import model.Album.Album;
import model.PlanoSubscricao.PlanoSubscricao;
import controller.Persistencia;

public class Model {

    private Map<String, Playlist> playlistTable;
    private Map<String, Song> songTable;
    private Map<String, User> userTable;
    private Map<String, Album> albumTable;

    public Model() {
        try {
            this.userTable = Persistencia.loadUsers();
            this.playlistTable = Persistencia.loadPlaylists();
            this.songTable = Persistencia.loadSongs();
            this.albumTable = Persistencia.loadAlbuns();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Inicializa mapas vazios como fallback
            this.userTable = new HashMap<>();
            this.playlistTable = new HashMap<>();
            this.songTable = new HashMap<>();
            this.albumTable = new HashMap<>();
        }
    }

    // Função que chama os métodos de salvar para users, playlists e songs
    public void saveAll() {
        try {
            Persistencia.saveUsers(userTable);
            Persistencia.savePlaylists(playlistTable);
            Persistencia.saveSongs(songTable);
            Persistencia.saveAlbum(albumTable);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public Model(Map<String, Playlist> playlists,
            Map<String, Song> songs,
            Map<String, User> users,
            Map<String, Album> albums) {

        this.playlistTable = new HashMap<>(playlists);
        this.songTable = new HashMap<>(songs);
        this.userTable = new HashMap<>(users);
        this.albumTable = new HashMap<>(albums);
    }

    // === User ===

    public void addUser(String username, String nome, String password, String email,
            String morada, PlanoSubscricao plano) {
        User user = new User(nome, username, password, email, morada, plano);
        userTable.put(username, user);
    }

    public User getUser(String username) {
        return userTable.get(username).clone();
    }

    public boolean removeUser(String username) {
        return userTable.remove(username) != null;
    }

    public boolean userExists(String username) {
        return userTable.containsKey(username);
    }

    // === Song ===
    public void addSong(String nome, String interprete, String editora, String letra, String pauta, String genero,
            int duracao) {
        Song song = new Song(nome, interprete, editora, letra, pauta, genero, duracao);
        songTable.put(nome, song);
    }

    public Song getSong(String name) {
        return songTable.get(name).clone();
    }

    public boolean removeSong(String name) {
        return songTable.remove(name) != null;
    }

    public boolean songExists(String name) {
        return songTable.containsKey(name);
    }

    // === Playlist ===
    // Depois fazer algo em relaçaõ ao tipo de playlist
    // public void addPlaylist(String nome, List<Song> musicas, boolean publica) {
    // Playlist playlist = new Playlist(nome, musicas, publica);
    // playlistTable.put(nome, playlist);
    // }

    public Playlist getPlaylist(String name) {
        return playlistTable.get(name).clone();
    }

    public boolean removePlaylist(String name) {
        return playlistTable.remove(name) != null;
    }

    public boolean playlistExists(String name) {
        return playlistTable.containsKey(name);
    }

    public PlaylistRandom createPlaylistRandom() {
        List<Song> todasAsMusicas = new ArrayList<>(songTable.values());

        Collections.shuffle(todasAsMusicas);

        PlaylistRandom playlist = null;

        playlist = new PlaylistRandom("random", todasAsMusicas, false);

        return playlist;

    }

    // === Album ===
    public void addAlbum(String nome, String artista, List<Song> albumList) {
        Album album = new Album(nome, artista, albumList);
        albumTable.put(nome, album);
    }

    public Album getAlbum(String name) {
        return albumTable.get(name).clone();
    }

    public boolean removeAlbum(String name) {
        return albumTable.remove(name) != null;
    }

    public boolean albumExists(String name) {
        return albumTable.containsKey(name);
    }

    // Querys

    // Devolve o nome da música mais ouvida
    public String musicaMaisOuvida() {
        String maisReproduzida = null;
        int maiorNumRep = -1;

        for (Map.Entry<String, Song> entry : songTable.entrySet()) {
            Song song = entry.getValue();
            if (song.getNumRep() > maiorNumRep) {
                maiorNumRep = song.getNumRep();
                maisReproduzida = song.getNome();
            }
        }

        return maisReproduzida;
    }

    // Devolve o nome do interprete mais escutado
    public String interpreteMaisOuvido() {

        Map<String, Integer> interpretes = new HashMap<>();

        for (Song song : songTable.values()) {
            String interprete = song.getInterprete();
            int numRep = song.getNumRep();

            interpretes.put(interprete, interpretes.getOrDefault(interprete, 0) + numRep);
        }

        String interpreteMaisOuvido = null;
        int maiorReps = -1;

        for (Map.Entry<String, Integer> entry : interpretes.entrySet()) {
            if (entry.getValue() > maiorReps) {
                maiorReps = entry.getValue();
                interpreteMaisOuvido = entry.getKey();
            }
        }

        return interpreteMaisOuvido;
    }

    public String generoMaisOuvido() {

        Map<String, Integer> generos = new HashMap<>();

        for (Song song : songTable.values()) {
            String genero = song.getInterprete();
            int numRep = song.getNumRep();

            generos.put(genero, generos.getOrDefault(genero, 0) + numRep);
        }

        String generoMaisOuvido = null;
        int maiorReps = -1;

        for (Map.Entry<String, Integer> entry : generos.entrySet()) {
            if (entry.getValue() > maiorReps) {
                maiorReps = entry.getValue();
                generoMaisOuvido = entry.getKey();
            }
        }

        return generoMaisOuvido;
    }

    public String userMaisPontos() {
        String userMaisPontos = null;
        double maiorNumPontos = -1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getPontos() > maiorNumPontos) {
                maiorNumPontos = user.getPontos();
                userMaisPontos = user.getNome();
            }
        }

        return userMaisPontos;
    }

    public User userMaisPlaylists() {
        User userMaisPlaylists = null;
        int maisPlaylists = -1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getNumPlaylists() > maisPlaylists) {
                maisPlaylists = user.getNumPlaylists();
                userMaisPlaylists = user;
            }
        }

        return userMaisPlaylists;
    }

    public int numPlaylistsPublicas() {
        int numPublicas = 0;

        for (Map.Entry<String, Playlist> entry : playlistTable.entrySet()) {
            Playlist playlist = entry.getValue();
            if (playlist.getPublica()) {
                numPublicas++;
            }
        }

        return numPublicas;
    }

}