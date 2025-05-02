
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Album.Album;
import Playlist.Playlist;
import Song.Song;
import User.User;
import view.Input;

public class App {

    public void mainLoop() {
        Scanner sc = new Scanner(System.in);
        Input io = new Input();
        Map<String, User> users = new HashMap<>();
        Map<String, Playlist> playlists = new HashMap<>();
        Map<String, Song> songs = new HashMap<>();
        Map<String, Album> albuns = new HashMap<>();

        // Usando try-catch para carregar os dados e substituir os valores vazios
        try {
            users = Persistencia.loadUsers();
            playlists = Persistencia.loadPlaylists();
            songs = Persistencia.loadSongs();
            albuns = Persistencia.loadAlbuns();
            System.out.println(albuns);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime o erro no console
            return; // Sai do método caso ocorra uma falha no carregamento dos dados
        }

        Controller controller = new Controller(playlists, songs, users,albuns);

        while (true) {

            int option;
            io.mainMenu();
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                io.optionError();
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1:

                    io.logInUserMenu(sc, controller);
                    break;
                case 2:
                    io.createUserMenu(sc, controller);
                    break;
                case 3:
                    io.createAlbumMenu(sc, controller);
                    break;
                case 4:
                    io.out();
                    break;
                default:
                    io.optionError();
                    break;
            }

            if (option == 4) {
                controller.saveAll();
                break;
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        App app = new App();

        app.mainLoop();

    }
}