package Application.model.Album;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Application.model.Song.Song;

/**
 * Classe que representa um álbum de musicas, contém um nome, o artista, e uma lista de musicas.
 */
public class Album implements Serializable {
    private String nome;
    private String artista;
    private List<Song> album;

    /**
     * Construtor por omissão. Cria um álbum vazio com nome e artista vazios.
     */
    public Album() {
        this.nome = "";
        this.artista = "";
        this.album = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     * 
     * @param nome O nome do álbum.
     * @param artista O nome do artista.
     * @param albumList Lista de músicas a incluir no álbum (vão ser clonadas).
     */
    public Album(String nome ,String artista, List<Song> albumList){
        this.nome = nome;
        this.artista = artista;
        this.album = new ArrayList<>(albumList);
    }

    /**
     * Construtor de cópia.
     * 
     * @param outroAlbum Álbum a ser copiado.
     */
    public Album(Album outroAlbum){
        this.nome = outroAlbum.getNome();
        this.artista = outroAlbum.getArtista();
        this.album = outroAlbum.getAlbum();
    }

    /**
     * Obtém o nome do álbum.
     * 
     * @return O nome do álbum.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o nome do artista.
     * 
     * @return O nome do artista.
     */
    public String getArtista(){
        return artista;
    }

    /**
     * Obtém a lista de músicas do álbum.
     * 
     * @return Uma cópia da lista de músicas.
     */
    public List<Song> getAlbum() {
        // return this.album.stream().map(song -> song.clone()).collect(Collectors.toList());
        return new ArrayList<>(this.album);

    }

    /**
     * Define o nome do álbum.
     * 
     * @param nome O novo nome do álbum.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o nome do artista.
     * 
     * @param artista O novo nome do artista.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Define a lista de músicas do álbum.
     * 
     * @param albumList Lista de músicas (serão clonadas).
     */
    public void setAlbum(List<Song> albumList){
        this.album = new ArrayList<>(albumList);
    }

    /**
     * Adiciona uma música ao álbum.
     * 
     * @param song Música a adicionar (vai ser clonada).
     */
    public void addSong(Song song) {
        album.add(song);
    }

    /**
     * Remove uma música do álbum. 
     * 
     * @param song Música a remover.
     * @return true se a música foi removida com sucesso, false caso contrário.
     */
    public boolean removeSong(Song song) {
        return album.remove(song);
    }

    /**
     * @brief Obtém o número de músicas no álbum.
     * 
     * @return O número total de músicas presentes na lista `album`.
     */
    public int tamanho() {
        return album.size();
    }
    
    /**
     * @brief Obtém a música na posição especificada.
     * 
     * Retorna a música localizada no índice `n` da lista `album`.
     *
     * @param n Índice da música a obter (começa em 0).
     * @return A música na posição `n`.
     * @throws IndexOutOfBoundsException Se o índice estiver fora dos limites da lista.
     */
    public Song getNMusica(int n) {
        return album.get(n);
    }


    public boolean contemMusica(String musica)
    {
        return album.stream()
        .anyMatch(s -> s.getNome().equalsIgnoreCase(musica));
    }


    /**
     * Representação textual do álbum.
     * 
     * @return Uma string com o nome, artista e lista de músicas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Álbum: ").append(nome).append("\n");
        sb.append("Artista: ").append(artista).append("\n");
        sb.append("Músicas:\n");
        if(album.size() == 0){
            return"No musics in this album";
        }else{
            for (int i = 0; i < album.size(); i++) {
                sb.append("  ").append(i + 1).append(". ")
                    .append(album.get(i).toString()).append("\n");
            }

        }

        return sb.toString();
    }


    /**
     * Cria e devolve uma cópia (clone) deste objeto Album.
     *
     * @return Uma nova instância de Album com os mesmos dados deste objeto.
     */
    @Override
    public Album clone() {
        return new Album(this);
    }





}
