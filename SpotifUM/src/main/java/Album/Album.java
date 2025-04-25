package Album;
import java.util.Iterator;
import  java.util.List;

import PlanoSubscricao.PlanoSubscricao;
import Song.Song;

public class Album {
    private String nome;
    private List<Song> album;

      public void reproduzir(PlanoSubscricao plano)
    {
        
            Iterator<Song> musicas =this.album.iterator();
            while(musicas.hasNext())
            {
                 Song s=musicas.next();
                 s.reproduzirMusica();

                 //?????????? Como sabemos se o user quer avançar ou retroceder
               
            }
    } 
}
