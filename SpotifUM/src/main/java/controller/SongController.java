package controller;

import java.util.HashMap;
import java.util.Map;

import Song.Song;

public class SongController {

    private Map<String, Song> songTable;

    public SongController() {
        this.songTable = new HashMap<>();
    }

    // Adiciona uma música à tabela
    public void addSong(String title, Song song) {
        songTable.put(title, song.clone());
    }

    // Remove uma música pelo título
    public boolean removeSong(String title) {
        if (songTable.containsKey(title)) {
            songTable.remove(title);
            return true;
        }
        return false;
    }

    // Obtém uma música pelo título
    public Song getSong(String title) {
        return songTable.get(title).clone();
    }


    // Verifica se uma música existe
    public boolean songExists(String title) {
        return songTable.containsKey(title);
    }
}
