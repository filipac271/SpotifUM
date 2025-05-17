package Application.model.Song;

/**
 * Representa uma música multimédia que inclui um URL de vídeo associado.
 * Estende a classe {@link Song}.
 */
public class SongMultimedia extends Song {

    /** URL do vídeo associado à música. */
    private String videoUrl;

    /**
     * Construtor vazio que inicializa a música multimédia com valores padrão.
     */
    public SongMultimedia() {
        super();
        this.videoUrl = "";
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome Nome da música.
     * @param interprete Intérprete da música.
     * @param editora Editora da música.
     * @param letra Letra da música.
     * @param pauta Pauta (partitura) da música.
     * @param genero Género musical.
     * @param duracao Duração da música em segundos.
     * @param videoUrl URL do vídeo associado.
     */
    public SongMultimedia(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao, String videoUrl) {
        super(nome, interprete, editora, letra, pauta, genero, duracao);
        this.videoUrl = videoUrl;
    }

    /**
     * Construtor de cópia.
     *
     * @param outra Instância de {@code SongMultimedia} a copiar.
     */
    public SongMultimedia(SongMultimedia outra) {
        super(outra);
        this.videoUrl = outra.videoUrl;
    }

    /**
     * Devolve a URL do vídeo associado.
     *
     * @return URL do vídeo.
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Define a URL do vídeo associado.
     *
     * @param videoUrl URL do vídeo a definir.
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Devolve a letra da música precedida pela URL do vídeo e uma linha de separação.
     *
     * @return String contendo a URL do vídeo, uma linha separadora e a letra da música.
     */
    @Override 
    public String getLetra() {
        return videoUrl + "\n" + "_".repeat(videoUrl.length()) + "\n" + super.getLetra();
    }

    /**
     * Devolve uma representação textual da música multimédia.
     *
     * @return String com a descrição da música multimédia incluindo o URL do vídeo.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", videoUrl=").append(this.videoUrl);
        return sb.toString();
    }

    /**
     * Cria e devolve uma cópia desta instância.
     *
     * @return Clonagem desta música multimédia.
     */
    @Override
    public SongMultimedia clone() {
        return new SongMultimedia(this);
    }

    /**
     * Regista uma reprodução da música, incrementando o contador de reproduções,
     * e devolve a letra da música.
     *
     * @return Letra da música.
     */
    @Override
    public String getReproducao() {
        int numRep = getNumRep();
        setNumRep(numRep + 1);
        return getLetra();
    }

    /**
     * Verifica se um objeto é igual a esta instância.
     * Dois objetos são iguais se forem do mesmo tipo e tiverem os mesmos atributos,
     * incluindo os da superclasse.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} se forem iguais, {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SongMultimedia)) return false;

        return true;
    }
}
