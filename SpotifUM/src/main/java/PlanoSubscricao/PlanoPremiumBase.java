package PlanoSubscricao;



public class PlanoPremiumBase extends PlanoPremium{
    public PlanoPremiumBase()
    {  
        super();
         this.nomePlano="premiumBase"; 
    
    }
    public double calculaPontos(double pontos)
    {
        return pontos+10;
    }
}
