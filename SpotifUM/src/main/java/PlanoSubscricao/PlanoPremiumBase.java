package PlanoSubscricao;



public class PlanoPremiumBase extends PlanoPremium{
    public PlanoPremiumBase()
    {  
        super();
         this.nomePlano="Premium Base"; 
    
    }
    public double calculaPontos(double pontos)
    {
        return pontos+10;
    }
}
