package controller;

import java.util.HashMap;
import java.util.Map;

import Playlist.Playlist;

public class PlaylistController {
    
    private Map<String, Playlist> playlistTable;

    public PlaylistController() {
        this.playlistTable = new HashMap<>();
    }

    // Adiciona uma playlist
    public void addPlaylist(String name, Playlist playlist) {
        playlistTable.put(name, playlist.clone());
    }

    // Remove uma playlist
    public boolean removePlaylist(String name) {
        if (playlistTable.containsKey(name)) {
            playlistTable.remove(name);
            return true;
        }
        return false;
    }

    // Obtém uma playlist pelo nome
    public Playlist getPlaylist(String name) {
        return playlistTable.get(name).clone();
    }

    // Verifica se uma playlist existe
    public boolean playlistExists(String name) {
        return playlistTable.containsKey(name);
    }
}
