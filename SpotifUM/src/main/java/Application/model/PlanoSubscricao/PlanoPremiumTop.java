package Application.model.PlanoSubscricao;

/**
 * Classe que representa o plano de subscrição "Premium Top".
 * Extende {@link PlanoPremium} e oferece vantagens adicionais no cálculo de pontos.
 */
public class PlanoPremiumTop extends PlanoPremium {

    /**
     * Construtor por omissão.
     * Define o nome do plano como "Premium Top".
     */
    public PlanoPremiumTop() {
        super();
        this.nomePlano = "PlanoPremiumTop";
    }

    public PlanoPremiumTop(PlanoPremiumTop outro) {
        super(outro);
        this.nomePlano = "PlanoPremiumTop";
    }

    /**
     * Calcula os pontos atribuídos a um utilizador deste plano.
     * Se os pontos forem zero, retorna 100(quando é feito o login pela primeira vez). 
     * Caso contrário, adiciona 2.5% de bónus.
     * 
     * @param pontos Pontos base a considerar.
     * @return Pontos ajustados com bónus do plano.
     */
    public double calculaPontos(double pontos) {
        if (pontos == 0) return 100;
        return (0.025 * pontos + pontos);
    }


    @Override
    public PlanoPremiumTop clone(){
        return new PlanoPremiumTop(this);
    }
    
}
