
package controller;

import java.util.Scanner;

import view.Input;

public class App {

    public void mainLoop() {
        Scanner sc = new Scanner(System.in); 
        Input io = new Input();
        Controller controller= new Controller();
        while (true) {

            int option;
            io.mainMenu();
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                io.optionError();
                sc.nextLine(); 
                continue;
            }

            switch (option) {
                case 1:
                     
                    io.logInUserMenu(sc,controller);
                    break;
                case 2:
                    io.createUserMenu(sc,controller);  
                    break;
                case 3:
                    io.createAlbumMenu(sc,controller);
                    break;
                case 4:
                    io.out();
                    break; 
                default:
                    io.optionError();
                    break;
            }

            if (option == 4) {
                break; 
            }
        }
    
       sc.close();
    }
    

    public static void main(String[] args) {
        App app = new App();
        
        app.mainLoop();

    }
}