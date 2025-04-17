    package Playlist;

    import java.util.List;
    import java.util.stream.Collectors;

    import Song.Song;

    import java.util.ArrayList;

    public abstract class Playlist {
        private String nome;
        private List<Song> musicas;

        public Playlist(String nome, List<Song> musicas) {
            this.nome = nome;
            this.musicas = musicas.stream()
                    .map(song -> song.clone())
                    .collect(Collectors.toList());
        }

        public Playlist() {
            this.nome = "";
            this.musicas = new ArrayList<>();
        }

        public Playlist(Playlist outra) {
            this.nome = outra.nome;
            this.musicas = outra.musicas.stream()
                    .map(song -> song.clone())
                    .collect(Collectors.toList());

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
