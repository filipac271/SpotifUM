package Application.model.Song;

public class SongExplicit extends Song implements Explicito{

    public SongExplicit() {
        super();
    }

    public SongExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero, int duracao){
        super(nome, interprete, editora, letra, pauta, genero, duracao);
    }

    public SongExplicit(SongExplicit outra) {
        super(outra);
    }

    @Override
    public String getLetra() {
        return "⚠️ Conteúdo explícito ⚠️\n" + super.getLetra();
    }

     


    public String getSongExplicit (int age){
        if (age < 18) {
            return "Esta musica tem conteudo explicito daí não poder ser reproduzida";
        } else {
            int numRep = getNumRep();
            setNumRep(numRep+1);
            return getLetra();
        }

    }

}
