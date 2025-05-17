package Application.exceptions;

/**
 * @brief Exceção lançada quando nenhum intérprete foi ouvido.
 * 
 * Esta exceção é utilizada para sinalizar que ainda não houve
 * qualquer intérprete escutado pelo utilizador.
 */
public class zeroInterpretesListen extends RuntimeException {

    /**
     * @brief Construtor da exceção zeroInterpretesListen.
     * 
     * Cria uma nova instância da exceção com uma mensagem predefinida.
     */
    public zeroInterpretesListen() {
        super("Ainda nenhum intérprete foi ouvido");
    }

}
