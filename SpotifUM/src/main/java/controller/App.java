
package controller;

import Entidades.Song;
import io.Input;

public class App {

    public static void main(String[] args) {
        // Criando uma instância de Song
        Song song = new Song("Música Exemplo", "Artista Exemplo", "Editora Exemplo", 
                             "Letra da música aqui...", "Pauta da música", 
                             "Pop", 210);

        // Criando uma instância de IO
        Input io = new Input();
        
        // Reproduzindo a música (imprimindo a letra)
        io.playSong(song);
    }
}