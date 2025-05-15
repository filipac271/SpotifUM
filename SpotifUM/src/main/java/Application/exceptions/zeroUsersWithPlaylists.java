package Application.exceptions;

public class zeroUsersWithPlaylists extends RuntimeException {
    public zeroUsersWithPlaylists() {
        super("Não existe nenhum user com alguma Playlist");
    }

}