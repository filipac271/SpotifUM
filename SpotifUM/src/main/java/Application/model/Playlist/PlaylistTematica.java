package Application.model.Playlist;

import java.util.List;

import Application.model.Song.Song;

/**
 * Representa uma playlist temática com restrições de género musical e duração máxima.
 * Apenas músicas que respeitam o género e que a sua adição não exceda a duração máxima são adicionadas.
 */
public class PlaylistTematica extends Playlist {

    /**
     * Género musical da playlist.
     */
    private final String genero;

    /**
     * Duração máxima total da playlist (em segundos).
     */
    private final int duracaoMaxima;

    /**
     * Construtor com nome, músicas, género e duração máxima.
     * Apenas adiciona músicas que respeitam as restrições.
     *
     * @param nome Nome da playlist.
     * @param musicas Lista de músicas a tentar adicionar.
     * @param genero Género da playlist.
     * @param duracaoMaxima Duração máxima permitida.
     */
    public PlaylistTematica(String nome, List<Song> musicas, String genero, int duracaoMaxima) {

        //super(nome, List.of());
        //super(); // inicia vazia
        super(nome, List.of(), false); // inicia vazia
        this.genero = genero;
        this.duracaoMaxima = duracaoMaxima;

        // adicionar apenas as que respeitam as restrições
        for (Song s : musicas) {
            this.adicionarMusica(s);
        }
    }

    /**
     * Construtor com nome, género e duração máxima (sem músicas iniciais).
     *
     * @param nome Nome da playlist.
     * @param genero Género musical permitido.
     * @param duracaoMaxima Duração máxima em segundos.
     */
    public PlaylistTematica(String nome, String genero, int duracaoMaxima) {
        //super();
        super(nome, List.of(), false);
        this.genero = genero;
        this.duracaoMaxima = duracaoMaxima;
    }

    /**
     * Construtor por omissão.
     * Inicializa com nome vazio, género vazio e duração máxima 0.
     */
    public PlaylistTematica() {
        super();
        this.genero = "";
        this.duracaoMaxima = 0;
    }

    /**
     * Construtor de cópia.
     *
     * @param outra A playlist temática a copiar.
     */
    public PlaylistTematica(PlaylistTematica outra) {
        super(outra);
        this.genero = outra.genero;
        this.duracaoMaxima = outra.duracaoMaxima;
    }

    /**
     * Obtém o género da playlist.
     *
     * @return Género da playlist.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Obtém a duração máxima da playlist.
     *
     * @return Duração máxima (em segundos).
     */
    public int getDuracaoMaxima() {
        return duracaoMaxima;
    }

    /**
     * Obtém a duração atual (total das músicas já adicionadas).
     *
     * @return Duração atual da playlist.
     */
    public int getDuracaoAtual() {
        return super.getDuracaoTotal();
    }

    /**
     * Tenta adicionar uma música à playlist, respeitando género e duração máxima.
     *
     * @param musica Música a adicionar.
     */
    @Override
    public void adicionarMusica(Song musica) {
        if (!musica.getGenero().equalsIgnoreCase(genero)) return;
        if (getDuracaoAtual() + musica.getDuracao() > duracaoMaxima) return;
        super.adicionarMusica(musica);
    }

    /**
     * Reproduz a playlist, imprimindo a letra de cada música.
     */


    /**
     * Cria uma cópia/clone da playlist temática.
     *
     * @return Nova instância com os mesmos dados.
     */
    @Override
    public PlaylistTematica clone() {
        return new PlaylistTematica(this);
    }

    /**
     * Representação textual da playlist temática.
     *
     * @return String com os dados principais da playlist.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlaylistTematica{")
          .append("nome='").append(getNome()).append('\'')
          .append(", genero='").append(genero).append('\'')
          .append(", duracaoMaxima=").append(duracaoMaxima)
          .append(", duracaoAtual=").append(getDuracaoAtual())
          .append(", numMusicas=").append(tamanho())
          .append('}');
        return sb.toString();
    }
}
