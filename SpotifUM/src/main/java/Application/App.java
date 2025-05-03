package Application;
import Application.controller.Controller;
import Application.model.Model;
import Application.view.View;




public class App {



    public static void main(String[] args) {

        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        view.run();

    }
}