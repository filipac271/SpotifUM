package Application.model.Song;

/**
 * Representa uma música multimédia com conteúdo explícito.
 * Estende {@link SongMultimedia} e implementa a interface {@link Explicito}.
 */
public class SongMediaExplicit extends SongMultimedia implements Explicito {

    /**
     * Construtor vazio que inicializa a música multimédia explícita com valores padrão.
     */
    public SongMediaExplicit() {
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
     * @param videoUrl URL do vídeo associado à música.
     */
    public SongMediaExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao, String videoUrl) {
        super(nome, interprete, editora, letra, pauta, genero, duracao, videoUrl);
    }

    /**
     * Construtor de cópia.
     *
     * @param outra Instância de {@code SongMediaExplicit} a copiar.
     */
    public SongMediaExplicit(SongMediaExplicit outra) {
        super(outra);  
    }

    /**
     * Verifica se o objeto é igual a esta instância.
     * Dois objetos são iguais se forem do tipo {@code SongMediaExplicit} e forem iguais segundo a superclasse.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} se forem iguais, {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SongMediaExplicit)) return false;
        if (!super.equals(obj)) return false;

        return true;
    }

    /**
     * Devolve a letra da música explícita se a idade for maior ou igual a 18.
     * Caso contrário, devolve uma mensagem de restrição de reprodução.
     * Incrementa o contador de reproduções se a reprodução for permitida.
     * A letra é precedida de um aviso de conteúdo explícito.
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
            return "⚠️ Conteúdo explícito ⚠️\n" + getLetra();
        }
    }
}
