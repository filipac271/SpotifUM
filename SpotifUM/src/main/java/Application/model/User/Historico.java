package Application.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import Application.model.Song.Song;

/**
 * Representa uma entrada no histórico de reproduções de um utilizador,
 * contendo uma música e a data em que foi reproduzida.
 */
public class Historico implements Serializable {

    /** Música reproduzida. */
    private Song musica;

    /** Data da reprodução. */
    private LocalDate data;

    /**
     * Construtor vazio que inicializa os campos a {@code null}.
     */
    public Historico() {
        this.musica = null;
        this.data = null;
    }

    /**
     * Construtor parametrizado.
     *
     * @param musica Música reproduzida.
     * @param data Data da reprodução.
     */
    public Historico(Song musica, LocalDate data) {
        this.musica = musica;
        this.data = data;
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Instância de {@code Historico} a copiar.
     */
    public Historico(Historico outro) {
        this.musica = outro.getMusica();
        this.data = outro.getData();
    }

    /**
     * Devolve a música associada ao histórico.
     *
     * @return A música reproduzida.
     */
    public Song getMusica() {
        return this.musica;
    }

    /**
     * Devolve a data da reprodução da música.
     *
     * @return Data da reprodução.
     */
    public LocalDate getData() {
        return this.data;
    }

    /**
     * Cria e devolve uma cópia desta instância de histórico.
     *
     * @return Clonagem do histórico.
     */
    @Override
    public Historico clone() {
        return new Historico(this);
    }
}
