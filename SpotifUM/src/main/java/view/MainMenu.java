package view;

import java.util.Scanner;

public class MainMenu {
     public void mainLoop() {
        Scanner sc = new Scanner(System.in); 
        Input io = new Input();

        while (true) {

            int option;
            io.mainMenu();
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine(); 
                continue;
            }
    
            switch (option) {
                case 1:
                    System.out.println("A tentar iniciar sessao");
                    io.createUserMenu(sc);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Bora lá criar um album");
                    io.createAlbumMenu(sc);
                    break;
                case 4:
                    System.out.println("A sair...");
                    break; 
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            if (option == 4) {
                break; 
            }
        }
    
       sc.close();
    }
    
}
