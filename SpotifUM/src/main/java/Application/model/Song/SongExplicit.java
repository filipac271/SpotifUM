package Application.model.Song;

public class SongExplicit extends Song {

    public SongExplicit() {
        super();
    }

    public SongExplicit(String nome, String interprete, String editora, String letra, String pauta, String genero,
            int duracao) {
        super(nome, interprete, editora, letra, pauta, genero, duracao);
    }

    public SongExplicit(SongExplicit outra) {
        super(outra);
    }

    @Override
    public String getLetra() {
        return "⚠️ Conteúdo explícito ⚠️\n" + super.getLetra();
    }

     
    // public void reproduzirMusica(int idade){
    //     if(idade > 18){
    //         System.out.println(getLetra());
    //     }else{
    //         System.out.println("Esta musica é explicita\n Usuários com menos de 18 anos não podem escutar esta música");
    //     }

    // }


    public String getReproducaoExplicita(int age) {
        
            if (age < 18) {
                return "Esta musica tem conteudo explicito daí não poder ser reproduzida";
            } else {
                int numRep = getNumRep();
                setNumRep(numRep);
                return getLetra();
            }


    }

}
