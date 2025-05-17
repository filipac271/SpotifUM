package Application.model.Song;

import java.io.Serializable;

/**
 * Representa uma música com os seus atributos principais,
 * tais como nome, intérprete, editora, letra, pauta, género,
 * duração e número de reproduções.
 */
public class Song implements Serializable {

    /** Nome da música. */
    private String nome;

    /** Nome do intérprete da música. */
    private String interprete;

    /** Editora da música. */
    private String editora;

    /** Letra da música. */
    private String letra;

    /** Pauta da música (partitura). */
    private String pauta;

    /** Género musical da música. */
    private String genero;

    /** Duração da música, em segundos. */
    private int duracao;

    /** Número de vezes que a música foi reproduzida. */
    private int numRep;

    /**
     * Construtor vazio que inicializa todos os campos com valores padrão.
     */
    public Song() {
        this.nome = "";
        this.interprete = "";
        this.editora = "";
        this.letra = "";
        this.pauta = "";
        this.genero = "";
        this.duracao = 0;
        this.numRep = 0;
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
     */
    public Song(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao) {
        this.nome = nome;
        this.interprete = interprete;
        this.editora = editora;
        this.letra = letra;
        this.pauta = pauta;
        this.genero = genero;
        this.duracao = duracao;
        this.numRep = 0;
    }

    /**
     * Construtor de cópia.
     *
     * @param outraMusica Instância de Song a copiar.
     */
    public Song(Song outraMusica) {
        this.nome = outraMusica.getNome();
        this.interprete = outraMusica.getInterprete();
        this.editora = outraMusica.getEditora();
        this.letra = outraMusica.getLetra();
        this.pauta = outraMusica.getPauta();
        this.genero = outraMusica.getGenero();
        this.duracao = outraMusica.getDuracao();
        this.numRep = outraMusica.getNumRep();
    }

    // Getters

    /** @return Nome da música. */
    public String getNome() {
        return nome;
    }

    /** @return Intérprete da música. */
    public String getInterprete() {
        return interprete;
    }

    /** @return Editora da música. */
    public String getEditora() {
        return editora;
    }

    /** @return Letra da música. */
    public String getLetra() {
        return letra ;
    }

    /** @return Pauta (partitura) da música. */
    public String getPauta() {
        return pauta;
    }

    /** @return Género musical da música. */
    public String getGenero() {
        return genero;
    }

    /** @return Duração da música em segundos. */
    public int getDuracao() {
        return duracao;
    }

    /** @return Número de reproduções da música. */
    public int getNumRep() {
        return numRep;
    }

    // Setters

    /**
     * Define o nome da música.
     *
     * @param nome Nome a definir.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o intérprete da música.
     *
     * @param interprete Intérprete a definir.
     */
    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    /**
     * Define a editora da música.
     *
     * @param editora Editora a definir.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Define a letra da música.
     *
     * @param letra Letra a definir.
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    /**
     * Define a pauta (partitura) da música.
     *
     * @param pauta Pauta a definir.
     */
    public void setMusica(String pauta) {
        this.pauta = pauta;
    }

    /**
     * Define o género musical da música.
     *
     * @param genero Género a definir.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Define a duração da música em segundos.
     *
     * @param duracao Duração a definir.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Define o número de reproduções da música.
     *
     * @param numRep Número de reproduções a definir.
     */
    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    /**
     * Incrementa o número de reproduções e devolve a letra da música.
     *
     * @return Letra da música.
     */
    public String getReproducao() {
        this.numRep++;
        return "\n/////////////////Música/////////////////\n" + this.letra + "\n////////////////////////////////////////";
    }

    /**
     * Cria uma representação textual da música com os seus principais atributos.
     *
     * @return String com detalhes da música.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Song [nome: ").append(this.nome)
          .append(", interprete: ").append(this.interprete)
          .append(", editora: ").append(this.editora)
          .append(", genero: ").append(this.genero)
          .append(", duracao: ").append(this.duracao)
          .append(" segundos, numRep: ").append(this.numRep)
          .append("]");
        return sb.toString();
    }

    /**
     * Cria uma cópia profunda da instância atual.
     *
     * @return Novo objeto Song igual a este.
     */
    @Override
    public Song clone() {
        return new Song(this);
    }

    /**
     * Verifica se duas músicas são iguais comparando todos os seus atributos.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} se forem iguais, {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Song song = (Song) obj;

        return duracao == song.duracao &&
               numRep == song.numRep &&
               nome.equals(song.nome) &&
               interprete.equals(song.interprete) &&
               editora.equals(song.editora) &&
               letra.equals(song.letra) &&
               pauta.equals(song.pauta) &&
               genero.equals(song.genero);
    }
}
