package Application.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.time.LocalDate;

import Application.model.PlanoSubscricao.PlanoPremium;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.Song.Song;

public class User implements Serializable {

    private String nome;
    private String username;
    private String password;
    private String email;
    private String morada;
    private int age;
    private double pontos;
    private PlanoSubscricao plano;
    private List<Historico> historico;

    public User() {
        this.nome = "";
        this.username = "";
        this.password = "";
        this.email = "";
        this.morada = "";
        this.age = 0;
        this.pontos = 0;
        this.plano = null;
        this.historico = new ArrayList<>();

    }

    public User(String nome, String username, String password, String email,
            String morada, int age, PlanoSubscricao plano) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.morada = morada;
        this.age = age;
        this.pontos = plano.calculaPontos(0);
        this.plano = plano;
        this.historico = new ArrayList<>();

    }

    public User(User user) {
        this.nome = user.getNome();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.morada = user.getMorada();
        this.age = user.getAge();
        this.pontos = user.getPontos();
        this.plano = user.getPlano();
        this.historico = user.getHistorico();
    }

    public String getNome() {
        return this.nome;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMorada() {
        return this.morada;
    }

    public int getAge() {
        return this.age;
    }

    public double getPontos() {
        return this.pontos;
    }

    public PlanoSubscricao getPlano() {
        //        return this.plano.clone;

        return this.plano;
    }

    public List<Historico> getHistorico() {
        return this.historico.stream()
                .map(h -> h.clone())
                .collect(Collectors.toList());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }

    public void setPlano(PlanoSubscricao plano) {
        this.plano = plano;
    }

    public int getNumPlaylists() {
        if (plano instanceof PlanoPremium) {
            return ((PlanoPremium) plano).numPlaylists();
        } else {
            return 1;
        }
    }
    

    public int numMusicasOuvidas(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null) {
            return this.historico.size();
        } else {
            try {
                long numMusics = historico.stream()
                        .map(Historico::getData)
                        .filter(data -> !data.isBefore(dataInicio) && !data.isAfter(dataFim))
                        .count();

                return (int) numMusics;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public void addHistorico(Song song, LocalDate data) {
        Historico h = new Historico(song, data);
        this.historico.add(h);
    }

    @Override
    public User clone() {
        return new User(this);
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User [nome: ").append(this.nome)
                .append(", username: ").append(this.username)
                .append(", email: ").append(this.email)
                .append(", morada: ").append(this.morada)
                .append(", pontos: ").append(this.pontos)
                .append(" plano: ").append(this.getPlano().getNome())
                .append("]");

        return sb.toString();
    }

}
