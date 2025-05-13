package Application.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import Application.model.Song.Song;

public class Historico implements Serializable {
    private Song musica;
    private LocalDate data;

    public Historico()
    {
        this.musica=null;
        this.data=null;
    }
    public Historico(Song musica, LocalDate data)
    {
        this.musica=musica;
        this.data=data;
    }

    public Historico(Historico outro)
    {
        this.musica=outro.getMusica();
        this.data=outro.getData();
    }

    public Song getMusica() {
        return this.musica;
    }

    public LocalDate getData() {
        return this.data;
    }

    @Override
    public Historico clone()
    {
        return new Historico(this);
    }
}
