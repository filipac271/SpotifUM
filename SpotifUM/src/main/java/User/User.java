package User;

import PlanoSubscricao.PlanoSubscricao;

public class User {
    
    private String nome;
    private String password;
    private String email;
    private String morada;
    private double pontos;
    private PlanoSubscricao plano;

   
    
    public User()
    {
        this.nome="";
        this.password="";
        this.email="";
        this.morada="";
        this.pontos=0;
        this.plano=null;
        
    }
    public User(String nome, String password, String email, 
                String morada,PlanoSubscricao plano)
    {
        this.nome=nome;
        this.password=password;
        this.email=email;
        this.morada=morada;
        this.pontos=plano.calculaPontos(0);
        this.plano=plano;
    }
    public User(User user)
    {
        this.nome=user.getNome();
        this.email=user.getEmail();
        this.morada=user.getMorada();
        this.pontos=user.getPontos();
        this.plano=user.getPlano();

    }

    public String getNome(){
        return this.nome;
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

    public void setNome(String nome) { 
        this.nome = nome; 
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
   


//     @Override
//    public abstract User clone();

}
