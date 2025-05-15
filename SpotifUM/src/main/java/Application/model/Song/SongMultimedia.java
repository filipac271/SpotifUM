package Application.model.Song;

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
    public String getLetra(){
        return videoUrl + "\n" + "_".repeat(videoUrl.length()) + "\n" + super.getLetra();
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", videoUrl=").append(this.videoUrl);
        return sb.toString();
    }

    @Override
    public SongMultimedia clone() {
        return new SongMultimedia(this);
    }

    @Override
    public String getReproducao() {
        int numRep = getNumRep();
        setNumRep(numRep+1);
        return getLetra();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SongMultimedia)) return false;

        return true;
    }
}
