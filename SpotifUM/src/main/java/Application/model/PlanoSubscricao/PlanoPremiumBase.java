package Application.model.PlanoSubscricao;

/**
 * Representa o plano de subscrição "Premium Base".
 * 
 * Este plano oferece funcionalidades premium básicas como playlists e avanço/retrocesso,
 * e atribui pontos adicionais simples.
 */
public class PlanoPremiumBase extends PlanoPremium {

    /**
     * Construtor por omissão.
     * Inicializa o plano com o nome "Premium Base".
     */
    public PlanoPremiumBase() {  
        super();
        this.nomePlano = "PlanoPremiumBase"; 
    }

    /**
     * Calcula os pontos atribuídos com base nos pontos atuais.
     * 
     * @param pontos Pontos atuais.
     * @return Pontos atualizados (acrescenta 10).
     */
    @Override
    public double calculaPontos(double pontos) {
        return pontos + 10;
    }


}
