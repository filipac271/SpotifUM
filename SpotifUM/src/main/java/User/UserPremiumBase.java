package User;

public class UserPremiumBase extends UserPremium{

    @Override
    public void adicionarPontos(int p) {
        this.setPontos(p+10);
        
    }
    
}
