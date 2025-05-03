package Application.model.Song;

public class SongMediaExplicit extends SongExplicit{
    private String videoUrl;

    public SongMediaExplicit() {
        super();
        this.videoUrl = "";
    }

    public SongMediaExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao, String videoUrl) {
        super(nome, interprete, editora, letra, pauta, genero, duracao);
        this.videoUrl = videoUrl;
    }

    public SongMediaExplicit(SongMediaExplicit outra) {
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
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", videoUrl=").append(this.videoUrl);
        return sb.toString();
    }

    @Override
    public SongMediaExplicit clone() {
        return new SongMediaExplicit(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SongMediaExplicit)) return false;

        SongMediaExplicit that = (SongMediaExplicit) obj;
        return this.videoUrl.equals(that.videoUrl);
    }

    



}
