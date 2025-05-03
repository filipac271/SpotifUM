package Application.view;

import java.util.Scanner;

import Application.controller.Controller;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    // Método para reproduzir a música (imprimir a letra no terminal)
    public void playSong(String name) {
        System.out.println("Reproduzindo a música: " + controller.getSong(name).getNome());
        System.out.println("Intérprete: " + controller.getSong(name).getInterprete());
        System.out.println("Editora: " + controller.getSong(name).getEditora());
        System.out.println("Gênero: " + controller.getSong(name).getGenero());
        System.out.println("Duração: " + controller.getSong(name).getDuracao() + " segundos");
        System.out.println("\nLetra da música:");
        System.out.println(controller.getSong(name).getLetra());
    }

    public void mainMenu() {
        System.out.println("\n1. Iniciar Sessão");
        System.out.println("2. Criar conta");
        System.out.println("3. Criar Álbum");
        System.out.println("4. Fechar o programa");
        System.out.println("Prima o número correspondente à opção que deseja executar:");
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            int option;
            mainMenu();
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                optionError();
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    logInUserMenu(sc);
                    break;
                case 2:
                    createUserMenu(sc);
                    break;
                case 3:
                    createAlbumMenu(sc);
                    break;
                case 4:
                    out();
                    break;
                default:
                    optionError();
                    break;
            }

            if (option == 4) {
                controller.saveAll();
                break;
            }
        }

        sc.close();
    }

    public void logInUserMenu(Scanner sc) {

        System.out.println("A iniciar sessao");

        System.out.println("Digite o seu Username: ");
        String username = sc.nextLine();
        if (!(controller.userExists(username))) {
            System.out.println("Utilizador não existe!");
        } else {
            System.out.println("Digite a sua Password: ");
            String password = sc.nextLine();
            if (!controller.authenticUser(username, password)) {
                System.out.println("Password ou nome de Utilizador estão errados!");
                //////////
            } else {
                System.out.println("Bem vindo de volta " + username);
                userMenu(sc, username);
            }
        }

    }

    public int createPlanoMenu(Scanner sc) {
        System.out.println("\nEscolha o seu plano:");
        System.out.println("\n1.Plano Free");
        System.out.println("\n2.Plano Premium Base");
        System.out.println("\n3.Plano Premium Top");
        int option = 0;
        try {
            option = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            optionError();
            sc.nextLine();
        }
        return option;
    }

    public void createUserMenu(Scanner sc) {

        System.out.println("Digite o seu Nome: ");
        String nome = sc.nextLine();
        System.out.println("Crie um username único: ");
        String username = sc.nextLine();

        while (controller.userExists(username)) {
            System.out.println("Este username já existe! Escolha outro: ");
            username = sc.nextLine();
        }

        System.out.println("Crie uma password: ");
        String password = sc.nextLine();
        System.out.println("Digite o seu Email: ");
        String email = sc.nextLine();
        System.out.println("Digite a sua Morada: ");
        String morada = sc.nextLine();

        int planoOption = createPlanoMenu(sc);
        controller.addUser(nome, username, password, email, morada, planoOption);

        System.out.println("A sua conta foi criada com sucesso " + nome);

        System.out.println("Nome: " + nome + "\nUsername" + username + "\nEmail: " + email + "\nMorada: " + morada);

    }

    public void createAlbumMenu (Scanner sc){

        System.out.println("Criar um album");

        System.out.println("Digite o número de musicas do album: ");
        int numMusicas = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do Album: ");
        String nome = sc.nextLine();
        System.out.println("Digite o artista do Album: ");
        String artista = sc.nextLine();
        controller.addAlbum(nome, artista, null);

        for (int i = 0; i < numMusicas; i++) {
        System.out.println("Música " + (i + 1) + ":");

        System.out.print(" Digite o nome: ");
        String nomeMusica = sc.nextLine();

        System.out.print(" Digite o intérprete: ");
        String interprete = sc.nextLine();

        System.out.print(" Digite a editora: ");
        String editora = sc.nextLine();

        System.out.print(" Digite a letra: ");
        String letra = sc.nextLine();

        System.out.print(" Digite a pauta: ");
        String pauta = sc.nextLine();

        System.out.print(" Digite o gênero: ");
        String genero = sc.nextLine();

        System.out.print(" Digite a duração (em segundos): ");
        int duracao = sc.nextInt();
        sc.nextLine();
        controller.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao);
        

        controller.getAlbum(nome).addSong(controller.getSong(nomeMusica));
        }
        
        System.out.println("O album " + nome +" de " + artista + " foi criado tendo um total de " + numMusicas + " músicas.");

    }

    public void out() {
        System.out.println("A sair...");
    }

    public void optionError() {
        System.out.println("Entrada inválida. Tente novamente.");
    }

    public void userMenu(Scanner sc, String username) {
        System.out.println("Seja bem-vindo/a, " + username);

        String tipoPlano = controller.getUser(username).getPlano().getNome();

        System.out.println("\nAqui estão as opções para Plano" + tipoPlano);

        switch (tipoPlano) {
            case "Free":
                createUserFreeMenu(sc, controller);
                break;

            // case "Premium Base":
            // createUserPremiumBaseMenu();
            // break;

            // case "Premium Top":
            // createuserPTMenu();
            // break;

        }

    }

    public void createUserFreeMenu(Scanner sc, Controller controller) {
        System.out.println("\nTemos as melhores músicas para ouvir!");
        System.out.println("\nPressione 1 para ouvir música");

        String opcao = sc.nextLine();

        if (opcao.equals("1")) {
            controller.createPlaylistRandom().reproduzir(); // Cria a playlist no controller e depois reproduz-la
        } else {
            System.out.println("Opção inválida. Para mais opções dê upgrade do plano!");
        }

    }
}