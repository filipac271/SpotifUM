package User;

public abstract class User {
    
    private String nome;
    private String email;
    private String morada;
    private double pontos;
   
    public abstract void adicionarPontos(int p) ;
    
    public User()
    {
        this.nome="";
        this.email="";
        this.morada="";
        this.pontos=0;
        
    }
    public User(String nome, String email, 
                String morada,double pontos)
    {
        this.nome=nome;
        this.email=email;
        this.morada=morada;
        this.pontos=0;
    }
    public User(User user)
    {
        this.nome=user.getNome();
        this.email=user.getEmail();
        this.morada=user.getMorada();
        this.pontos=user.getPontos();

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

//     @Override
//    public abstract User clone();
}
