/**
 * @file zeroGenresListen.java
 * @brief Exceção lançada quando não há géneros escutados pelo utilizador.
 */

package Application.exceptions;

/**
 * @class zeroGenresListen
 * @brief Exceção do tipo RuntimeException que indica que nenhuma música foi reproduzida.
 *
 * Esta exceção é usada quando uma operação requer que o utilizador tenha escutado
 * pelo menos uma música, mas nenhuma reprodução foi registada até ao momento.
 */
public class zeroGenresListen extends RuntimeException {

    /**
     * Construtor da exceção zeroGenresListen.
     *
     * Inicializa a exceção com uma mensagem padrão a indicar que nenhuma música foi reproduzida.
     */
    public zeroGenresListen() {
        super("Ainda nenhuma música foi reproduzida");
    }
}
