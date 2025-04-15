package io;
import Entidades.Song;

public class Input {

    // Método para reproduzir a música (imprimir a letra no terminal)
    public void playSong(Song song) {
        System.out.println("Reproduzindo a música: " + song.getNome());
        System.out.println("Intérprete: " + song.getInterprete());
        System.out.println("Editora: " + song.getEditora());
        System.out.println("Gênero: " + song.getGenero());
        System.out.println("Duração: " + song.getDuracao() + " segundos");
        System.out.println("\nLetra da música:");
        System.out.println(song.getLetra()); 
    }

  
}