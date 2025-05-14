package ApplicationTests.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Application.model.Playlist.Playlist;
import Application.model.Playlist.PlaylistUser;

import Application.model.Song.Song;


public class PlaylistTest {


      @Test
    public void playlistTest() {
        // Criar músicas de exemplo
        List<Song> musicas = new ArrayList<>();
        Song musica1 = new Song("Musica 1", "Interprete 1", "Editora 1", "Letra 1", "Pauta 1", "Genero 1", 180);
        Song musica2 = new Song("Musica 2", "Interprete 2", "Editora 2", "Letra 2", "Pauta 2", "Genero 2", 200);
        musicas.add(musica1);
        musicas.add(musica2);

        // Criar playlist com músicas
        Playlist playlist = new PlaylistUser("Minha Playlist", musicas, true);

        // Verificar o nome da playlist
        assertEquals("Minha Playlist", playlist.getNome());

        // Verificar se a playlist é pública
        assertTrue(playlist.getPublica() == true);

        // Verificar o número de músicas na playlist
        assertEquals(2, playlist.getMusicas().size());

        // Verificar se as músicas são as mesmas
        assertEquals(musica1, playlist.getMusicas().get(0));
        assertEquals(musica2, playlist.getMusicas().get(1));
    }
    
}
