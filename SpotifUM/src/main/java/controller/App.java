
package controller;

import java.util.Scanner;

import io.Input;

public class App {

    public void mainLoop() {
        Scanner sc = new Scanner(System.in); 
        Input io = new Input();

        while (true) {

            int option;
    
            try {
                option = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine(); 
                continue;
            }
    
            switch (option) {
                case 1:
                    System.out.println("A tentar iniciar sessao");
                    io.createUserMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("A sair...");
                    break; 
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            if (option == 4) {
                break; // Sair do while
            }
        }
    
       sc.close();
    }
    

    public static void main(String[] args) {
        // Criando uma instância de IO
        Input io = new Input();
        App app = new App();

        io.mainMenu();

        app.mainLoop();

    }
}