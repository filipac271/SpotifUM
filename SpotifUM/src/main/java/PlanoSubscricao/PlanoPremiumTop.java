package PlanoSubscricao;

public class PlanoPremiumTop extends PlanoPremium{
   
    public PlanoPremiumTop()
    {  
        super();
         this.nomePlano="premiumTop"; 
    
    }

    public double calculaPontos(double pontos)
    {
        if(pontos==0) return 100;
        return (0.025*pontos +pontos);
    }
}

