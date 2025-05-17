/**
 * @file zeroSongsListen.java
 * @brief Exceção lançada quando não há músicas reproduzidas.
 */

package Application.exceptions;
/**
 * @class zeroSongsListen
 * @brief Exceção do tipo RuntimeException que indica que nenhuma música foi reproduzida.
 *
 * Esta exceção é utilizada quando uma operação requer músicas previamente reproduzidas,
 * mas nenhuma reprodução foi registada.
 */
public class zeroSongsListen extends RuntimeException {

    /**
     * Construtor da exceção zeroSongsListen.
     *
     * Inicializa a exceção com uma mensagem padrão a indicar que não existem músicas que já foramreproduzidas.
     */
    public zeroSongsListen() {
        super("Não existe músicas que já tenham sido reproduzidas");
    }
}
