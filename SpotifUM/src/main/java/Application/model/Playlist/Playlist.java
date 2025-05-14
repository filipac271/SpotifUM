package Application.model.Playlist;

import java.util.List;
import java.util.ArrayList;

import Application.model.Song.Song;
import java.io.Serializable;


/**
 * Classe abstrata que representa uma playlist de músicas.
 * 
 * Uma playlist pode ser pública ou privada, conter múltiplas músicas
 * e oferece funcionalidades como adicionar, remover e calcular a duração total.
 * Subclasses devem implementar os métodos {@code clone} e {@code reproduzir}.
 */
public abstract class Playlist implements Serializable{

    /**
     * Nome da playlist.
     */
    protected String nome;

    /**
     * Lista de músicas da playlist.
     */
    protected List<Song> musicas;

    /**
     * Indica se a playlist é pública (visível a outros utilizadores).
     */
    private boolean publica;

    /**
     * Construtor com parâmetros.
     * 
     * @param nome Nome da playlist.
     * @param musicas Lista de músicas.
     * @param publica Indica se a playlist é pública.
     */
    public Playlist(String nome, List<Song> musicas, boolean publica) {
        this.nome = nome;
        this.musicas = new ArrayList<>(musicas);
        this.publica = publica;
    }

    /**
     * Construtor por omissão.
     * Cria uma playlist vazia, privada e sem nome.
     */
    public Playlist() {
        this.nome = "";
        this.musicas = new ArrayList<>();
        this.publica = false;
    }

    /**
     * Construtor de cópia.
     * 
     * @param outra Playlist a copiar.
     */
    public Playlist(Playlist outra) {
        this.nome = outra.getNome();
        this.musicas = outra.getMusicas();
        this.publica = outra.getPublica();
    }

    /**
     * Obtém o nome da playlist.
     * 
     * @return Nome da playlist.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da playlist.
     * 
     * @param nome Novo nome da playlist.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a lista de músicas da playlist (cópia shallow).
     * 
     * @return Lista de músicas .
     */
    public List<Song> getMusicas() {

        return new ArrayList<>(musicas);

    }



    public Song getNMusica(int n)
    {
       
        return musicas.get(n);

    }

    /**
     * Define a lista de músicas da playlist (usa cópias).
     * 
     * @param musicas Lista de músicas a definir.
     */
    public void setMusicas(List<Song> musicas) {
        this.musicas = new ArrayList<>(musicas);
    }

    /**
     * Verifica se a playlist é pública.
     * 
     * @return true se for pública, false caso contrário.
     */
    public boolean getPublica() {
        return this.publica;
    }

    /**
     * Define se a playlist é pública ou privada.
     * 
     * @param publica true para tornar pública, false para privada.
     */
    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    /**
     * Adiciona uma música à playlist (usa cópia).
     * 
     * @param musica Música a adicionar.
     */
    public void adicionarMusica(Song musica) {
        musicas.add(musica);
    }

    public void adicionarMusicaIndex(Song musica, int n) {
        musicas.add(n,musica);
    }

    /**
     * Remove uma música da playlist.
     * 
     * @param musica Música a remover.
     */
    public void removerMusica(Song musica) {
        musicas.remove(musica);
    }

    /**
     * Calcula a duração total da playlist (em segundos).
     * 
     * @return Duração total das músicas.
     */
    public int getDuracaoTotal() {
        return musicas.stream().mapToInt(song -> song.getDuracao()).sum();
    }

    /**
     * Verifica se a playlist está vazia.
     * 
     * @return true se não tiver músicas, false caso contrário.
     */
    public boolean isEmpty() {
        return musicas.isEmpty();
    }

    /**
     * Obtém o número de músicas da playlist.
     * 
     * @return Tamanho da playlist.
     */
    public int tamanho() {
        return musicas.size();
    }

    /**
     * Cria uma cópia da playlist.
     * Deve ser implementado pelas subclasses.
     * 
     * @return Nova instância da playlist (cópia).
     */
    public abstract Playlist clone();


}
