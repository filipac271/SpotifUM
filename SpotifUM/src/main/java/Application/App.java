package Application;

import Application.controller.Controller;
import Application.model.Model;
import Application.view.View;

/**
 * Classe principal da aplicação SpotifUM.
 * 
 * Esta classe contém o método main que inicializa o modelo, controlador e a interface gráfica
 * (view) e inicia a execução da aplicação.
 * 
 * @author  
 */
public class App {

    /**
     * Método principal que inicia a aplicação.
     * Cria as instâncias do Model, Controller e View, e chama o método run da View para começar.
     * 
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        view.run();
    }
}
