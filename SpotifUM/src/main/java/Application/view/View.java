package Application.view;

import java.time.LocalDate;
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

    public int getOpcao(Scanner sc) {
        int option = -1;
        while (option == -1) {
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                optionError();
                sc.nextLine();
            }
        }
        return option;
    }

    public String getOpcaoString(Scanner sc) {
        String option = null;
        while (option == null) {
            try {
                option = sc.nextLine();
                sc.nextLine();
            } catch (Exception e) {
                optionError();
                sc.nextLine();
            }
        }
        return option;
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            int option;
            mainMenu();
            option = getOpcao(sc);

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
                    //checkQueriesMenu
                    break;
                case 5:
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

    public void createAlbumMenu(Scanner sc) {

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

        System.out.println(
                "O album " + nome + " de " + artista + " foi criado tendo um total de " + numMusicas + " músicas.");

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

            case "Premium Base":
                createUserPremiumBaseMenu(sc, controller, username);
                break;

            case "Premium Top":
                createuserPTMenu(sc, controller, username);
                break;

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

    public void createUserPremiumBaseMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\nPressione 1 para ouvir");
        System.out.println("\nPressione 2 para adicionar uma playlist à sua biblioteca");
        System.out.println("\nPressione 3 para adicionar um album à sua biblioteca");
        System.out.println("\nPressione 4 para criar uma playlist");
        System.out.println("\nPressione 5 para sair");

        while (true) {
            int opcao = getOpcao(sc);

            switch (opcao) {
                case 1:
                    createOuvirMenu(sc, controller, username);
                    break;
                case 2:
                    System.out.println("\nQual o nome da playlist que deseja adicionar?");
                    String nome = getOpcaoString(sc);
                    controller.guardarPlaylist(username, nome);
                    break;
                case 3:
                    System.out.println("\nQual o nome do album que deseja adicionar?");
                    String nomeA = getOpcaoString(sc);
                    controller.guardarPlaylist(username, nomeA);
                    break;
                case 4:
                    criarPlaylistMenu(sc, username);
                    break;
                case 5:
                    out();
                    break;
                default:
                    break;
            }

            if (opcao == 4)
                break;
        }

        System.out.println("\nPressione 4 para criar uma playlist");
        System.out.println("\nPressione 5 para adicionar uma playlist à sua biblioteca");
        System.out.println("\nPressione 6 para adicionar um album à sua biblioteca");
    }

    public void createOuvirMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\nPressione 1 para ouvir música");
        System.out.println("\nPressione 2 para ouvir uma playlist");// Ver a parte de ordenar !!!!
        System.out.println("\nPressione 3 para ouvir um album");

        while (true) {
            int opcao = getOpcao(sc);
            String nome;
            int index;
            String selecao;
            switch (opcao) {
                case 1:
                    nome = menuOuvir(sc, "musica");
                    break;
                case 2:
                    nome = menuOuvir(sc, "playlist");
                    index = 0;
                    selecao = null;
                    while (index < controller.getPlaylist(nome).tamanho()) {
                        index = controller.reproduzirPlaylist(username, nome, index, selecao);
                        if (index == -1) {
                            System.out.println("\nOperação não é possivel!");
                        }
                        selecao = perguntarContinuar(sc);
                        if (selecao.equals("N"))
                            break;
                    }
                    break;
                case 3:
                    nome = menuOuvir(sc, "album");
                    index = 0;
                    selecao = null;
                    while (index < controller.getAlbum(nome).tamanho()) {
                        index = controller.reproduzirAlbum(username, nome, index, selecao);
                        selecao = perguntarContinuar(sc);
                        if (selecao.equals("N"))
                            break;
                    }

                case 4:
                    out();
                default:
                    break;
            }

            if (opcao == 4)
                break;
        }

    }

    public String menuOuvir(Scanner sc, String tipo) {
        System.out.println("\nQual " + tipo + " deseja ouvir?");
        String nome = sc.nextLine();
        return nome;
    }

    public String perguntarContinuar(Scanner sc) {
        System.out.println("\nA - Avançar Música ");
        System.out.println("\nP - Próxima Música");
        System.out.println("\nR - Retroceder ");
        System.out.println("\nS - Sair");

        String opcao = getOpcaoString(sc);
        return opcao;
    }

    public void criarPlaylistMenu(Scanner sc, String username) {
        System.out.println("Criar uma playlist:");
        System.out.println("Nome da Playlist:");
        String nomeP = getOpcaoString(sc);
        System.out.println("Tornar playlist pública? (s/n)");
        String publica = getOpcaoString(sc);
        System.out.println("Quantas músicas pretende adicionar:");
        int nMusicas = getOpcao(sc);
        controller.createPlaylist(username, nomeP, publica);
        String nomeM;
        for (int i = 0; i < nMusicas; i++) {
            System.out.println("Nome da Música Nº: " + (i + 1));
            nomeM = getOpcaoString(sc);
            controller.addToPlaylist(nomeP, nomeM);

        }
        controller.addPlaylistToUser(nomeP, username);
    }

    public void createuserPTMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\nPressione 1 para ouvir");
        System.out.println("\nPressione 2 para adicionar uma playlist à sua biblioteca");
        System.out.println("\nPressione 3 para adicionar um album à sua biblioteca");
        System.out.println("\nPressione 4 para criar uma playlist");
        System.out.println("\nPressione 5 para gerar uma playlist");
        System.out.println("\nPressione 6 para sair");

        while (true) {
            int opcao = getOpcao(sc);

            switch (opcao) {
                case 1:
                    createOuvirMenu(sc, controller, username);
                    break;
                case 2:
                    System.out.println("\nQual o nome da playlist que deseja adicionar?");
                    String nome = getOpcaoString(sc);
                    controller.guardarPlaylist(username, nome);
                    break;
                case 3:
                    System.out.println("\nQual o nome do album que deseja adicionar?");
                    String nomeA = getOpcaoString(sc);
                    controller.guardarPlaylist(username, nomeA);
                    break;
                case 4:
                    criarPlaylistMenu(sc, username);
                    break;
                case 5:
                    // Recomendador
                    break;
                case 6:
                    out();
                    break;
                default:
                    break;
            }

            if (opcao == 4)
                break;
        }

    }

public void checkQueriesMenu(Scanner sc, Controller controller) {
    System.out.println("Sê bem-vindo/a às estatísticas do SpotifUM!");
    System.out.println("\nPressione 1 para ver qual é a música mais reproduzida.");
    System.out.println("Pressione 2 para verificar qual é o intérprete mais ouvido.");
    System.out.println("Pressione 3 para verificar qual é o utilizador que mais ouviu músicas desde sempre.");
    System.out.println("Pressione 4 para verificar qual é o utilizador que mais músicas ouviu por um determinado período de tempo.");
    System.out.println("Pressione 5 para verificar qual é o utilizador com mais pontos.");
    System.out.println("Pressione 6 para verificar qual é o género mais ouvido.");
    System.out.println("Pressione 7 para verificar quantas playlists públicas existem.");
    System.out.println("Pressione 8 para verificar qual é o utilizador com mais playlists.");

    int opcao = getOpcao(sc);
    LocalDate dataInicio = null;
    LocalDate dataFim = null;

    // Pedir as datas para a q3 onde se pode escolher o intervalo
    if (opcao == 4) {
        System.out.print("Introduza a data de início (YYYY-MM-DD): ");
        dataInicio = LocalDate.parse(sc.nextLine());
        System.out.print("Introduza a data de fim (YYYY-MM-DD): ");
        dataFim = LocalDate.parse(sc.nextLine());
    }

    switch (opcao) {
        case 1:
            controller.query1();
            break;
        case 2:
        case 6:
            controller.query2e5(opcao);
            break;
        case 3:
        case 5:
        case 8:
            controller.query34e7(opcao, null, null);
            break;
        case 4:
            controller.query34e7(opcao, dataInicio, dataFim);
            break;
        case 7:
            controller.query6();
            break;
        default:
            System.out.println("Opção inválida.");
            break;
    }
}


}
