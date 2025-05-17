package Application.model.Song;

/**
 * Representa uma música com conteúdo explícito.
 * Extende a classe {@link Song} e implementa a interface {@link Explicito}.
 */
public class SongExplicit extends Song implements Explicito {

    /**
     * Construtor vazio que inicializa a música explícita com valores padrão.
     */
    public SongExplicit() {
        super();
    }

    /**
     * Construtor parametrizado.
     *
     * @param nome Nome da música.
     * @param interprete Intérprete da música.
     * @param editora Editora da música.
     * @param letra Letra da música.
     * @param pauta Pauta (partitura) da música.
     * @param genero Género musical.
     * @param duracao Duração da música em segundos.
     */
    public SongExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao) {
        super(nome, interprete, editora, letra, pauta, genero, duracao);
    }

    /**
     * Construtor de cópia.
     *
     * @param outra Instância de {@code SongExplicit} a copiar.
     */
    public SongExplicit(SongExplicit outra) {
        super(outra);
    }

    /**
     * Retorna a letra da música precedida de um aviso de conteúdo explícito.
     *
     * @return Letra da música com aviso de conteúdo explícito.
     */
    @Override
    public String getLetra() {
        return "\n⚠️ Conteúdo explícito ⚠️\n" + super.getLetra();
    }

    /**
     * Devolve a letra da música explícita se a idade for maior ou igual a 18.
     * Caso contrário, devolve uma mensagem de restrição de reprodução.
     * Incrementa o contador de reproduções se for permitido ouvir a música.
     *
     * @param age Idade do utilizador que tenta reproduzir a música.
     * @return Letra da música se idade >= 18, ou mensagem de restrição caso contrário.
     */
    public String getSongExplicit(int age) {
        if (age < 18) {
            return "Esta musica tem conteudo explicito daí não poder ser reproduzida";
        } else {
            int numRep = getNumRep();
            setNumRep(numRep + 1);
            return "\n/////////////////Música/////////////////" + getLetra() + "\n////////////////////////////////////////";
        }
    }
}
