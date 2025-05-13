package Application.model.Song;

public class SongMediaExplicit extends SongMultimedia implements Explicito{

    public SongMediaExplicit() {
        super();
    }

    public SongMediaExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao, String videoUrl) {
        super(nome, interprete, editora, letra, pauta, genero, duracao,videoUrl);
    }

    public SongMediaExplicit(SongMediaExplicit outra) {
        super(outra);  
    }





    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SongMediaExplicit)) return false;
        if (!super.equals(obj)) return false;

        return true;
    }

    
    public String getSongExplicit (int age){
        if (age < 18) {
            return "Esta musica tem conteudo explicito daí não poder ser reproduzida";
        } else {
            int numRep = getNumRep();
            setNumRep(numRep+1);
            return  "⚠️ Conteúdo explícito ⚠️\n" + getLetra();
        }

    }


}
