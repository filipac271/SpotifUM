package Application.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.time.LocalDate;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.Song.Song;


public class User implements Serializable{
    
    private String nome;
    private String username;
    private String password;
    private String email;
    private String morada;
    private double pontos;
    private PlanoSubscricao plano;
    private List<Historico> historico;
   
    
    public User()
    {
        this.nome="";
        this.username="";
        this.password="";
        this.email="";
        this.morada="";
        this.pontos=0;
        this.plano=null;
        this.historico=new ArrayList<>();
        
    }
    public User(String nome, String username,String password, String email, 
                String morada,PlanoSubscricao plano)
    {
        this.nome=nome;
        this.username=username;
        this.password=password;
        this.email=email;
        this.morada=morada;
        this.pontos=plano.calculaPontos(0);
        this.plano=plano;
        this.historico=new ArrayList<>();

    }
    public User(User user)
    {
        this.nome=user.getNome();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.morada=user.getMorada();
        this.pontos=user.getPontos();
        this.plano=user.getPlano();
        this.historico=user.getHistorico();
    }

    public String getNome(){
        return this.nome;
    }

    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }
    public String getMorada(){
        return this.morada;
    }
    public double getPontos(){
        return this.pontos;
    }
    public PlanoSubscricao getPlano()
    {
        return this.plano; 
    }
    public List<Historico> getHistorico()
    {
        return this.historico.stream()
                    .map(h -> h.clone())
                    .collect(Collectors.toList());
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public void setEmail(String email) { 
        this.email = email;
    }
    public void setMorada(String morada) { 
        this.morada = morada; 
    }
    public void setPontos(double pontos){
        this.pontos=pontos;
    }

    public void setPlano(PlanoSubscricao plano)
    {
        this.plano=plano;
    }
   
    public int getNumPlaylists()
    {
        return this.plano.numPlaylists();
    }
    
    public int numMusicasOuvidas(LocalDate dataInicio, LocalDate dataFim) {

        if(dataInicio==null) 
        {
            return this.historico.size();
        }
        else
        {
            return (int) historico.stream()
            .map(Historico::getData)
            .filter(data -> !data.isBefore(dataInicio) && !data.isAfter(dataFim))
            .count();
        }
        
    }

    public void addHistorico(Song song,LocalDate data)
    {
        Historico h= new Historico(song,data);
        this.historico.add(h);
    }


    @Override
    public User clone(){
        return new User(this);
    };

}
