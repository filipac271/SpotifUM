package Songs;

public class SongMultimedia extends Song {
    private String videoUrl;

    public SongMultimedia() {
        super();
        this.videoUrl = "";
    }

    public SongMultimedia(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao, String videoUrl) {
        super(nome, interprete, editora, letra, pauta, genero, duracao);
        this.videoUrl = videoUrl;
    }

    public SongMultimedia(SongMultimedia outra) {
        super(outra);
        this.videoUrl = outra.videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return super.toString() + ", videoUrl=" + this.videoUrl;
    }

    @Override
    public SongMultimedia clone() {
        return new SongMultimedia(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SongMultimedia)) return false;

        SongMultimedia that = (SongMultimedia) obj;
        return this.videoUrl.equals(that.videoUrl);
    }
}
