package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import PlanoSubscricao.PlanoFree;
import PlanoSubscricao.PlanoPremiumBase;
import PlanoSubscricao.PlanoPremiumTop;
import PlanoSubscricao.PlanoSubscricao;
import Playlist.Playlist;
import Song.Song;
import User.User;
import view.Input;

public class Controller {

      private Map<String, Playlist> playlistTable;
      private Map<String, Song> songTable;
      private Map<String, User> userTable;
   
      
      public Controller() {

          this.playlistTable = new HashMap<>();
          this.songTable = new HashMap<>();
          this.userTable = new HashMap<>();
      }


      public Controller (Map<String, Playlist> playListMap, Map<String, Song>  SongsMap , Map<String, User> UsersMap){

        this.playlistTable = playListMap;
        this.songTable = SongsMap;
        this.userTable = UsersMap;
      }


      // Função que chama os métodos de salvar para users, playlists e songs
    public void saveAll() {
        try {
            Persistencia.saveUsers(userTable);
            Persistencia.savePlaylists(playlistTable);
            Persistencia.saveSongs(songTable);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }



   // PLAYLIST

    // Adiciona uma playlist
    public void addPlaylist(String name, Playlist playlist) {
        playlistTable.put(name, playlist.clone());
    }

    // Remove uma playlist
    public boolean removePlaylist(String name) {
        if (playlistTable.containsKey(name)) {
            playlistTable.remove(name);
            return true;
        }
        return false;
    }

    // Obtém uma playlist pelo nome
    public Playlist getPlaylist(String name) {
        return playlistTable.get(name).clone();
    }

    // Verifica se uma playlist existe
    public boolean playlistExists(String name) {
        return playlistTable.containsKey(name);
    }

    public int numPlaylistsPublicas()
    {
        int numPublicas = 0;

        for (Map.Entry<String, Playlist> entry : playlistTable.entrySet()) {
            Playlist playlist = entry.getValue();
            if (playlist.getPublica()) {
                numPublicas++;
            }
        }

        return numPublicas;
    }

    //  SONG

    // Adiciona uma música à tabela
    public void addSong(String nomeMusica,String interprete,String editora,String letra,String pauta,String genero,int duracao) {
        Song song= new Song( nomeMusica, interprete, editora, letra, pauta, genero, duracao);
        songTable.put(nomeMusica, song.clone());
    }

    // Remove uma música pelo título
    public boolean removeSong(String title) {
        if (songTable.containsKey(title)) {
            songTable.remove(title);
            return true;
        }
        return false;
    }

    // Obtém uma música pelo título
    public Song getSong(String title) {
        return songTable.get(title).clone();
    }


    // Verifica se uma música existe
    public boolean songExists(String title) {
        return songTable.containsKey(title);
    }

    // Devolve o nome da música mais ouvida
    public String musicaMaisOuvida()
    {
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

    //Devolve o nome do interprete mais escutado
    public String interpreteMaisOuvido()
    {
           
        Map<String, Integer> interpretes= new HashMap<>();

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

    public String generoMaisOuvido()
    {
           
        Map<String, Integer> generos= new HashMap<>();

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


    // USER

    // Adiciona um novo utilizador
    public void addUser(String nome, String username,String password, String email, String morada,PlanoSubscricao plano ) {
        plano= new PlanoFree();
        User user= new User(nome,username,password,email,morada,plano);
        this.userTable.put(username, user);
    }
    
    // Remove um utilizador pelo username
    public boolean removeUser(String username) {
        if (this.userTable.containsKey(username)) {
            this.userTable.remove(username);
            return true;
        }
        return false;
    }

    public boolean authenticUser(String username,String password)
    {
        User user=userTable.get(username) ;

         if ( user == null ) return false;

        return password.equals(user.getPassword()) ;

    }

    

    // // Retorna um utilizador pelo username
    // public User getUser(String username) {
    // return userTable.get(username).clone();
    // }

    // Retorna um utilizador pelo username
    public User getUser(String username) {
        return userTable.get(username);
    }

    // Verifica se um utilizador existe
    public boolean userExists(String username) {
        return userTable.containsKey(username);
    }

    public String userMaisPontos()
    {
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

    public User userMaisPlaylists()
    {
        User userMaisPlaylists = null;
        int maisPlaylists=-1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getNumPlaylists() > maisPlaylists) {
                maisPlaylists = user.getNumPlaylists();
                userMaisPlaylists = user;
            }
        }

        return userMaisPlaylists;
    }

    public PlanoSubscricao createPlano(Scanner sc)
    {
        Input io=new Input();
        int op=io.createPlanoMenu(sc);
        PlanoSubscricao plano=null;

        switch (op) {
            case 1:
                plano=new PlanoFree();
                break;
            case 2:
                plano=new PlanoPremiumBase();
                break;
            case 3:
                plano=new PlanoPremiumTop();
                break;    
            default:
                break;
        }
        return plano;
        }

        // ALBUM

    //    public void createAlbum(String nome, String artista)
    //    {
    //          new Album(nome,artista,null);
    //    }
       
    //     public void   addSongAlbum()

}
