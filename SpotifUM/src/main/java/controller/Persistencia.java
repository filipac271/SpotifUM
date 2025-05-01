package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Playlist.Playlist;
import Song.Song;
import User.User;
import Album.Album;

public class Persistencia {




    @SuppressWarnings("unchecked")
    public static Map<String, User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File("users.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"))) {
            return (Map<String, User>) ois.readObject();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Playlist> loadPlaylists() throws IOException, ClassNotFoundException {
        File f = new File("playlists.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("playlists.bin"))) {
            return (Map<String, Playlist>) ois.readObject();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Song> loadSongs() throws IOException, ClassNotFoundException {
        File f = new File("songs.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("songs.bin"))) {
            return (Map<String, Song>) ois.readObject();
        }
    }


    @SuppressWarnings("unchecked")
    public static Map<String, Album> loadAlbuns() throws IOException, ClassNotFoundException {
        File f = new File("albuns.bin");
        if (!f.exists())
            return new HashMap<>(); // ficheiro ainda não existe

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("albuns.bin"))) {
            return (Map<String, Album>) ois.readObject();
        }
    }



    


    public static void savePlaylists(Map<String, Playlist> playlists) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("playlists.bin"))) {
            oos.writeObject(playlists);
        }
    }

    public static void saveUsers(Map<String, User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"))) {
            oos.writeObject(users);
        }
    }

    public static void saveSongs(Map<String, Song> songs) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("songs.bin"))) {
            oos.writeObject(songs);
        }
    }

    public static void saveAlbum(Map<String, Album> Albums) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("albuns.bin"))) {
            oos.writeObject(Albums);
        }
    }

}
