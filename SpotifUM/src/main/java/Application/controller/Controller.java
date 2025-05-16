package Application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Application.exceptions.zeroSongsListen;
import Application.model.Model;


public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void saveAll() {
        model.saveAll();
    }

    // ALBUNS

    public void addAlbum(String name, String artista) {

        model.addAlbum(name, artista);
    }

    public boolean removeAlbum(String name) {
        if (model.albumExists(name)) {
            model.removeAlbum(name);
            return true;
        }
        return false;
    }

    // private Album getAlbum(String name) {
    //     return model.getAlbum(name);
    // }

    public boolean albumExists(String name) {
        return model.albumExists(name);
    }


    public void guardarAlbum(String username, String nome) {
       model.guardarAlbumUser(username, nome);
    }


    public int addToAlbum(String nomeA, String nomeM) {
        if (albumExists(nomeA)) {
            model.addToAlbum(nomeA, nomeM);
            return 1;
        } else {
            return 0;
        }
    }

    // PLAYLIST
    public int addToPlaylist(String nomeP, String nomeM) {
        if (songExists(nomeM)) {
            model.addToPlaylist(nomeP, nomeM);
            return 1;
        } else {
            return 0;
        }

    }

    public void guardarPlaylist(String username, String nome) {
    model.guardarPlaylistUser(username, nome);
    }

    public boolean removePlaylist(String name) {
        if (model.playlistExists(name)) {
            model.removePlaylist(name);
            return true;
        }
        return false;
    }

    // private Playlist getPlaylist(String name) {
    //     return model.getPlaylist(name);
    // }

    public boolean playlistExists(String name) {
        return model.playlistExists(name);
    }

    public String createPlaylistRandom() {
        return model.createPlaylistRandom().getNome();
    }

    public void addPlaylist(String nomeP, String publicaS,String tematica,String genero,int duracaoMaxima) {
        boolean publica;
        boolean tema_Tempo;
        if (publicaS.equals("s")) {
            publica = true;
        } else {
            publica = false;
        }
        if (tematica.equals("s")) {
            tema_Tempo = true;
        } else {
            tema_Tempo = false;
        }

        model.addPlaylist(nomeP, publica,new ArrayList<>(),tema_Tempo,genero,duracaoMaxima);
    }

    public boolean validaAudicao(String username, String tipo, String nome) {
        if (tipo.equals("playlist")) {
            if (playlistExists(nome)) {
                return model.playlistAcessivel(username, nome);
            } else {
                return false;
            }
        } else if (tipo.equals("album")) {
            return albumExists(nome);
        } else if (tipo.equals("musica")) {
            return songExists(nome);
        } else {
            return false;
        }
    }

    public String letraDaMusicaNa_Playlist_Album(String username, String nome, int[] index, boolean serPlaylist) {
        int tamanho;
        //Song musica;
        String musica;
    
        if (serPlaylist) {
            // Playlist playlist = getPlaylist(nome); //deep clone
            // tamanho = playlist.tamanho();
            tamanho = model.getPlaylistTamanho(nome);
    
            if (index[0] < 0){
                return "Não há músicas anteriores na playlist";
            }
            if (index[0] >= tamanho){
                index[0] = tamanho;
                return "Já atingiu o final da playlist";
            }
    
            // musica = playlist.getNMusica(index[0]); //deep clone
            musica = model.getPlaylistNMusica(nome, index[0]);
    
        } else { // é álbum
            // Album album = getAlbum(nome);
            // tamanho = album.tamanho();
            tamanho = model.getAlbumTamanho(nome);
    
            if (index[0] < 0)
                return "Não há músicas anteriores no álbum";
            if (index[0] >= tamanho){
                index[0] = tamanho;
                return "Já atingiu o final do álbum";
            }
    
            // musica = album.getNMusica(index[0]);
            musica = model.getAlbumNMusica(username, index[0]);
        }
        
        return model.userReproduziu(musica, username);
    }
    
    public List<Integer> shufflePlaylist_album(String nome, boolean isPlaylist) {
        int tamanho;
        if (isPlaylist) {
            // Playlist playlist = model.(nome);
            // tamanho = playlist.tamanho();
            tamanho = model.getPlaylistTamanho(nome);
        } else {
            // Album album = getAlbum(nome);
            // tamanho = album.tamanho();
            tamanho = model.getAlbumTamanho(nome);
        }
        List<Integer> ordem = IntStream.range(0, tamanho)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(ordem);
        return ordem;
    }
    
    


    public ArrayList<String> getNomeMusicas(String nomeP, int n) {
        return model.getNomeMusicas(nomeP, n);

    }

    public void trocaMusicas(String nomeP, int i, int index) {
        model.trocaMusicas(nomeP, i, index);
    }

    public void ordenarPlaylist(String nomeP, int op) {
        model.ordenarPlaylist(nomeP, op);
    }

    public int proximaMusica(Scanner sc, int index, int selecao) {

        if (selecao == 1) {
            index += 2;
        } else if (selecao == 2) {
            index++;
        } else if (selecao == 3) {
            if (index != -1)
                index--;
        } else if (selecao == 4) {
            index = 0;
        } else {
            index = -2;
        }
        return index;
    }




    // SONG

    public String reproduzirMusica(String username, String nomeMusica) {
        // Song song = getSong(nomeMusica);
        String letra = model.userReproduziu(nomeMusica, username);
        return letra;
    }

    public void addSong(String nomeMusica, String interprete, String editora, String letra, String pauta, String genero,
            int duracao, String isExplicit, String isMedia, String url) {
        model.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao, isExplicit, isMedia, url);
    }

    public boolean removeSong(String name) {
        if (model.songExists(name)) {
            model.removeSong(name);
            return true;
        }
        return false;
    }

    // private Song getSong(String name) {
    //     return model.getSong(name);
    // }

    public boolean songExists(String name) {
        return model.songExists(name);
    }

    // USER

    


    public boolean changeUserPlan(String username, int planoInt, String planoString){
        // User user = model.getUser(username);
        // // String planoPresente = user.getPlano().getNome();
        // String planoPresente;
        // planoPresente = model.getUserPlano(username);

        // List<Playlist> playlists;
        // List<Album> albums;
        // if(planoPresente.equals(planoString))return false;
        // PlanoSubscricao pNew = getPlanoByOption(planoInt);
        // if(planoInt == 1){
        //     user.setPlano(pNew);
        //     return true;
        // }
        // if(!planoPresente.equals("PlanoFree")){

        //     //playlists = ((PlanoPremium) user.getPlano()).getPlaylists(); //shallow copy
        //     playlists = model.getUserPlanoPlaylist(username);

        //     //albums = ((PlanoPremium) user.getPlano()).getAlbuns(); //shallow copy
        //     albums = model.getUserPlanoAlbuns(username);
        //     PlanoPremium pPremium = (PlanoPremium) pNew;
        //     pPremium.setPlaylists(playlists);
        //     pPremium.setAlbuns(albums);
        //     user.setPlano(pPremium);
        //     return true;

        // }
        // return false;
        return model.changeUserPlano(username, planoInt, planoString);
    }

    public String getPlanoByUser(String username){
        return model.getPlanodoUser(username);
    }

    public void addUser(String nome, String username, String password, String email, String morada, int age,
            int planoOption) {
        model.addUser(username, nome, password, email, morada, age, planoOption);
    }

    public boolean removeUser(String username) {
        if (model.userExists(username)) {
            model.removeUser(username);
            return true;
        }
        return false;
    }

    public boolean authenticUser(String username, String password) {
        return model.userAutentico(username, password);
    }

    // private User getUser(String username) {
    //     return model.getUser(username);
    // }

    public boolean userExists(String username) {
        return model.userExists(username);
    }

    public void addPlaylistToUser(String nomeP, String username) {
        // User user = model.getUser(username);
        // PlanoSubscricao plano = user.getPlano();

        // PlanoPremium pPremium = (PlanoPremium) plano; 
        
        // //duvidoso
        // pPremium.guardarPlaylist(getPlaylist(nomeP));

        model.setPlaylistGuardada(nomeP, username);
        
    }

    // Queries
    public String query1() {
        try {
            
            return model.musicaMaisOuvida();
        } catch (zeroSongsListen e) {
            return e.getMessage();
        }
    }
    

    public String query2e6(int opcao) {
        try {
            return switch (opcao) {
                case 2 -> model.interpreteMaisOuvido();
                case 6 -> model.generoMaisOuvido();
                default -> "Essa opção é inválida!";
            };
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    

    public String query345e8(int opcao, LocalDate dataInicio, LocalDate dataFim) {
        try {
            return switch (opcao) {
                case 3 -> model.userMaisMusicasOuvidas(dataInicio, dataFim);
                case 4 -> model.userMaisMusicasOuvidas(dataInicio, dataFim);
                case 5 -> model.userMaisPontos();
                case 8 -> model.userMaisPlaylists();
                default -> "Opção inválida";
            };
        } catch (RuntimeException e) {
            return e.getMessage(); 
        }
    }
    

    public int query7() {
        int resposta = 0;
        resposta = model.numPlaylistsPublicas();
        return resposta;
    }

    public String gerarPlaylistRecomendada(String username, int opcao, int ngeneros, String generos, int segundos) {

        // Playlist musicasRecomendadas = model.recomendarMusicas(username, opcao, ngeneros, generos, segundos);

        // model.addPlaylist(musicasRecomendadas.getNome(), musicasRecomendadas.getPublica(), musicasRecomendadas.getMusicas(),false,"",0);

        // User user = getUser(username);
        // PlanoSubscricao p = user.getPlano();
        // PlanoPremium pPremium = (PlanoPremium) p; 
        // pPremium.guardarPlaylist(musicasRecomendadas);

        // return musicasRecomendadas.getNome();

        return model.playlistRecomendada(username, opcao, ngeneros, generos, segundos);
    }

}
