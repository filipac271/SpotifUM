package ApplicationTests.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Application.model.Song.Song;

public class SongTest {


        @Test
    public void songTest() {
        // Criar música de exemplo
        Song musica = new Song("Musica 1", "Interprete 1", "Editora 1", "Letra 1", "Pauta 1", "Genero 1", 180);

        // Verificar o nome da música
        assertEquals("Musica 1", musica.getNome());

        // Verificar o intérprete
        assertEquals("Interprete 1", musica.getInterprete());

        // Verificar a editora
        assertEquals("Editora 1", musica.getEditora());

        // Verificar a letra
        assertEquals("Letra 1", musica.getLetra());

        // Verificar a pauta
        assertEquals("Pauta 1", musica.getPauta());

        // Verificar o gênero
        assertEquals("Genero 1", musica.getGenero());

        // Verificar a duração
        assertEquals(180, musica.getDuracao());

        // Verificar o número de repetições
        assertEquals(0, musica.getNumRep());
    }
    
}
