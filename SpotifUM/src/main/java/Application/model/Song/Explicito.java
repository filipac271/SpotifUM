package Application.model.Song;

/**
 * @brief Interface para músicas com conteúdo explícito.
 * 
 * Define um método para verificar ou obter informações sobre o conteúdo explícito
 * de uma música, com base na idade do ouvinte.
 */
public interface Explicito {

    /**
     * @brief Verifica o conteúdo explícito da música com base na idade.
     * 
     * Retorna uma descrição ou informação sobre a adequação da música para a idade fornecida.
     *
     * @param age Idade do ouvinte.
     * @return Uma string indicando se a música contém conteúdo explícito adequado ou não.
     */
    public String getSongExplicit(int age);

}

