package Application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Application.model.Model;
import Application.model.Album.Album;
import Application.model.PlanoSubscricao.PlanoFree;
import Application.model.PlanoSubscricao.PlanoPremiumBase;
import Application.model.PlanoSubscricao.PlanoPremiumTop;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.Playlist.Playlist;
import Application.model.Song.Song;
import Application.model.User.User;

import Application.view.View;

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

    public int addToPlaylist(String nomeP, String nomeM) {
        if (songExists(nomeM)) {
            model.addToPlaylist(nomeP, nomeM);
            return 1;
        } else {
            return 0;
        }

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

    public void reproduzirPlaylistSequencial(String username, String nome, View v, Scanner sc) {
        int index = 0;
        Playlist playlist = getPlaylist(nome);
        String letra = "";
        while (index != -2) {
            if (index < playlist.tamanho() && index >= 0) {

                Song musica = playlist.getNMusica(index);
                letra = model.userReproduziu(musica, username);
                v.print(letra);
            } else {
                if (index < 0) {
                    v.print("Não há músicas anteriores na playlist");
                    index = 0;
                } else {
                    v.print("Já atingiu o final da playlist");
                    index = playlist.tamanho();

                }
            }

            index = proximaMusica(sc, index, v);
        }
    }

    public void reproduzirPlaylistAleatoriamente(String username, String nome, View v, Scanner sc) {
        Playlist playlist = getPlaylist(nome);
        int tamanho = playlist.tamanho();
        List<Integer> ordem = IntStream.range(0, tamanho)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(ordem);
        int index = 0;
        String letra = "";
        while (index != -2) {

            if (index < tamanho && index >= 0) {
                Song musica = playlist.getNMusica(ordem.get(index));
                letra = model.userReproduziu(musica, username);
                v.print(letra);
            } else if (index < 0) {
                v.print("Não há músicas anteriores na playlist");
                index = 0;
            } else {
                v.print("Já atingiu o final da playlist");
                index = tamanho;
            }

            index = proximaMusica(sc, index, v);

        }

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

    public int proximaMusica(Scanner sc, int index, View v) {
        int selecao = v.perguntarContinuar(sc);

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
    // ALBUM

    public void reproduzirAlbumSequencial(String username, String nome, View v, Scanner sc) {
        int index = 0;
        Album album = getAlbum(nome);
        String letra = "";
        while (index != -2) {
            if (index < album.tamanho() && index >= 0) {

                Song musica = album.getNMusica(index);
                letra = model.userReproduziu(musica, username);
                v.print(letra);

            } else if (index < 0) {
                v.print("Não há músicas anteriores no album");
                index = -1;
            } else {
                v.print("Já atingiu o final do album");
                index = album.tamanho();
            }

            index = proximaMusica(sc, index, v);

        }
    }

    public void reproduzirAlbumAleatoriamente(String username, String nome, View v, Scanner sc) {
        Album album = getAlbum(nome);
        int tamanho = album.tamanho();
        List<Integer> ordem = IntStream.range(0, tamanho).boxed().collect(Collectors.toList());
        Collections.shuffle(ordem);
        int index = 0;
        String letra = "";

        while (index != -2) {

            if (index < album.tamanho() && index >= 0) {
                Song musica = album.getNMusica(ordem.get(index));
                letra = model.userReproduziu(musica, username);
                v.print(letra);
            } else if (index < 0) {
                v.print("Não há músicas anteriores no album");
                index = 0;
            } else {
                v.print("Já atingiu o final do album");
                index = album.tamanho();
            }

            index = proximaMusica(sc, index, v);
        }
    }

    public void guardarAlbum(String username, String nome) {
        Album album = model.getAlbum(nome);
        User user = model.getUser(username);
        PlanoSubscricao plano = user.getPlano();
        plano.guardarAlbum(album);
    }

    // SONG

    public String reproduzirMusica(String username, String nomeMusica) {
        Song song = getSong(nomeMusica);
        System.out.println(System.identityHashCode(song) + "No controller ");

        String letra = model.userReproduziu(song, username);
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

    public void addUser(String nome, String username, String password, String email, String morada, int age,
            int planoOption) {
        PlanoSubscricao plano = getPlanoByOption(planoOption);
        model.addUser(username, nome, password, email, morada, age, plano);
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

    // Queries
    public String query1() {
        Song maisreproduzida = model.musicaMaisOuvida();
        return maisreproduzida.toString();
    }

    public String query2e5(int opcao) {
        String resposta = "Essa opção é invalida!";
        if (opcao == 2) {
            resposta = model.interpreteMaisOuvido();
        } else if (opcao == 6) {
            resposta = model.generoMaisOuvido();
        } else {
            return resposta;
        }

        return resposta;

    }

    public String query34e7(int opcao, LocalDate dataInicio, LocalDate dataFim) {
        String resposta;
        if (opcao == 3 || opcao == 4) {
            resposta = model.userMaisMusicasOuvidas(dataInicio, dataFim);
            return resposta;
        } else if (opcao == 5) {
            resposta = model.userMaisPontos();
        } else {
            resposta = model.userMaisPlaylists();
        }
        return resposta;
    }

    public int query6() {
        int resposta = 0;
        resposta = model.numPlaylistsPublicas();
        return resposta;
    }

    public String gerarPlaylistRecomendada(String username, int opcao, int ngeneros, String generos, int segundos) {

        Playlist musicasRecomendadas = model.recomendarMusicas(username, opcao, ngeneros, generos, segundos);

        model.addPlaylist(musicasRecomendadas.getNome(), musicasRecomendadas.getPublica(), musicasRecomendadas.getMusicas(),false,"",0);

        User user = getUser(username);
        PlanoSubscricao p = user.getPlano();
        p.guardarPlaylist(musicasRecomendadas);

        return musicasRecomendadas.getNome();
    }

}
