package ApplicationTests.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Application.model.Album.Album;
import Application.model.Song.Song;

public class AlbumTest {
    @Test
    public void albumTest() {
        // Criar músicas de exemplo
        List<Song> musicas = new ArrayList<>();
        Song musica1 = new Song("Musica 1", "Interprete 1", "Editora 1", "Letra 1", "Pauta 1", "Genero 1", 180);
        Song musica2 = new Song("Musica 2", "Interprete 2", "Editora 2", "Letra 2", "Pauta 2", "Genero 2", 200);
        musicas.add(musica1);
        musicas.add(musica2);

        // Criar álbum com músicas
        Album album = new Album("Meu Álbum", "Artista 1", musicas);

        // Verificar o nome do álbum
        assertEquals("Meu Álbum", album.getNome());

        // Verificar o nome do artista
        assertEquals("Artista 1", album.getArtista());

        // Verificar o número de músicas no álbum
        assertEquals(2, album.getAlbum().size());

        // Verificar se as músicas são as mesmas
        assertEquals(musica1, album.getAlbum().get(0));
        assertEquals(musica2, album.getAlbum().get(1));
    }
}
