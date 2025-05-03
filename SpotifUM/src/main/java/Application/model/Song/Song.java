package Application.model.Song;
import java.io.Serializable;

public class Song implements Cloneable, Serializable{
    private String nome;
    private String interprete;
    private String editora;
    private String letra;
    private String pauta;
    private String genero;
    private int duracao;
    private int numRep;

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

    public Song(Song outraMusica) {
        this.nome = outraMusica.nome;
        this.interprete = outraMusica.interprete;
        this.editora = outraMusica.editora;
        this.letra = outraMusica.letra;
        this.pauta = outraMusica.pauta;
        this.genero = outraMusica.genero;
        this.duracao = outraMusica.duracao;
        this.numRep = outraMusica.numRep;
    }

    public String getNome() {
        return nome;
    }

    public String getInterprete() {
        return interprete;
    }

    public String getEditora() {
        return editora;
    }

    public String getLetra() {
        return letra;
    }

    public String getMusica() {
        return pauta;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getNumRep() {
        return numRep;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setMusica(String pauta) {
        this.pauta = pauta;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Song [nome=").append(this.nome)
          .append(", interprete=").append(this.interprete)
          .append(", editora=").append(this.editora)
          .append(", genero=").append(this.genero)
          .append(", duracao=").append(this.duracao)
          .append(" segundos, numRep=").append(this.numRep)
          .append("]");
        return sb.toString();
    }

    
    
    @Override
    public Song clone() {
        return new Song(this);
    }

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


    public void reproduzirMusica()
    {
        System.out.println(this.letra);
        this.numRep++;
    }

   

}
