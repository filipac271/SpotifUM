package Application.model.Playlist;

import java.util.List;

import Application.model.Song.Song;

public class PlaylistUser extends Playlist {

   /**
    * Construtor com parâmetros.
    * 
    * @param nome    Nome da playlist.
    * @param musicas Lista de músicas.
    * @param publica Indica se a playlist é pública.
    */
   public PlaylistUser(String nome, List<Song> musicas, boolean publica) {
      super(nome, musicas, publica);
   }

   /**
    * Construtor por omissão.
    * Cria uma playlist vazia, privada e sem nome.
    */
   public PlaylistUser() {
      super();
   }

   /**
    * Construtor de cópia.
    * 
    * @param outra Playlist a copiar.
    */
   public PlaylistUser(Playlist outra) {
      super(outra);
   }

   /**
    * Reproduz todas as músicas da playlist.
    * Neste caso, imprime as letras das músicas no terminal.
    */

   /**
    * Cria uma cópia/clone desta playlist.
    * 
    * @return Uma nova instância de {@code PlaylistRandom} com os mesmos dados.
    */
   @Override
   public PlaylistUser clone() {
      return new PlaylistUser(this);
   }

   /**
    * @brief Retorna uma representação textual da playlist.
    * 
    * Gera uma string detalhada com o nome da playlist, visibilidade (pública ou privada),
    * número total de músicas, duração total em segundos e uma listagem das músicas com os respetivos nomes.
    *
    * @return String com a representação formatada da playlist.
    */
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Playlist: ").append(nome).append("\n");
      sb.append("Visibilidade: ").append(getPublica() ? "Pública" : "Privada").append("\n");
      sb.append("Número de músicas: ").append(musicas.size()).append("\n");
      sb.append("Duração total: ").append(getDuracaoTotal()).append(" segundos\n");
      sb.append("Músicas:\n");
         for (int i = 0; i < musicas.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(musicas.get(i).getNome()).append("\n");
      }
            return sb.toString();
   } 

}
