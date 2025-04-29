        package Playlist;


    import java.util.List;

    import java.util.stream.Collectors;



    import PlanoSubscricao.PlanoSubscricao;
    import Song.Song;


    import java.util.ArrayList;



    public abstract class Playlist {
        protected String nome;
        protected List<Song> musicas;
        private boolean publica;


            public Playlist(String nome, List<Song> musicas,boolean publica) {
                this.nome = nome;
                this.musicas = musicas.stream()
                                    .map(song -> song.clone())
                                    .collect(Collectors.toList());
                this.publica=publica;
        
            }
            
            public Playlist() {
                this.nome = "";
                this.musicas = new ArrayList<>();
                this.publica=false;
            }
        
            public Playlist(Playlist outra) {
                this.nome = outra.nome;
                this.musicas = outra.musicas.stream()
                        .map(song -> song.clone())
                        .collect(Collectors.toList());
                this.publica=outra.publica;
        
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }

            public List<Song> getMusicas() {
                return musicas.stream()
                        .map(song -> song.clone())
                        .collect(Collectors.toList());
            }

            public void setMusicas(List<Song> musicas) {
                this.musicas = musicas.stream()
                        .map(song -> song.clone())
                        .collect(Collectors.toList());
            }
            public boolean getPublica()
            {
                return this.publica;
            }
        
            public void setPublica(boolean publica)
            {
                this.publica=publica;
            }

            public void adicionarMusica(Song musica) {
                musicas.add(musica.clone());
            }

            public void removerMusica(Song musica) {
                musicas.remove(musica);
            }

            public int getDuracaoTotal() {
                return musicas.stream().mapToInt(song -> song.getDuracao()).sum();
            }

            public boolean isEmpty() {
                return musicas.isEmpty();
            }

            public int tamanho() {
                return musicas.size();
            }

            public abstract Playlist clone();


            public abstract void reproduzir();
        }

