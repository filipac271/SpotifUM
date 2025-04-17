package User;

public class UserPremiumTop extends UserPremium{
    @Override
    public void adicionarPontos(int p) {
        this.setPontos(p*0.025 + p);
        
    }

   
}
