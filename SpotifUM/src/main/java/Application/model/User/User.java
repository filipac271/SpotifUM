package Application.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.time.LocalDate;

import Application.model.PlanoSubscricao.PlanoPremium;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.Song.Song;

/**
 * @class User
 * @brief Representa um utilizador da aplicação, com dados pessoais,
 * histórico de músicas e plano de subscrição.
 */
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

    /**
     * @brief Construtor por defeito. Inicializa os campos com valores padrão.
     */
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

    /**
     * @brief Construtor parametrizado.
     * @param nome Nome do utilizador.
     * @param username Nome de utilizador.
     * @param password Palavra-passe.
     * @param email Endereço de email.
     * @param morada Morada do utilizador.
     * @param age Idade do utilizador.
     * @param plano Plano de subscrição.
     */
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

    /**
     * @brief Construtor de cópia.
     * @param user Outro objeto User a ser copiado.
     */
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

    /// @return Nome do utilizador.
    public String getNome() { 
        return this.nome; 
    }

    /// @return Username do utilizador.
    public String getUsername() { 
        return this.username; 
    }

    /// @return Palavra-passe do utilizador.
    public String getPassword() { 
        return this.password; 
    }

    /// @return Email do utilizador.
    public String getEmail() { 
        return this.email; 
    }

    /// @return Morada do utilizador.
    public String getMorada() { 
        return this.morada; 
    }

    /// @return Idade do utilizador.
    public int getAge() { 
        return this.age; 
    }

    /// @return Pontuação do utilizador.
    public double getPontos() { return this.pontos; }

    /**
     * @return Plano de subscrição do utilizador.
     */
    public PlanoSubscricao getPlano() {
        return this.plano;
    }

    /**
     * @return Lista de histórico de músicas (clonada).
     */
    public List<Historico> getHistorico() {
        return this.historico.stream()
                .map(Historico::clone)
                .collect(Collectors.toList());
    }

    /// @param nome Novo nome a ser definido.
    public void setNome(String nome){ 
        this.nome = nome; 
    }

    /// @param username Novo username.
    public void setUsername(String username) { 
        this.username = username; 
    }

    /// @param password Nova palavra-passe.
    public void setPassword(String password) { 
        this.password = password; 
    }

    /// @param email Novo email.
    public void setEmail(String email) { 
        this.email = email; 
    }

    /// @param morada Nova morada.
    public void setMorada(String morada) { 
        this.morada = morada; 
    }

    /// @param age Nova idade.
    public void setAge(int age) { 
        this.age = age; 
    }

    /// @param pontos Nova pontuação.
    public void setPontos(double pontos) { 
        this.pontos = pontos; 
    }

    /// @param plano Novo plano de subscrição.
    public void setPlano(PlanoSubscricao plano) { 
        this.plano = plano; 
    }

    /**
     * @brief Retorna o número de playlists permitido pelo plano.
     * @return Número de playlists.
     */
    public int getNumPlaylists() {
        if (plano instanceof PlanoPremium) {
            return ((PlanoPremium) plano).numPlaylists();
        } else {
            return 1;
        }
    }

    /**
     * @brief Conta o número de músicas ouvidas entre duas datas.
     * @param dataInicio Data de início.
     * @param dataFim Data de fim.
     * @return Número de músicas ouvidas no intervalo, ou total se dataInicio for null.
     */
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

    /**
     * @brief Adiciona uma música ao histórico do utilizador.
     * @param song Música a ser adicionada.
     * @param data Data de audição.
     */
    public void addHistorico(Song song, LocalDate data) {
        Historico h = new Historico(song, data);
        this.historico.add(h);
    }

    /**
     * @brief Cria uma cópia do objeto User.
     * @return Novo objeto User clonado.
     */
    @Override
    public User clone() {
        return new User(this);
    }

    /**
     * @brief Representação textual do utilizador.
     * @return String com os dados do utilizador.
     */
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
