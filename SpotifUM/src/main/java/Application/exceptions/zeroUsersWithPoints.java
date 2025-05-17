/**
 * @file zeroUsersWithPoints.java
 * @brief Exceção lançada quando não há utilizadores com pontos associados.
 */

package Application.exceptions;
/**
 * @class zeroUsersWithPoints
 * @brief Exceção do tipo RuntimeException que indica que nenhum utilizador tem pontos associados.
 *
 * Esta exceção é usada quando uma operação exige pelo menos um utilizador com pontos registados,
 * mas nenhum foi encontrado.
 */
public class zeroUsersWithPoints extends RuntimeException {

    /**
     * Construtor da exceção zeroUsersWithPoints.
     *
     * Inicializa a exceção com uma mensagem padrão a indicar que não existem utilizadores com pontos.
     */
    public zeroUsersWithPoints() {
        super("Não existe Users com pontos associados");
    }
}
