package PlanoSubscricao;

public class PlanoPremiumBase implements PlanoSubscricao{
    public double calculaPontos(double pontos)
    {
        return pontos+10;
    }
}
