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

/**
 * @class Controller
 * @brief Classe responsável por intermediar a comunicação entre a interface e o modelo de dados (Model).
 * 
 * Esta classe contém métodos que encapsulam e redirecionam chamadas ao objeto Model,
 * representando operações relacionadas a álbuns, músicas, playlists, utilizadores e queries.
 */
public class Controller {

    private Model model;


    /**
     * @brief Construtor do Controller.
     * @param model Instância do Model a ser utilizada pelo Controller.
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * @brief Salva todos os dados do modelo.
     */
    public void saveAll() {
        model.saveAll();
    }


    // ==== ÁLBUNS ============

    /**
     * @brief Adiciona um novo álbum.
     * @param name Nome do álbum.
     * @param artista Nome do artista.
     */
    public void addAlbum(String name, String artista) {
        model.addAlbum(name, artista);
    }

    /**
     * @brief Remove um álbum, se existir.
     * @param name Nome do álbum.
     * @return true se o álbum foi removido, false caso contrário.
     */
    public boolean removeAlbum(String name) {
        if (model.albumExists(name)) {
            model.removeAlbum(name);
            return true;
        }
        return false;
    }

    /**
     * @brief Verifica se um álbum existe.
     * @param name Nome do álbum.
     * @return true se existir, false caso contrário.
     */
    public boolean albumExists(String name) {
        return model.albumExists(name);
    }

    /**
     * @brief Guarda um álbum na conta do utilizador.
     * @param username Nome do utilizador.
     * @param nome Nome do álbum.
     */
    public void guardarAlbum(String username, String nome) {
        model.guardarAlbumUser(username, nome);
    }

    /**
     * @brief Adiciona uma música a um álbum.
     * @param nomeA Nome do álbum.
     * @param nomeM Nome da música.
     * @return 1 se adicionado com sucesso, 0 caso o álbum não exista.
     */
    public int addToAlbum(String nomeA, String nomeM) {
        if (albumExists(nomeA)) {
            model.addToAlbum(nomeA, nomeM);
            return 1;
        } else {
            return 0;
        }
    }


    // ==== PLAYLISTS =========

    /**
     * @brief Adiciona uma música a uma playlist.
     * @param nomeP Nome da playlist.
     * @param nomeM Nome da música.
     * @return 1 se adicionada com sucesso, 0 caso a música não exista.
     */
    public int addToPlaylist(String nomeP, String nomeM) {
        if (songExists(nomeM)) {
            model.addToPlaylist(nomeP, nomeM);
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * @brief Guarda uma playlist para um utilizador.
     * @param username Nome do utilizador.
     * @param nome Nome da playlist.
     */
    public void guardarPlaylist(String username, String nome) {
    model.guardarPlaylistUser(username, nome);
    }

    /**
     * @brief Remove uma playlist, se existir.
     * @param name Nome da playlist.
     * @return true se removida, false caso contrário.
     */
    public boolean removePlaylist(String name) {
        if (model.playlistExists(name)) {
            model.removePlaylist(name);
            return true;
        }
        return false;
    }

    /**
     * @brief Verifica se uma playlist existe.
     * @param name Nome da playlist.
     * @return true se existir, false caso contrário.
     */
    public boolean playlistExists(String name) {
        return model.playlistExists(name);
    }

    /**
     * @brief Cria uma playlist aleatória.
     * @return Nome da playlist criada.
     */
    public String createPlaylistRandom() {
        return model.createPlaylistRandom().getNome();
    }

    /**
     * @brief Adiciona uma nova playlist ao modelo.
     * @param nomeP Nome da playlist.
     * @param publicaS Se é pública (s/n).
     * @param tematica Se é temática (s/n).
     * @param genero Género principal.
     * @param duracaoMaxima Duração máxima da playlist.
     */
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

    /**
     * @brief Verifica se um utilizador pode aceder a uma música/playlist/álbum.
     * @param username Nome do utilizador.
     * @param tipo Tipo do objeto: "musica", "playlist" ou "album".
     * @param nome Nome da entidade.
     * @return true se pode aceder, false caso contrário.
     */
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

    /**
     * @brief Obtém a letra da música na posição atual da playlist ou álbum.
     * @param username Nome do utilizador.
     * @param nome Nome da playlist ou álbum.
     * @param index Array com o índice da música (usado como referência).
     * @param serPlaylist true se for playlist, false se for álbum.
     * @return Letra da música ou mensagem de erro.
     */
    public String letraDaMusicaNa_Playlist_Album(String username, String nome, int[] index, boolean serPlaylist) {
        int tamanho;
        String musica;
    
        if (serPlaylist) {
            tamanho = model.getPlaylistTamanho(nome);
    
            if (index[0] < 0){
                return "Não há músicas anteriores na playlist";
            }
            if (index[0] >= tamanho){
                index[0] = tamanho;
                return "Já atingiu o final da playlist";
            }

            musica = model.getPlaylistNMusica(nome, index[0]);
    
        } else { 
            tamanho = model.getAlbumTamanho(nome);
    
            if (index[0] < 0)
                return "Não há músicas anteriores no álbum";
            if (index[0] >= tamanho){
                index[0] = tamanho;
                return "Já atingiu o final do álbum";
            }

            musica = model.getAlbumNMusica(nome, index[0]);
        }
        
        return model.userReproduziu(musica, username);
    }
    
    /**
     * @brief Devolve uma lista embaralhada de índices da playlist ou álbum.
     * @param nome Nome da playlist ou álbum.
     * @param isPlaylist true se for playlist, false se for álbum.
     * @return Lista embaralhada de índices.
     */
    public List<Integer> shufflePlaylist_album(String nome, boolean isPlaylist) {
        int tamanho;
        if (isPlaylist) {
            tamanho = model.getPlaylistTamanho(nome);
        } else {
            tamanho = model.getAlbumTamanho(nome);
        }
        List<Integer> ordem = IntStream.range(0, tamanho)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(ordem);
        return ordem;
    }
    
    

    /**
     * @brief Obtém os nomes das músicas de uma playlist.
     * @param nomeP Nome da playlist.
     * @param n Quantidade de músicas a obter.
     * @return Lista de nomes das músicas.
     */
    public ArrayList<String> getNomeMusicas(String nomeP, int n) {
        return model.getNomeMusicas(nomeP, n);

    }

    /**
     * @brief Troca a posição de duas músicas numa playlist.
     * @param nomeP Nome da playlist.
     * @param i Índice da primeira música.
     * @param index Índice da segunda música.
     */
    public void trocaMusicas(String nomeP, int i, int index) {
        model.trocaMusicas(nomeP, i, index);
    }

    /**
     * @brief Ordena uma playlist com base numa opção.
     * @param nomeP Nome da playlist.
     * @param op Opção de ordenação.
     */
    public void ordenarPlaylist(String nomeP, int op) {
        model.ordenarPlaylist(nomeP, op);
    }

    /**
     * @brief Determina o próximo índice da música a ser reproduzida.
     * @param sc Scanner para entrada do utilizador.
     * @param index Índice atual.
     * @param selecao Opção escolhida.
     * @return Novo índice.
     */
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




    // ==== SONG ===========

    /**
     * @brief Reproduz uma música para o utilizador.
     * @param username Nome do utilizador.
     * @param nomeMusica Nome da música.
     * @return Letra da música.
     */
    public String reproduzirMusica(String username, String nomeMusica) {
        String letra = model.userReproduziu(nomeMusica, username);
        return letra;
    }

    /**
     * @brief Adiciona uma nova música ao sistema.
     * @param nomeMusica Nome da música.
     * @param interprete Nome do intérprete.
     * @param editora Nome da editora.
     * @param letra Letra da música.
     * @param pauta Pauta musical.
     * @param genero Género musical.
     * @param duracao Duração em segundos.
     * @param isExplicit Indica se é explícita.
     * @param isMedia Indica se é media.
     * @param url URL do ficheiro da música.
     */
    public void addSong(String nomeMusica, String interprete, String editora, String letra, String pauta, String genero,
            int duracao, String isExplicit, String isMedia, String url) {
        model.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao, isExplicit, isMedia, url);
    }

    /**
     * @brief Remove uma música, se existir.
     * @param name Nome da música.
     * @return true se removida, false caso contrário.
     */
    public boolean removeSong(String name) {
        if (model.songExists(name)) {
            model.removeSong(name);
            return true;
        }
        return false;
    }

    /**
     * @brief Verifica se uma música existe.
     * @param name Nome da música.
     * @return true se existir, false caso contrário.
     */
    public boolean songExists(String name) {
        return model.songExists(name);
    }


    // ==== UTILIZADORES ======

    /**
     * @brief Altera o plano de um utilizador.
     * @param username Nome do utilizador.
     * @param planoInt Código do plano.
     * @param planoString Nome do plano.
     * @return true se alterado com sucesso.
     */
    public String changeUserPlan(String username, int planoInt){
        return model.changeUserPlano(username, planoInt);
    }

    /**
     * @brief Obtém o plano atual de um utilizador.
     * @param username Nome do utilizador.
     * @return Nome do plano.
     */
    public String getPlanoByUser(String username){
        return model.getPlanodoUser(username);
    }

    /**
     * @brief Adiciona um novo utilizador.
     * @param nome Nome real.
     * @param username Nome de utilizador.
     * @param password Palavra-passe.
     * @param email Email do utilizador.
     * @param morada Morada.
     * @param age Idade.
     * @param planoOption Plano escolhido.
     */
    public void addUser(String nome, String username, String password, String email, String morada, int age,
            int planoOption) {
        model.addUser(username, nome, password, email, morada, age, planoOption);
    }

    /**
     * @brief Remove um utilizador, se existir.
     * @param username Nome do utilizador.
     * @return true se removido, false caso contrário.
     */
    public boolean removeUser(String username) {
        if (model.userExists(username)) {
            model.removeUser(username);
            return true;
        }
        return false;
    }

    /**
     * @brief Verifica se um utilizador é autêntico.
     * @param username Nome de utilizador.
     * @param password Palavra-passe.
     * @return true se for válido, false caso contrário.
     */
    public boolean authenticUser(String username, String password) {
        return model.userAutentico(username, password);
    }

    /**
     * @brief Verifica se um utilizador existe.
     * @param username Nome do utilizador.
     * @return true se existir, false caso contrário.
     */
    public boolean userExists(String username) {
        return model.userExists(username);
    }

    /**
     * @brief Associa uma playlist a um utilizador.
     * @param nomeP Nome da playlist.
     * @param username Nome do utilizador.
     */
    public void addPlaylistToUser(String nomeP, String username) {
        model.setPlaylistGuardada(nomeP, username);
    }

    // ==== QUERIES ===========

    /**
     * @brief Retorna a música mais ouvida.
     * @return Nome da música ou mensagem de erro.
     */
    public String query1() {
        try {
            
            return model.musicaMaisOuvida();
        } catch (zeroSongsListen e) {
            return e.getMessage();
        }
    }
    
    /**
     * @brief Retorna o intérprete ou género mais ouvido.
     * @param opcao 2 para intérprete, 6 para género.
     * @return Resultado ou mensagem de erro.
     */
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
    
    /**
     * @brief Queries relacionadas a utilizadores e músicas ouvidas.
     * @param opcao Opção da query.
     * @param dataInicio Data de início.
     * @param dataFim Data de fim.
     * @return Resultado ou mensagem de erro.
     */
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
    
    /**
     * @brief Retorna o número de playlists públicas.
     * @return Número de playlists públicas.
     */
    public int query7() {
        int resposta = 0;
        resposta = model.numPlaylistsPublicas();
        return resposta;
    }

    /**
     * @brief Gera uma playlist recomendada.
     * @param username Nome do utilizador.
     * @param opcao Critério de recomendação.
     * @param ngeneros Número de géneros.
     * @param generos Lista de géneros.
     * @param segundos Duração máxima.
     * @return Nome da playlist criada.
     */
    public String gerarPlaylistRecomendada(String username, int opcao, int ngeneros, String generos, int segundos) {
        return model.playlistRecomendada(username, opcao, ngeneros, generos, segundos);
    }

}
