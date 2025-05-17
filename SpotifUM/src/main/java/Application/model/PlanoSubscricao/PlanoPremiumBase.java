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
     * @brief Construtor por cópia da classe PlanoPremiumBase.
     * 
     * Cria uma nova instância de PlanoPremiumBase copiando os dados de outra instância.
     * Chama o construtor da superclasse para garantir a cópia correta da hierarquia.
     *
     * @param outro A instância de PlanoPremiumBase a ser copiada.
     */
    public PlanoPremiumBase(PlanoPremiumBase outro) {  
        super(outro);
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

    /**
     * @brief Cria uma cópia deste objeto.
     * 
     * Método de clonagem que retorna uma nova instância de PlanoPremiumBase com os mesmos dados.
     *
     * @return Uma nova instância de PlanoPremiumBase, cópia desta.
     */
    @Override
    public PlanoPremiumBase clone(){
        return new PlanoPremiumBase(this);
    }

}
