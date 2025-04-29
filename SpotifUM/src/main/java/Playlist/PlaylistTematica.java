package Playlist;

import java.util.List;

import Song.Song;


public class PlaylistTematica extends Playlist {
    private final String genero;
    private final int duracaoMaxima;

    public PlaylistTematica(String nome, List<Song> musicas, String genero, int duracaoMaxima) {
        //super(nome, List.of());
        super(); // inicia vazia
        this.genero = genero;
        this.duracaoMaxima = duracaoMaxima;

        // adicionar apenas as que respeitam as restrições
        for (Song s : musicas) {
            this.adicionarMusica(s);
        }
    }

    public PlaylistTematica(String nome, String genero, int duracaoMaxima) {
        //super(nome, List.of());
        super();
        this.genero = genero;
        this.duracaoMaxima = duracaoMaxima;
    }

    public PlaylistTematica() {
        super();
        this.genero = "";
        this.duracaoMaxima = 0;
    }

    public PlaylistTematica(PlaylistTematica outra) {
        super(outra);
        this.genero = outra.genero;
        this.duracaoMaxima = outra.duracaoMaxima;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracaoMaxima() {
        return duracaoMaxima;
    }

    public int getDuracaoAtual() {
        return super.getDuracaoTotal();
    }

    @Override
    public void adicionarMusica(Song musica) {
        if (!musica.getGenero().equalsIgnoreCase(genero)) return;
        if (getDuracaoAtual() + musica.getDuracao() > duracaoMaxima) return;
        super.adicionarMusica(musica);
    }

    @Override
    public void reproduzir() {
        for (Song s : super.getMusicas()) {
            System.out.println(s.getLetra());
            System.out.println("----");
        }
    }

    @Override
    public PlaylistTematica clone() {
        return new PlaylistTematica(this);
    }

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
