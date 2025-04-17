package org.example.PlanoSubscricao;

public class PlanoPremiumTop implements PlanoSubscricao{
    public double calculaPontos(double pontos)
    {
        return (0.025*pontos +pontos);
    }
}

