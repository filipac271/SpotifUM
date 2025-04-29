package controller;

import java.util.HashMap;
import java.util.Iterator;
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

    // Devolve o nome da música mais ouvida
    public String musicaMaisOuvida()
    {
        String maisReproduzida = null;
        int maiorNumRep = -1;

        for (Map.Entry<String, Song> entry : songTable.entrySet()) {
            Song song = entry.getValue();
            if (song.getNumRep() > maiorNumRep) {
                maiorNumRep = song.getNumRep();
                maisReproduzida = song.getNome();
            }
        }

        return maisReproduzida;
    }

    //Devolve o nome do interprete mais escutado
    public String interpreteMaisOuvido()
    {
           
        Map<String, Integer> interpretes= new HashMap<>();

        for (Song song : songTable.values()) {
            String interprete = song.getInterprete(); 
            int numRep = song.getNumRep();
            
            interpretes.put(interprete, interpretes.getOrDefault(interprete, 0) + numRep);
        }

        String interpreteMaisOuvido = null;
        int maiorReps = -1;

        for (Map.Entry<String, Integer> entry : interpretes.entrySet()) {
            if (entry.getValue() > maiorReps) {
                maiorReps = entry.getValue();
                interpreteMaisOuvido = entry.getKey();
            }
        }

       return interpreteMaisOuvido;
    }

    public String generoMaisOuvido()
    {
           
        Map<String, Integer> generos= new HashMap<>();

        for (Song song : songTable.values()) {
            String genero = song.getInterprete(); 
            int numRep = song.getNumRep();
            
            generos.put(genero, generos.getOrDefault(genero, 0) + numRep);
        }

        String generoMaisOuvido = null;
        int maiorReps = -1;

        for (Map.Entry<String, Integer> entry : generos.entrySet()) {
            if (entry.getValue() > maiorReps) {
                maiorReps = entry.getValue();
                generoMaisOuvido = entry.getKey();
            }
        }

       return generoMaisOuvido;
    }

}
