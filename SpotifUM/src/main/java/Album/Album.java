package Album;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import PlanoSubscricao.PlanoSubscricao;
import Song.Song;

public class Album {
    private String nome;
    private String artista;
    private List<Song> album;


    public Album() {
        this.nome = "";
        this.artista = "";
        this.album = new ArrayList<>();
    }


    public Album(String nome ,String artista, List<Song> albumList){
        this.nome = nome;
        this.artista = artista;
        this.album = albumList.stream()
            .map(song -> song.clone())  
            .collect(Collectors.toList());
    }


    public Album(Album outroAlbum){
        this.nome = outroAlbum.getNome();
        this.artista = outroAlbum.getArtista();
        this.album = outroAlbum.getAlbum();
    }

    public String getNome() {
        return nome;
    }

    public String getArtista(){
        return artista;
    }
    
    public List<Song> getAlbum() {
        return this.album.stream().map(song -> song.clone()).collect(Collectors.toList());
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setAlbum(List<Song> albumList){

        this.album = albumList.stream()
            .map(song -> song.clone())  
            .collect(Collectors.toList());

    }

    public void addSong(Song song) {
        album.add(song.clone());
    }

    public boolean removeSong(Song song) {
        return album.remove(song);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Álbum: ").append(nome).append("\n");
        sb.append("Artista: ").append(artista).append("\n");
        sb.append("Músicas:\n");
    
        for (int i = 0; i < album.size(); i++) {
            sb.append("  ").append(i + 1).append(". ")
              .append(album.get(i).toString()).append("\n");
        }
    
        return sb.toString();
    }
    

}
