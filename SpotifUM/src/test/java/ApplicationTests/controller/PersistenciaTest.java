package ApplicationTests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Application.controller.Persistencia;
import Application.model.Album.Album;
import Application.model.PlanoSubscricao.PlanoFree;
import Application.model.Playlist.Playlist;
import Application.model.Playlist.PlaylistUser;
import Application.model.Song.Song;
import Application.model.User.User;

public class PersistenciaTest {
    private static final String USERS_FILE = "users.bin";
    private static final String PLAYLISTS_FILE = "playlists.bin";
    private static final String SONGS_FILE = "songs.bin";
    private static final String ALBUMS_FILE = "albuns.bin";

    @BeforeEach
    public void setUp() {
        // Apagar arquivos de teste antes de cada execução de teste
        new File(USERS_FILE).delete();
        new File(PLAYLISTS_FILE).delete();
        new File(SONGS_FILE).delete();
        new File(ALBUMS_FILE).delete();
    }

    @Test
    public void testLoadUsersWhenFileExists() throws IOException, ClassNotFoundException {
        // Preparar dados de teste
        Map<String, User> users = Map.of("user1", new User("user1", "JotaJota", "password123", "joao@gmail.com", "rua do Faial", 19, new PlanoFree()));
        Persistencia.saveUsers(users);

        // Carregar os dados do arquivo
        Map<String, User> loadedUsers = Persistencia.loadUsers();

        // Verificar se os dados carregados são corretos
        assertEquals(1, loadedUsers.size());
        assertTrue(loadedUsers.containsKey("user1"));
        assertEquals("user1", loadedUsers.get("user1").getNome());
    }

    @Test
    public void testLoadUsersWhenFileDoesNotExist() throws IOException, ClassNotFoundException {
        // Apagar o arquivo de dados (simular arquivo não encontrado)
        new File(USERS_FILE).delete();

        // Carregar os dados do arquivo
        Map<String, User> loadedUsers = Persistencia.loadUsers();

        // Verificar se o mapa carregado está vazio
        assertTrue(loadedUsers.isEmpty());
    }

    @Test
    public void testSavePlaylists() throws IOException, ClassNotFoundException {
        // Preparar dados de teste
        Map<String, Playlist> playlists = Map.of("playlist1", new PlaylistUser("My Playlist",new ArrayList<>(),false));

        // Salvar playlists no arquivo
        Persistencia.savePlaylists(playlists);

        // Carregar playlists do arquivo
        Map<String, Playlist> loadedPlaylists = Persistencia.loadPlaylists();

        // Verificar se os dados carregados são corretos
        assertEquals(1, loadedPlaylists.size());
        assertTrue(loadedPlaylists.containsKey("playlist1"));
        assertEquals("My Playlist", loadedPlaylists.get("playlist1").getNome());
    }

    @Test
    public void testSaveSongs() throws IOException, ClassNotFoundException {
        // Preparar dados de teste
        Map<String, Song> songs = Map.of("song1", new Song("Song One", "Artist One", "Label One", "Lyrics One", "Sheet One", "Genre One", 180));

        // Salvar músicas no arquivo
        Persistencia.saveSongs(songs);

        // Carregar músicas do arquivo
        Map<String, Song> loadedSongs = Persistencia.loadSongs();

        // Verificar se os dados carregados são corretos
        assertEquals(1, loadedSongs.size());
        assertTrue(loadedSongs.containsKey("song1"));
        assertEquals("Song One", loadedSongs.get("song1").getNome());
    }

    @Test
    public void testSaveAlbuns() throws IOException, ClassNotFoundException {
        // Preparar dados de teste
        Map<String, Album> albums = Map.of("album1", new Album("album1", "Artista 1", new ArrayList<>()));

        // Salvar álbuns no arquivo
        Persistencia.saveAlbum(albums);

        // Carregar álbuns do arquivo
        Map<String, Album> loadedAlbums = Persistencia.loadAlbuns();

        // Verificar se os dados carregados são corretos
        assertEquals(1, loadedAlbums.size());
        assertTrue(loadedAlbums.containsKey("album1"));
        assertEquals("album1", loadedAlbums.get("album1").getNome());
    }
}
