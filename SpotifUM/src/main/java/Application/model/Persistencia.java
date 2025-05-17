package Application.model;

/**
 * @file Persistencia.java
 * @brief Classe utilitária para guardar e carregar dados persistentes da aplicação.
 *
 * Esta classe fornece métodos estáticos para guardar e carregar objetos serializados,
 * como utilizadores, playlists, músicas e álbuns. Os dados são armazenados em ficheiros binários.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Application.model.User.User;
import Application.model.Song.Song;
import Application.model.Album.Album;
import Application.model.Playlist.Playlist;

/**
 * @class Persistencia
 * @brief Classe responsável pela persistência de dados (leitura e escrita em ficheiros binários).
 */

public class Persistencia {

    /**
     * Carrega os utilizadores a partir do ficheiro "users.bin".
     *
     * @return Mapa de utilizadores carregados do ficheiro. Se o ficheiro não existir, retorna um mapa vazio.
     * @throws IOException Se ocorrer um erro de leitura do ficheiro.
     * @throws ClassNotFoundException Se a classe dos objetos serializados não for encontrada.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File("users.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"))) {
            return (Map<String, User>) ois.readObject();
        }
    }

    /**
     * Carrega as playlists a partir do ficheiro "playlists.bin".
     *
     * @return Mapa de playlists carregadas do ficheiro. Se o ficheiro não existir, retorna um mapa vazio.
     * @throws IOException Se ocorrer um erro de leitura do ficheiro.
     * @throws ClassNotFoundException Se a classe dos objetos serializados não for encontrada.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Playlist> loadPlaylists() throws IOException, ClassNotFoundException {
        File f = new File("playlists.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("playlists.bin"))) {
            return (Map<String, Playlist>) ois.readObject();
        }
    }

    /**
     * Carrega as músicas a partir do ficheiro "songs.bin".
     *
     * @return Mapa de músicas carregadas do ficheiro. Se o ficheiro não existir, retorna um mapa vazio.
     * @throws IOException Se ocorrer um erro de leitura do ficheiro.
     * @throws ClassNotFoundException Se a classe dos objetos serializados não for encontrada.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Song> loadSongs() throws IOException, ClassNotFoundException {
        File f = new File("songs.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("songs.bin"))) {
            return (Map<String, Song>) ois.readObject();
        }
    }

    /**
     * Carrega os álbuns a partir do ficheiro "albuns.bin".
     *
     * @return Mapa de álbuns carregados do ficheiro. Se o ficheiro não existir, retorna um mapa vazio.
     * @throws IOException Se ocorrer um erro de leitura do ficheiro.
     * @throws ClassNotFoundException Se a classe dos objetos serializados não for encontrada.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Album> loadAlbuns() throws IOException, ClassNotFoundException {
        File f = new File("albuns.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("albuns.bin"))) {
            return (Map<String, Album>) ois.readObject();
        }
    }

    /**
     * Guarda as playlists no ficheiro "playlists.bin".
     *
     * @param playlists Mapa de playlists a guardar.
     * @throws IOException Se ocorrer um erro de escrita no ficheiro.
     */
    public static void savePlaylists(Map<String, Playlist> playlists) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("playlists.bin"))) {
            oos.writeObject(playlists);
        }
    }

    /**
     * Guarda os utilizadores no ficheiro "users.bin".
     *
     * @param users Mapa de utilizadores a guardar.
     * @throws IOException Se ocorrer um erro de escrita no ficheiro.
     */
    public static void saveUsers(Map<String, User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"))) {
            oos.writeObject(users);
        }
    }

    /**
     * Guarda as músicas no ficheiro "songs.bin".
     *
     * @param songs Mapa de músicas a guardar.
     * @throws IOException Se ocorrer um erro de escrita no ficheiro.
     */
    public static void saveSongs(Map<String, Song> songs) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("songs.bin"))) {
            oos.writeObject(songs);
        }
    }

    /**
     * Guarda os álbuns no ficheiro "albuns.bin".
     *
     * @param albums Mapa de álbuns a guardar.
     * @throws IOException Se ocorrer um erro de escrita no ficheiro.
     */
    public static void saveAlbum(Map<String, Album> Albums) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("albuns.bin"))) {
            oos.writeObject(Albums);
        }
    }

}
