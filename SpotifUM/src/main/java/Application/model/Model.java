package Application.model;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.time.LocalDate;

import Application.model.Playlist.Playlist;
import Application.model.Playlist.PlaylistRandom;
import Application.model.Playlist.PlaylistUser;
import Application.model.Song.Song;
import Application.model.Song.SongExplicit;
import Application.model.Song.SongMediaExplicit;
import Application.model.User.Historico;
import Application.model.User.User;
import Application.model.Album.Album;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.controller.Persistencia;


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
            //Funçoes de debugging que printam a base de dados
            Utils.printMap(userTable);
            Utils.printMap(playlistTable);
            Utils.printMap(songTable);
            Utils.printMap(albumTable);
            
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
                        String morada, PlanoSubscricao plano)
     {
       
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

    public String userReproduziu(Song musica, String username)
    {
            String letra;
        if (musica instanceof SongExplicit)  {
             letra = ((SongExplicit) musica).getReproducaoExplicita(19);             
        }
         else if (musica instanceof SongMediaExplicit) {
             letra = ((SongMediaExplicit) musica).getReproducaoExplicita(19);     
        } else {
             letra = musica.getReproducao();
        }

        User user = getUser(username);
        System.out.println(user.getNome());
        LocalDate data = LocalDate.now();
        user.addHistorico(musica, data);
        double pontosAtuais=user.getPontos();
        PlanoSubscricao plano= user.getPlano();
        plano.calculaPontos(pontosAtuais);
        user.setPlano(plano);

        return letra;
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

    // public void addPlaylist(String nome, List<Song> musicas, boolean publica, String tipo) {
    //     // Playlist playlist;
    
    //     switch (tipo) {

    //         // case "User":
    //         //     playlist = new PlaylistUser(nome, musicas, publica);
    //         //     break;
    //         default:
    //             throw new IllegalArgumentException("Tipo de playlist desconhecido: " + tipo);
    //     }
    
    //     // playlistTable.put(nome, playlist);
    // }

    public void addPlaylist(String nome, Playlist playlist)
    {
        playlistTable.put(nome, playlist);
    }
    public void addToPlaylist(String nomeP,String nomeM )
    {
        Song musica=songTable.get(nomeM);
        playlistTable.get(nomeP).adicionarMusica(musica);
    }


    

    public Playlist getPlaylist(String name) {
        return playlistTable.get(name).clone();
    }

    public boolean removePlaylist(String name) {
        return playlistTable.remove(name) != null;
    }

    public boolean playlistExists(String name) {
        return playlistTable.containsKey(name);
    }

    public boolean playlistAcessivel(String username,String nome)
    {
        if(getPlaylist(nome).getPublica())
        {
            return true;
        }
        else
        {
            Playlist p=getPlaylist(nome);
            User user= getUser(username);
            return user.getPlano().playlistGuardada(p);
        }
    }
    
    

    public PlaylistRandom createPlaylistRandom() {
        List<Song> todasAsMusicas = new ArrayList<>(songTable.values());

        Collections.shuffle(todasAsMusicas);

        PlaylistRandom playlist = null;

        playlist = new PlaylistRandom("random", todasAsMusicas, false);


        return playlist;

    }

    public PlaylistUser createPlaylist(String nomeP,boolean publica)
        {
            List<Song> musicas = new ArrayList<>();
            PlaylistUser playlist= new PlaylistUser(nomeP, musicas,publica);
            return playlist;
        }
    
    public ArrayList<String> getNomeMusicas(String nomeP,int n)
    {
        ArrayList<String> nomeMusicas=new ArrayList<>();
        Playlist p= getPlaylist(nomeP);
        for(int i=0;i<n;i++)
        {
            nomeMusicas.add(p.getNMusica(i).getNome()) ;
        }
        return nomeMusicas;

    }

    public void trocaMusicas(String nomeP,int i,int index)
    {
        Playlist p = getPlaylist(nomeP);
        Song s1 = p.getNMusica(index);

        p.adicionarMusicaIndex(p.getNMusica(i), index);
        p.adicionarMusicaIndex(s1,i);
        playlistTable.replace(nomeP,p);
    }

    public void ordenarPlaylist(String nomeP,int op)
    {
        Playlist playlist=getPlaylist(nomeP);

        switch (op) {
            case 1: // Nome da música A-Z
            playlist.getMusicas().sort((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
            break;

            case 2: // Nome da música Z-A
                playlist.getMusicas().sort((s1, s2) -> s2.getNome().compareToIgnoreCase(s1.getNome()));
                break;

            case 3: // Nome do intérprete A-Z
                playlist.getMusicas().sort((s1, s2) -> s1.getInterprete().compareToIgnoreCase(s2.getInterprete()));
                break;

            case 4: // Nome do intérprete Z-A
                playlist.getMusicas().sort((s1, s2) -> s2.getInterprete().compareToIgnoreCase(s1.getInterprete()));
                break;

            default:
                 break;
        }
        playlistTable.replace(nomeP, playlist);

    }

    // === Album ===
    public void addAlbum(String nome, String artista, List<Song> albumList) {
        Album album = new Album(nome, artista, albumList);
        albumTable.put(nome, album);
    }

    public Album getAlbum(String name) {
        return albumTable.get(name);
    }

    public boolean removeAlbum(String name) {
        return albumTable.remove(name) != null;
    }

    public boolean albumExists(String name) {
        return albumTable.containsKey(name);
    }

    // Querys

    // Query 1- Qual a música mais reproduzida?
    public Song musicaMaisOuvida() {
        Song maisReproduzida = null;
        int maiorNumRep = -1;

        for (Map.Entry<String, Song> entry : songTable.entrySet()) {
            Song song = entry.getValue();
            if (song.getNumRep() > maiorNumRep) {
                maiorNumRep = song.getNumRep();
                maisReproduzida = song;
            }
        }

        return maisReproduzida;
    }

    // Query 2 - Qual o interprete mais escutado?
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

    //Query 3 - Qual o utilizador que mais músicas ouviu num período de tempo ou desde sempre?
    //Consideramos que cada musica só se conte uma vez mesmo o user ouvindo-a várias vezes
    public User userMaisMusicasOuvidas(LocalDate dataInicio, LocalDate dataFim)
    {
        User userMaisMusicas = null;
        int maiorNumMusicas=0;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            int nmusicas=user.numMusicasOuvidas(dataInicio,dataFim);
            if (nmusicas > maiorNumMusicas) {
                maiorNumMusicas = nmusicas;
                userMaisMusicas = user;
            }
        }
        return userMaisMusicas;
    }


    //Query 4- Qual o Utilizador com mais pontos?
    public User userMaisPontos() {
        User userMaisPontos = null;
        double maiorNumPontos = -1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getPontos() > maiorNumPontos) {
                maiorNumPontos = user.getPontos();
                userMaisPontos = user;
            }
        }

        return userMaisPontos;
    }

    // Query 5- Qual o tipo de música mais reproduzida?
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

    // Query 6 - Quantas playlists públicas existem?
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

    //Query 7 - Qual o utilizador com mais playlists?
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

   

}