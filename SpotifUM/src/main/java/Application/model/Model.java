package Application.model;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.time.LocalDate;

import Application.model.Playlist.Playlist;
import Application.model.Playlist.PlaylistRandom;
import Application.model.Playlist.PlaylistTematica;
import Application.model.Playlist.PlaylistUser;
import Application.model.Song.Explicito;
import Application.model.Song.Song;
import Application.model.Song.SongExplicit;
import Application.model.Song.SongMediaExplicit;
import Application.model.Song.SongMultimedia;
import Application.model.User.Historico;
import Application.model.User.User;
import Application.model.Album.Album;
import Application.model.PlanoSubscricao.PlanoPremium;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.exceptions.zeroGenresListen;
import Application.exceptions.zeroInterpretesListen;
import Application.exceptions.zeroSongsListen;
import Application.exceptions.zeroUsersWithPlaylists;
import Application.exceptions.zeroUsersWithPoints;

/**
 * @class Model
 * @brief Classe responsável por gerir e manter os dados centrais da aplicação,
 *        incluindo utilizadores, playlists, músicas e álbuns.
 */
public class Model {

    /// Tabela que associa IDs a playlists.
    private Map<String, Playlist> playlistTable;

    /// Tabela que associa IDs a músicas.
    private Map<String, Song> songTable;

    /// Tabela que associa usernames a utilizadores.
    private Map<String, User> userTable;

    /// Tabela que associa IDs a álbuns.
    private Map<String, Album> albumTable;

    /**
     * @brief Construtor da classe Model.
     *
     *        Tenta carregar os dados persistidos de utilizadores, playlists,
     *        músicas e álbuns. Caso falhe, inicializa mapas vazios.
     */
    public Model() {
        try {
            this.userTable = Persistencia.loadUsers();
            this.playlistTable = Persistencia.loadPlaylists();
            this.songTable = Persistencia.loadSongs();
            this.albumTable = Persistencia.loadAlbuns();

            reconciliarAlbunsEPlaylistsComSongs();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Inicializa mapas vazios como fallback
            this.userTable = new HashMap<>();
            this.playlistTable = new HashMap<>();
            this.songTable = new HashMap<>();
            this.albumTable = new HashMap<>();
        }
    }

    /**
     * @brief Salva todos os dados (utilizadores, playlists, músicas, álbuns) no
     *        sistema de persistência.
     *
     *        Função que chama os métodos de salvar para users, playlists e songs
     */
    public void saveAll() {
        try {
            Persistencia.saveUsers(userTable);
            Persistencia.savePlaylists(playlistTable);
            Persistencia.saveSongs(songTable);
            Persistencia.saveAlbum(albumTable);

            // Funções de debugging que printam a base de dados
            Utils.printMap(userTable);
            Utils.printMap(playlistTable);
            Utils.printMap(songTable);
            Utils.printMap(albumTable);

        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    /**
     * @brief Reconcilia objetos de músicas dentro de álbuns e playlists com os
     *        objetos da tabela de músicas.
     *
     *        Garante que as instâncias de músicas em álbuns e playlists sejam
     *        consistentes
     *        com as instâncias armazenadas em songTable.
     */
    private void reconciliarAlbunsEPlaylistsComSongs() {
        // Reconcilia álbuns
        for (Album album : albumTable.values()) {
            List<Song> novasMusicas = album.getAlbum().stream()
                    .map(m -> songTable.getOrDefault(m.getNome(), m))
                    .collect(Collectors.toList());
            album.setAlbum(novasMusicas);
        }

        // Reconcilia playlists
        for (Playlist playlist : playlistTable.values()) {
            List<Song> novasMusicas = playlist.getMusicas().stream()
                    .map(m -> songTable.getOrDefault(m.getNome(), m))
                    .collect(Collectors.toList());
            playlist.setMusicas(novasMusicas);
        }

        // Reconcilia planos dos utilizadores
        for (User user : userTable.values()) {
            PlanoSubscricao planoOriginal = user.getPlano();

            if (planoOriginal instanceof PlanoPremium premiumOriginal) {
                // Faz uma cópia atualizável do plano
                PlanoPremium planoRecon = (PlanoPremium) premiumOriginal;

                // Reconcilia playlists
                List<Playlist> playlistsRecon = planoRecon.getPlaylists().stream()
                        .map(p -> playlistTable.getOrDefault(p.getNome(), p))
                        .collect(Collectors.toList());
                planoRecon.setPlaylists(playlistsRecon);

                // Reconcilia álbuns
                List<Album> albunsRecon = planoRecon.getAlbuns().stream()
                        .map(a -> albumTable.getOrDefault(a.getNome(), a))
                        .collect(Collectors.toList());
                planoRecon.setAlbuns(albunsRecon);

                // Atualiza o plano no utilizador
                user.setPlano(planoRecon);
            }
        }

    }

    /**
     * @brief Construtor alternativo que permite injeção direta das tabelas.
     * 
     * @param playlists Mapa de playlists.
     * @param songs     Mapa de músicas.
     * @param users     Mapa de utilizadores.
     * @param albums    Mapa de álbuns.
     */
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

    /**
     * @brief Adiciona um novo utilizador à base de dados.
     * 
     * @param username    Nome de utilizador.
     * @param nome        Nome completo.
     * @param password    Palavra-passe.
     * @param email       Email.
     * @param morada      Morada.
     * @param age         Idade.
     * @param planoOption Opção do plano de subscrição.
     */
    public void addUser(String username, String nome, String password, String email,
            String morada, int age, int planoOption) {

        User user = new User(nome, username, password, email, morada, age, planoOption);
        userTable.put(username, user);
    }

    /**
     * @brief Retorna um utilizador a partir do username.
     * 
     * @param username Nome de utilizador.
     * @return Objeto User correspondente ou null se não existir.
     */
    private User getUser(String username) {
        return userTable.get(username);
    }

    /**
     * @brief Remove um utilizador da base de dados.
     * 
     * @param username Nome de utilizador.
     * @return true se foi removido com sucesso, false caso contrário.
     */
    public boolean removeUser(String username) {
        return userTable.remove(username) != null;
    }

    /**
     * @brief Verifica se um utilizador existe na base de dados.
     * 
     * @param username Nome de utilizador.
     * @return true se existir, false caso contrário.
     */
    public boolean userExists(String username) {
        return userTable.containsKey(username);
    }

    /**
     * @brief Registra a reprodução de uma música por um utilizador.
     * 
     *        Atualiza o histórico e os pontos do utilizador com base no plano.
     * 
     * @param music    Nome da música.
     * @param username Nome do utilizador.
     * @return Letra da música (pode variar com idade e tipo de música).
     */
    public String userReproduziu(String music, String username) {
        String letra;
        User user = getUser(username);
        Song musica = getSong(music);
        if (musica instanceof SongExplicit) {
            letra = ((SongExplicit) musica).getSongExplicit(user.getAge());
        } else if (musica instanceof SongMediaExplicit) {
            letra = ((SongMediaExplicit) musica).getSongExplicit(user.getAge());
        } else {
            letra = musica.getReproducao();
        }

        LocalDate data = LocalDate.now();
        user.addHistorico(musica, data);
        double pontosAtuais = user.getPontos();
        PlanoSubscricao plano = user.getPlano();
        double pontosAtualizados = plano.calculaPontos(pontosAtuais);
        user.setPontos(pontosAtualizados);
        return letra;
    }

    /**
     * @brief Altera o plano de subscrição de um utilizador.
     * 
     *        Preserva playlists e álbuns caso o utilizador já esteja num plano
     *        premium.
     * 
     * @param username    Nome de utilizador.
     * @param planoInt    Identificador do novo plano (1, 2 ou 3).
     * @param planoString Nome do plano atual (para verificação).
     * @return true se a alteração foi feita, false se já estava no mesmo plano.
     */
    public String changeUserPlano(String username, int planoInt) {
        User user = getUser(username);
        String planoPresente = user.getPlano().getNome();
        PlanoSubscricao novoPlano = user.getPlanoByOption(planoInt);
        if (planoPresente.equals(novoPlano.getNome()))
            return "";
        if (planoInt == 1) {
            user.setPlano(novoPlano);
            return novoPlano.getNome();
        }
        if (!(planoPresente.equals("PlanoFree"))) {
            PlanoSubscricao planoCopy = user.getPlano();
            List<Playlist> playlists = planoCopy.getPlaylists();
            List<Album> albums = planoCopy.getAlbuns();

            novoPlano.setPlaylists(playlists);
            novoPlano.setAlbuns(albums);
            user.setPlano(novoPlano);
        } else {
            user.setPlano(novoPlano);
        }

        return novoPlano.getNome();
    }

    /**
     * @brief Verifica se o utilizador é autêntico (username e password coincidem).
     * 
     * @param username Nome de utilizador.
     * @param password Palavra-passe fornecida.
     * @return true se as credenciais forem válidas, false caso contrário.
     */
    public boolean userAutentico(String username, String password) {
        User user = getUser(username);
        return user != null && password.equals(user.getPassword());
    }

    /**
     * @brief Retorna o nome do plano de subscrição atual de um utilizador.
     * 
     * @param username Nome de utilizador.
     * @return Nome do plano.
     */
    public String getPlanodoUser(String username) {
        User u = getUser(username);
        return u.getPlano().getNome();
    }

    // === Song ===

    /**
     * @brief Adiciona uma nova música à tabela de músicas.
     * 
     *        Cria uma instância do tipo de música apropriado com base nos
     *        parâmetros `isExplicit` e `isMedia`.
     * 
     * @param nome       Nome da música.
     * @param interprete Nome do intérprete.
     * @param editora    Nome da editora.
     * @param letra      Letra da música.
     * @param pauta      Pauta musical.
     * @param genero     Género musical.
     * @param duracao    Duração da música (em segundos).
     * @param isExplicit Indica se a música é explícita ("s" para sim).
     * @param isMedia    Indica se é música multimédia ("s" para sim).
     * @param url        URL do conteúdo multimédia, se aplicável.
     */
    public void addSong(String nome, String interprete, String editora, String letra, String pauta,
            String genero, int duracao, int isExplicit, int isMedia, String url) {

        Song song;

        boolean media = isMedia==1;
        boolean explicit = isExplicit==1;

        if (media) {
            if (explicit) {
                song = new SongMediaExplicit(nome, interprete, editora, letra, pauta, genero, duracao, url);
            } else {
                song = new SongMultimedia(nome, interprete, editora, letra, pauta, genero, duracao, url);
            }
        } else {
            if (explicit) {
                song = new SongExplicit(nome, interprete, editora, letra, pauta, genero, duracao);
            } else {
                song = new Song(nome, interprete, editora, letra, pauta, genero, duracao);
            }
        }

        songTable.put(nome, song);
    }

    /**
     * @brief Obtém uma música da tabela de músicas pelo nome.
     * 
     * @param name Nome da música.
     * @return Instância da música ou null se não existir.
     */
    private Song getSong(String name) {
        return songTable.get(name);
    }

    /**
     * @brief Remove uma música da tabela de músicas.
     * 
     * @param name Nome da música a remover.
     * @return true se a música foi removida com sucesso, false se não existia.
     */
    public boolean removeSong(String name) {
        return songTable.remove(name) != null;
    }

    /**
     * @brief Verifica se uma música existe na tabela.
     * 
     * @param name Nome da música.
     * @return true se a música existir, false caso contrário.
     */
    public boolean songExists(String name) {
        return songTable.containsKey(name);
    }

    // === Playlist ===

    /**
     * @brief Adiciona uma nova playlist à tabela de playlists.
     * 
     *        Se for temática, cria uma PlaylistTematica, caso contrário cria uma
     *        PlaylistUser.
     * 
     * @param nomeP         Nome da playlist.
     * @param publica       Indica se a playlist é pública.
     * @param musicas       Lista de músicas a adicionar.
     * @param tematica      Indica se a playlist é temática.
     * @param genero        Género associado (apenas para playlists temáticas).
     * @param duracaoMaxima Duração máxima da playlist (apenas para playlists
     *                      temáticas).
     */
    public void addPlaylist(String nomeP, boolean publica, List<Song> musicas, boolean tematica, String genero,
            int duracaoMaxima) {
        Playlist playlist;
        if (tematica) {
            // usa PlaylistTematica
            playlist = new PlaylistTematica(nomeP, musicas, publica, genero, duracaoMaxima);
        } else {
            // usa a playlist normal do utilizador
            playlist = new PlaylistUser(nomeP, musicas, publica);
        }
        playlistTable.put(nomeP, playlist);
    }

    /**
     * @brief Adiciona uma música existente a uma playlist existente.
     * 
     * @param nomeP Nome da playlist.
     * @param nomeM Nome da música.
     */
    public void addToPlaylist(String nomeP, String nomeM) {
        Song musica = songTable.get(nomeM);
        playlistTable.get(nomeP).adicionarMusica(musica);
    }

    /**
     * @brief Obtém uma playlist pelo nome.
     * 
     * @param name Nome da playlist.
     * @return Playlist correspondente ou null se não existir.
     */
    private Playlist getPlaylist(String name) {
        return playlistTable.get(name);
    }

    /**
     * @brief Remove uma playlist da tabela.
     * 
     * @param name Nome da playlist.
     * @return true se a playlist foi removida, false se não existia.
     */
    public boolean removePlaylist(String name) {
        return playlistTable.remove(name) != null;
    }

    /**
     * @brief Verifica se uma playlist existe.
     * 
     * @param name Nome da playlist.
     * @return true se existir, false caso contrário.
     */
    public boolean playlistExists(String name) {
        return playlistTable.containsKey(name);
    }

    /**
     * @brief Verifica se uma playlist é acessível por um determinado utilizador.
     * 
     * @param username Nome de utilizador.
     * @param nome     Nome da playlist.
     * @return true se a playlist for pública ou guardada pelo utilizador, false
     *         caso contrário.
     */
    public boolean playlistAcessivel(String username, String nome) {
        if (getPlaylist(nome).getPublica()) {
            return true;
        } else {
            Playlist p = getPlaylist(nome);
            User user = getUser(username);
            PlanoSubscricao plano = user.getPlano();
            return plano.playlistGuardada(p);
        }
    }

    /**
     * @brief Cria uma playlist aleatória com todas as músicas existentes.
     * 
     * @return Uma String com o nome da PlaylistRandom criada e armazenada.
     */
    public String createPlaylistRandom() {
        List<Song> todasAsMusicas = new ArrayList<>(songTable.values());
        Collections.shuffle(todasAsMusicas);
        PlaylistRandom playlist = new PlaylistRandom("random", todasAsMusicas, false);
        playlistTable.put(playlist.getNome(), playlist);
        return playlist.getNome();
    }

    /**
     * @brief Obtém os nomes das primeiras N músicas de uma playlist.
     * 
     * @param nomeP Nome da playlist.
     * @param n     Número de músicas a listar.
     * @return Lista com os nomes das músicas.
     */
    public List<String> getNomeMusicas(String nome, String tipo) {
        List<String> nomeMusicas = new ArrayList<>();
        int n=0;

        if(tipo.equals("album"))
        {
            Album a=getAlbum(nome);
            n=a.tamanho();
            for (int i = 0; i < n; i++) {
                nomeMusicas.add(a.getNMusica(i).getNome());
           }
        }
        else
        {
            Playlist p = getPlaylist(nome);
            p.tamanho();
            for (int i = 0; i < n; i++) {
                 nomeMusicas.add(p.getNMusica(i).getNome());
            }
        }
        
        return nomeMusicas;
    }

    /**
     * @brief Troca duas músicas de posição numa playlist.
     * 
     * @param nomeP Nome da playlist.
     * @param i     Índice da primeira música.
     * @param index Índice da segunda música.
     */
    public void trocaMusicas(String nomeP, int i, int index) {
        Playlist p = getPlaylist(nomeP);
        Song s1 = p.getNMusica(index);
        p.adicionarMusicaIndex(p.getNMusica(i), index);
        p.adicionarMusicaIndex(s1, i);
        playlistTable.replace(nomeP, p);
    }

    /**
     * @brief Ordena uma playlist com base na opção fornecida.
     * 
     * @param nomeP Nome da playlist.
     * @param op    Opção de ordenação:
     *              1 - Nome da música A-Z
     *              2 - Nome da música Z-A
     *              3 - Nome do intérprete A-Z
     *              4 - Nome do intérprete Z-A
     */
    public void ordenarPlaylist(String nomeP, int op) {
        Playlist playlist = getPlaylist(nomeP);
       List<Song> songs= playlist.getMusicas();
        switch (op) {
            case 1: // Nome da música A-Z
             songs.sort((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
                break;
            case 2: // Nome da música Z-A
            songs.sort((s1, s2) -> s2.getNome().compareToIgnoreCase(s1.getNome()));
                break;
            case 3: // Nome do intérprete A-Z
            songs.sort((s1, s2) -> s1.getInterprete().compareToIgnoreCase(s2.getInterprete()));
                break;
            case 4: // Nome do intérprete Z-A
            songs.sort((s1, s2) -> s2.getInterprete().compareToIgnoreCase(s1.getInterprete()));
                break;
            default:
                break;
        }
        
        playlist.setMusicas(songs);
        playlistTable.put(nomeP, playlist);
    }

    /**
     * @brief Guarda uma playlist no plano premium de um utilizador.
     * 
     * @param username Nome de utilizador.
     * @param nome     Nome da playlist a guardar.
     */
    public void guardarPlaylistUser(String username, String nome) {
        Playlist playlist = getPlaylist(nome);
        User user = getUser(username);
        PlanoSubscricao plano = user.getPlano();
        plano.guardarPlaylist(playlist);
        user.setPlano(plano);
    }

    /**
     * @brief Obtém o número de músicas numa playlist.
     * 
     * @param nome Nome da playlist.
     * @return Número de músicas na playlist.
     */
    public int getPlaylistTamanho(String nome) {
        Playlist playlist = getPlaylist(nome);
        return playlist.tamanho();
    }

    /**
     * @brief Obtém o nome de uma música numa determinada posição da playlist.
     * 
     * @param nome  Nome da playlist.
     * @param index Índice da música.
     * @return Nome da música nessa posição.
     */
    public String getPlaylistNMusica(String nome, int index) {
        Playlist playlist = getPlaylist(nome);
        return playlist.getNMusica(index).getNome();
    }

    // === Album ===

    /**
     * @brief Adiciona um novo álbum vazio à tabela de álbuns.
     * 
     * @param nome    Nome do álbum.
     * @param artista Nome do artista.
     */
    public void addAlbum(String nome, String artista) {
        List<Song> albumList = new ArrayList<>();
        Album album = new Album(nome, artista, albumList);
        albumTable.put(nome, album);
    }

    /**
     * @brief Obtém um álbum pelo nome.
     * 
     * @param name Nome do álbum.
     * @return Álbum correspondente ou null se não existir.
     */
    private Album getAlbum(String name) {
        return albumTable.get(name);
    }

    /**
     * @brief Remove um álbum da tabela.
     * 
     * @param name Nome do álbum.
     * @return true se foi removido, false se não existia.
     */
    public boolean removeAlbum(String name) {
        return albumTable.remove(name) != null;
    }

    /**
     * @brief Verifica se um álbum existe.
     * 
     * @param name Nome do álbum.
     * @return true se existir, false caso contrário.
     */
    public boolean albumExists(String name) {
        return albumTable.containsKey(name);
    }

    /**
     * @brief Adiciona uma música existente a um álbum existente.
     * 
     * @param nomeA Nome do álbum.
     * @param nomeM Nome da música.
     */
    public void addToAlbum(String nomeA, String nomeM) {
        Song musica = songTable.get(nomeM);
        albumTable.get(nomeA).addSong(musica);
    }

    /**
     * @brief Guarda um álbum no plano premium de um utilizador.
     * 
     * @param username Nome de utilizador.
     * @param nome     Nome do álbum a guardar.
     */
    public void guardarAlbumUser(String username, String nome) {
        Album album = getAlbum(nome);
        User user = getUser(username);
        PlanoSubscricao plano = user.getPlano();

        plano.guardarAlbum(album);
        user.setPlano(plano);
    }

    /**
     * @brief Obtém o número de músicas num álbum.
     * 
     * @param name Nome do álbum.
     * @return Número de músicas no álbum.
     */
    public int getAlbumTamanho(String name) {
        Album album = getAlbum(name);
        return album.tamanho();
    }

    /**
     * @brief Obtém o nome de uma música numa posição específica do álbum.
     * 
     * @param name  Nome do álbum.
     * @param index Índice da música.
     * @return Nome da música nessa posição.
     */
    public String getAlbumNMusica(String name, int index) {
        Album album = getAlbum(name);
        return album.getNMusica(index).getNome();
    }

    public boolean musicaPertenceAlbum(String nomeA, String nomeM)
    {
        Album a=getAlbum(nomeA);
        return a.contemMusica(nomeM);
    }

    public void removeMusicAlbum(String nome, int index)
    {
        Album a=getAlbum(nome);
        Song s= a.getNMusica(index);
        a.removeSong(s);
    }

    // Querys

    // Query 1- Qual a música mais reproduzida?
    /**
     * @brief Retorna a música mais reproduzida.
     * 
     * @return String com a representação da música mais reproduzida.
     * @throws zeroSongsListen se nenhuma música tiver sido reproduzida.
     */
    public String musicaMaisOuvida() {
        Song maisReproduzida = null;
        int maiorNumRep = -1;

        for (Map.Entry<String, Song> entry : songTable.entrySet()) {
            Song song = entry.getValue();
            if (song.getNumRep() > maiorNumRep) {
                maiorNumRep = song.getNumRep();
                maisReproduzida = song;
            }
        }

        if (maisReproduzida == null)
            throw new zeroSongsListen();
        return maisReproduzida.toString();
    }

    // Query 2 - Qual o interprete mais escutado?
    /**
     * @brief Retorna o intérprete mais escutado, com base no total de reproduções.
     * 
     * @return Nome do intérprete mais ouvido.
     * @throws zeroInterpretesListen se nenhum intérprete tiver sido escutado.
     */
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

        if (interpreteMaisOuvido == null)
            throw new zeroInterpretesListen();
        return interpreteMaisOuvido;
    }

    // Query 3 - Qual o utilizador que mais músicas ouviu num período de tempo ou
    // desde sempre?
    // Consideramos que cada musica só se conte uma vez mesmo o user ouvindo-a
    // várias vezes
    /**
     * @brief Retorna o utilizador que ouviu mais músicas num intervalo de tempo (ou
     *        desde sempre).
     * 
     * @param dataInicio Data de início do intervalo.
     * @param dataFim    Data de fim do intervalo.
     * @return String com a representação do utilizador que mais músicas ouviu.
     * @throws zeroSongsListen se nenhum utilizador tiver ouvido músicas no
     *                         intervalo.
     */
    public String userMaisMusicasOuvidas(LocalDate dataInicio, LocalDate dataFim) {
        User userMaisMusicas = null;
        int maiorNumMusicas = 0;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            int nmusicas = user.numMusicasOuvidas(dataInicio, dataFim);
            if (nmusicas > maiorNumMusicas) {
                maiorNumMusicas = nmusicas;
                userMaisMusicas = user;
            }
        }
        if (userMaisMusicas == null)
            throw new zeroSongsListen();

        return userMaisMusicas.toString();
    }

    // Query 4- Qual o Utilizador com mais pontos?
    /**
     * @brief Retorna o utilizador com mais pontos acumulados.
     * 
     * @return String com a representação do utilizador com mais pontos.
     * @throws zeroUsersWithPoints se não houver utilizadores com pontos.
     */
    public String userMaisPontos() {
        User userMaisPontos = null;
        double maiorNumPontos = -1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getPontos() > maiorNumPontos) {
                maiorNumPontos = user.getPontos();
                userMaisPontos = user;
            }
        }
        if (userMaisPontos == null)
            throw new zeroUsersWithPoints();
        return userMaisPontos.toString();
    }

    // Query 5- Qual o tipo de música mais reproduzida?
    /**
     * @brief Retorna o género musical mais reproduzido.
     * 
     * @return Nome do género musical mais ouvido.
     * @throws zeroGenresListen se nenhum género tiver sido escutado.
     */
    public String generoMaisOuvido() {

        Map<String, Integer> generos = new HashMap<>();

        for (Song song : songTable.values()) {
            String genero = song.getGenero();
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

        if (generoMaisOuvido == null)
            throw new zeroGenresListen();
        return generoMaisOuvido;
    }

    // Query 6 - Quantas playlists públicas existem?
    /**
     * @brief Conta o número de playlists públicas existentes.
     * 
     * @return Número de playlists públicas.
     */
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

    // Query 7 - Qual o utilizador com mais playlists?
    /**
     * @brief Retorna o utilizador com mais playlists criadas.
     * 
     * @return String com a representação do utilizador com mais playlists.
     * @throws zeroUsersWithPlaylists se nenhum utilizador tiver playlists.
     */
    public String userMaisPlaylists() {
        User userMaisPlaylists = null;
        int maisPlaylists = 0;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getNumPlaylists() > maisPlaylists) {
                maisPlaylists = user.getNumPlaylists();
                userMaisPlaylists = user;
            }
        }
        if (userMaisPlaylists == null)
            throw new zeroUsersWithPlaylists();
        return userMaisPlaylists.toString();
    }

    // RECOMENDADOR
    /**
     * @brief Gera uma playlist recomendada para o utilizador com base no histórico.
     * 
     * @param username Nome de utilizador.
     * @param opcao    Define o tipo de músicas: 1 - todas, 2 - apenas explícitas.
     * @param ngeneros Número de géneros especificados.
     * @param generos  String com os géneros separados por espaço (ex: "rock pop
     *                 jazz").
     * @param segundos Duração máxima total da playlist recomendada (0 para sem
     *                 limite).
     * @return Uma playlist com músicas recomendadas.
     */
    public Playlist recomendarMusicas(String username, int opcao, int ngeneros, String generos, int segundos) {
        User user = getUser(username);
        Map<Song, Integer> contagem = new HashMap<>();
        List<Historico> historico = user.getHistorico();
        String[] generosArray = new String[0];

        if (ngeneros != 0 && generos != null && !generos.isEmpty()) {
            generos = generos.trim().replaceAll("\\s+", " "); // tira espaços a mais no fim e no inicio
            generosArray = generos.split(" "); // separa as palavras pelos espaços
        }

        for (Historico h : historico) {
            Song musica = h.getMusica();

            // Se for explicita e o utilizador quiser musica explicita opcao == 2 entao a
            // musica explicita fica
            if ((opcao == 2) && !(musica instanceof Explicito)) {
                continue;
            }

            if (ngeneros != 0) {
                boolean generoAceite = false;
                for (String genero : generosArray) {
                    if (musica.getGenero().equalsIgnoreCase(genero.trim())) {
                        generoAceite = true;
                        break;
                    }
                }
                if (!generoAceite)
                    continue;
            }

            boolean jaExiste = contagem.keySet().stream()
                    .anyMatch(s -> s.getNome().equalsIgnoreCase(musica.getNome()));

            if (!jaExiste){
                contagem.put(musica, contagem.getOrDefault(musica, 0) + 1);
            }

        }

        List<Song> ordenadas = contagem.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Limite de tempo -> Opcao 1
        if (segundos != 0) {
            List<Song> selecionadas = new ArrayList<>();
            int totalSegundos = 0;
            for (Song musica : ordenadas) {
                int duracao = musica.getDuracao();
                if (totalSegundos + duracao <= segundos) {
                    selecionadas.add(musica);
                    totalSegundos += duracao;
                } else {
                    break;
                }
            }
            Playlist p = new PlaylistUser("Recomendações para " + username, selecionadas, false);
            return p;
        } else {
            Playlist p = new PlaylistUser("Recomendações para " + username, ordenadas, false);

            return p;
        }
    }

    /**
     * @brief Cria e guarda automaticamente uma playlist recomendada para o
     *        utilizador.
     * 
     * @param username Nome de utilizador.
     * @param opcao    Define o tipo de músicas: 1 - todas, 2 - apenas explícitas.
     * @param ngeneros Número de géneros especificados.
     * @param generos  String com os géneros separados por espaço (ex: "rock pop
     *                 jazz").
     * @param segundos Duração máxima total da playlist recomendada (0 para sem
     *                 limite).
     * @return Nome da playlist recomendada gerada.
     */
    public String playlistRecomendada(String username, int opcao, int ngeneros, String generos, int segundos) {
        Playlist musicasRecomendadas = recomendarMusicas(username, opcao, ngeneros, generos, segundos);

        addPlaylist(musicasRecomendadas.getNome(), musicasRecomendadas.getPublica(), musicasRecomendadas.getMusicas(),
                false, "", 0);

        guardarPlaylistUser(username, musicasRecomendadas.getNome());

        return musicasRecomendadas.getNome();
    }

}