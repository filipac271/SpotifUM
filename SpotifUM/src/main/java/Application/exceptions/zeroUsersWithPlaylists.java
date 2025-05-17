/**
 * @file zeroUsersWithPlaylists.java
 * @brief Exceção lançada quando não há utilizadores com playlists.
 */

package Application.exceptions;
/**
 * @class zeroUsersWithPlaylists
 * @brief Exceção do tipo RuntimeException que indica que nenhum utilizador possui playlists.
 *
 * Esta exceção é usada quando uma operação requer pelo menos um utilizador com playlists
 * associadas, mas nenhum foi encontrado.
 */
public class zeroUsersWithPlaylists extends RuntimeException {

    /**
     * Construtor da exceção zeroUsersWithPlaylists.
     *
     * Inicializa a exceção com uma mensagem padrão a indicar que nenhum utilizador tem playlists.
     */
    public zeroUsersWithPlaylists() {
        super("Não existe nenhum user com alguma Playlist");
    }
}
