package Application.view;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

import Application.controller.Controller;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private void mainMenu() {
        System.out.println("\n 1. Iniciar Sessão");
        System.out.println(" 2. Criar conta");
        System.out.println(" 3. Criar Álbum");
        System.out.println(" 4. Estatísticas da App");
        System.out.println(" 5. Mudar de plano");
        System.out.println(" 6. Fechar o programa");
        System.out.println(" Prima o número correspondente à opção que deseja executar:");
    }


    private int getOpcao(Scanner sc, int min, int max) {
        int option = -1;
        while (option == -1) {
            try {
                option = sc.nextInt();
                sc.nextLine();
                if (option < min || option > max) {
                    option = -1;
                    System.out.println("Insira um número entre " + min + " e " + max + "!");
                }
            } catch (Exception e) {
                optionError();
                sc.nextLine();
            }
        }

        return option;
    }

    private String getOpcaoString(Scanner sc) {
        String option = "erro";
        while (option.equals("erro")) {
            try {
                option = sc.nextLine();
            } catch (Exception e) {
                optionError();
                option = sc.nextLine();
            }
        }
        return option;
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
    
        while (true) {
    
            int option;
            mainMenu(); // certifica-te de atualizar o menu mostrado ao utilizador
            option = getOpcao(sc, 1, 6);
    
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
                    checkQueriesMenu(sc);
                    break;
                case 5:
                    changePlanMenu(sc);
                    break;
                case 6:
                    out();
                    break;
                default:
                    optionError();
                    break;
            }

            if(option == 6){
                controller.saveAll();
                break;
            }

        }
    
        sc.close();
    }
    

    private void logInUserMenu(Scanner sc) {

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
            } else {
                System.out.println("\nBem vindo de volta " + username);
                userMenu(sc, username);
            }
        }

    }

    private void changePlanMenu(Scanner sc) {
        System.out.print("Introduz o teu username: ");
        String username = sc.nextLine();
    
        if (!controller.userExists(username)) {
            System.out.println("Utilizador não encontrado.");
            return;
        }
    
        System.out.println("Escolhe novo plano:");
        int option = createPlanoMenu(sc);
        
        String novoPlano = switch (option) {
            case 1 -> "PlanoFree";
            case 2 -> "PlanoPremiumBase";
            case 3 -> "PlanoPremiumTop";
            default -> null; 
        };
        
    
        boolean sucesso = controller.changeUserPlan(username, option,novoPlano);
        if (sucesso) {
            System.out.println("Plano alterado com sucesso para " + novoPlano + ".");
        } else {
            System.out.println("O plano escolhido é o mesmo que já está assinado.");
        }
    }
    

    private int createPlanoMenu(Scanner sc) {
        System.out.println("\nEscolha o seu plano:");
        System.out.println("\n1.Plano Free");
        System.out.println("\n2.Plano Premium Base");
        System.out.println("\n3.Plano Premium Top");
        int option = 0;
        option = getOpcao(sc, 1, 3);
        return option;
    }

    private void createUserMenu(Scanner sc) {

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
        System.out.println("Digite a sua Idade: ");
        int age = sc.nextInt();
        sc.nextLine();

        int planoOption = createPlanoMenu(sc);
        controller.addUser(nome, username, password, email, morada, age, planoOption);

        System.out.println("A sua conta foi criada com sucesso " + nome);

        System.out.println("Nome: " + nome + "\nUsername: " + username + "\nEmail: " + email + "\nMorada: " + morada);

    }

    private String criarMusica(Scanner sc) {
        System.out.print(" Digite o nome: ");
        String nomeMusica = getOpcaoString(sc);

        System.out.print(" Digite o intérprete: ");
        String interprete = getOpcaoString(sc);

        System.out.print(" Digite a editora: ");
        String editora = getOpcaoString(sc);

        System.out.print(" Digite a letra: ");
        String letra = getOpcaoString(sc);

        System.out.print(" Digite a pauta: ");
        String pauta = getOpcaoString(sc);

        System.out.print(" Digite o gênero: ");
        String genero = getOpcaoString(sc);

        System.out.print(" Digite a duração (em segundos): ");
        int duracao = sc.nextInt();
        sc.nextLine();

        System.out.print(" A musica é explicita? (s/n) ");
        String isExplicit = getOpcaoString(sc);

        System.out.print(" A musica têm video? (s/n) ");
        String isMedia = getOpcaoString(sc);
        String url = "";
        if (isMedia.equals("s")) {
            System.out.print(" Digite o url do video da musica: ");
            url = getOpcaoString(sc);
        }

        controller.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao, isExplicit, isMedia, url);

        return nomeMusica;
    }

    private void createAlbumMenu(Scanner sc) {
        System.out.println("\n-------Criar um album-------");
        
        System.out.println("Digite o número de musicas do album: ");
        int numMusicas = getOpcao(sc, 1, Integer.MAX_VALUE);
        System.out.println("Digite o nome do Album: ");
        String nome = getOpcaoString(sc);
        System.out.println("Digite o artista do Album: ");
        String artista = getOpcaoString(sc);
        controller.addAlbum(nome, artista);
        String nomeMusica;
        for (int i = 0; i < numMusicas; i++) {
            System.out.println("Música " + (i + 1) + ":");
            nomeMusica = criarMusica(sc);
            int v = controller.addToAlbum(nome,nomeMusica);
            if (v == 0){
                System.out.println("Musica n existente");
                i--;
            }
        }
        System.out.println("O album " + nome + " de " + artista + " foi criado tendo um total de " + numMusicas + " músicas.");
    }

    private void out() {
        System.out.println("A sair...");
    }

    private void optionError() {
        System.out.println("Entrada inválida. Tente novamente.");
    }

    private void userMenu(Scanner sc, String username) {

        String tipoPlano = controller.getPlanoByUser(username);

        switch (tipoPlano) {
            case "PlanoFree":
                createUserFreeMenu(sc, controller, username);
                break;

            case "PlanoPremiumBase":
                createUserPremiumBaseMenu(sc, controller, username);
                break;

            case "PlanoPremiumTop":
                createuserPTMenu(sc, controller, username);
                break;

        }

    }

    private void createUserFreeMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\nTemos as melhores músicas para ouvir!");
        System.out.println("\nPressione 1 para ouvir música");

        String opcao = sc.nextLine();

        if (opcao.equals("1")) {
            String nomePlaylist = controller.createPlaylistRandom();
            reproduzirSequencial(sc,username,nomePlaylist,true);
        } else {
            System.out.println("Opção inválida. Para mais opções dê upgrade do plano!");
        }

    }

    // Apresenta as opções de ações que um user Premium tem acesso
    private void opcoesPremiumMenu(String tipo) {
        System.out.println("\n||||||||||||||||||||||||||||||||||");
        System.out.println("\nPressione 1 para ouvir");
        System.out.println("\nPressione 2 para adicionar uma playlist à sua biblioteca");
        System.out.println("\nPressione 3 para adicionar um album à sua biblioteca");
        System.out.println("\nPressione 4 para criar uma playlist");

        if (tipo.equals("B")) {
            System.out.println("\nPressione 5 para sair");
        } else if (tipo.equals("T")) {
            System.out.println("\nPressione 5 para gerar uma playlist");
            System.out.println("\nPressione 6 para sair");
        }
    }

    // Menu para adicionar uma playlist ou álbum à biblioteca de um user
    private void adicionarPlaylistAlbum(String username, String tipo, Scanner sc) {
        System.out.println("\nQual " + tipo + " deseja adicionar? (Pressione Enter caso queira voltar atrás)");
        String nome = getOpcaoString(sc);
        if (nome.isEmpty())
            return;
        if (tipo.equals("album")) {
            controller.guardarAlbum(username, nome);
        } else if (tipo.equals("playlist")) {
            controller.guardarPlaylist(username, nome);
        }

    }

    // Apresenta as opções que um user com o plamo Premium Base tem acesso
    private void createUserPremiumBaseMenu(Scanner sc, Controller controller, String username) {

        while (true) {

            opcoesPremiumMenu("B");
            int opcao = getOpcao(sc, 1, 5);

            switch (opcao) {
                case 1:
                    createOuvirMenu(sc, controller, username);
                    break;
                case 2:
                    adicionarPlaylistAlbum(username, "playlist", sc);
                    break;
                case 3:
                    adicionarPlaylistAlbum(username, "album", sc);
                    break;
                case 4:
                    criarPlaylistMenu(sc, username);
                    break;
                case 5:
                    out();
                    break;
                default:
                    optionError();
                    break;
            }

            if (opcao == 5)
                break;
        }

    }

        private void reproduzirSequencial(Scanner sc, String username, String nome,boolean serPlaylist) {

            int[] index = new int[]{0};
            while (index[0] != -2) {
                String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome, index,serPlaylist);
                System.out.println(letraIndex);
                int select = perguntarContinuar(sc);
                index[0] = controller.proximaMusica(sc, index[0], select);
            }
        }

        private void reproduzirAleatorio(Scanner sc, String username, String nome,boolean serPlaylist) {

            List<Integer> ordem = controller.shufflePlaylist_album(nome, serPlaylist);
            int[] index = new int[]{0};
            int[] ordemIndex = new int[]{ordem.get(0)};
            while (index[0] != -2) {
                if(index[0] < 0 || index[0] >= ordem.size()) {
                    String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome, index,serPlaylist);
                    System.out.println(letraIndex);
                }else{
                    ordemIndex[0] = ordem.get(index[0]);
                    String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome,ordemIndex ,serPlaylist);
                    System.out.println(letraIndex);

                }
                int select = perguntarContinuar(sc);
                index[0] = controller.proximaMusica(sc, index[0], select);
            }
        }

    // Apresenta o menu de audição de uma música/playlist/álbum
    private void createOuvirMenu(Scanner sc, Controller controller, String username) {

        while (true) {
            System.out.println("\n||||||||||||||||||||||||||||||||||");
            System.out.println("\nPressione 1 para ouvir música");
            System.out.println("\nPressione 2 para ouvir uma playlist");
            System.out.println("\nPressione 3 para ouvir um album");
            System.out.println("\nPressione 4 para voltar atrás");
            int opcao = getOpcao(sc, 1, 4);
            String nome;
            int aleatorio;
            switch (opcao) {
                case 1:
                    nome = menuOuvir(sc, "musica", username);
                    if (nome.equals(""))
                        break;
                    String letra = controller.reproduzirMusica(username, nome);
                    System.out.println(letra);
                    break;
                case 2:
                    nome = menuOuvir(sc, "playlist", username);
                    if (nome.equals(""))
                        break;
                    System.out.println("\n Reproduzir aleatoriamente? \n   1-Sim  2-Não)");
                    aleatorio = getOpcao(sc, 1, 2);
                    if (aleatorio == 1) {

                        reproduzirAleatorio(sc, username, nome, true);
                    } else if (aleatorio == 2) {
                        reproduzirSequencial(sc, username, nome,true);
                    }

                    break;
                case 3:
                    nome = menuOuvir(sc, "album", username);
                    if (nome.equals(""))
                        break;
                    System.out.println("\nReproduzir aleatoriamente? \n 1-Sim  2-Não");
                    aleatorio = getOpcao(sc, 1, 2);
                    if (aleatorio == 1) {
                        reproduzirAleatorio(sc, username, nome, false);
                    } else if (aleatorio == 2) {
                        reproduzirSequencial(sc, username, nome, false);
                    }
                    break;
                case 4:
                    out();
                    break;
                default:
                    break;
            }

            if (opcao == 4)
                break;
        }

    }

    // Pergunta ao user que música/playlist/album quer ouvir e valida o nome dado
    private String menuOuvir(Scanner sc, String tipo, String username) {
        System.out.println("\nQual " + tipo + " deseja ouvir? (Pressione Enter caso queira voltar atrás)");
        String nome = sc.nextLine();
        if (nome.isEmpty())
            return "";
        while (!this.controller.validaAudicao(username, tipo, nome)) {
            System.out.println(tipo + "\n não existe");
            System.out.println("\nQual " + tipo + " deseja ouvir? (Pressione Enter caso queira voltar atrás)");
            nome = sc.nextLine();
            if (nome.isEmpty())
                return "";
        }

        return nome;
    }

    // Apresenta ao user (Premium) as opções de escolha de uma música ao ouvir uma playlist
    private int perguntarContinuar(Scanner sc) {
        System.out.println("\n1 - Avançar Música ");
        System.out.println("\n2 - Próxima Música");
        System.out.println("\n3 - Retroceder ");
        System.out.println("\n4 - Recomeçar ");
        System.out.println("\n5 - Sair");

        int opcao = getOpcao(sc, 1, 5);
        return opcao;
    }

    // Apresenta o menu da opcao criar playlist
    private void criarPlaylistMenu(Scanner sc, String username) {
        System.out.println("\n-------Criar uma playlist-------\n");
        System.out.println("Nome da Playlist:");
        String nomeP = getOpcaoString(sc);
        System.out.println("Tornar playlist pública? (s/n)");
        String publica = getOpcaoString(sc);
        System.out.println("Quantas músicas pretende adicionar:");
        int nMusicas = getOpcao(sc, 1, Integer.MAX_VALUE);
        System.out.println("Quer limite máximo de tempo e um género específico na playlist? (s/n)");
        String limiteTempoGenero = getOpcaoString(sc);
        int limiteTempo = 0;
        String genero = "";
        if (limiteTempoGenero.equals("s")) {
            System.out.println("Limite máximo de tempo:");
            limiteTempo = sc.nextInt();
            sc.nextLine();
            System.out.println("Género da Playlist:");
            genero = getOpcaoString(sc);
        }

        controller.addPlaylist(nomeP, publica, limiteTempoGenero, genero, limiteTempo);
        String nomeM;
        int i, v;
        for (i = 0; i < nMusicas; i++) {
            System.out.println("Nome da Música Nº: " + (i + 1));
            nomeM = getOpcaoString(sc);
            v = controller.addToPlaylist(nomeP, nomeM);
            if (v == 0) {
                System.out.println("Música não existe!");
                i--;
            }
        }
        ordenarPlaylist(nomeP, nMusicas, sc);
        controller.addPlaylistToUser(nomeP, username);
    }

    // Pergunta ao user como quer ordenar a sua Playlist
    private void ordenarPlaylist(String nomeP, int nMusicas, Scanner sc) {
        System.out.println("De que forma quer ordenar a playlist ?");
        System.out.println("1- Nome da música  a-z");
        System.out.println("2- Nome da música z-a");
        System.out.println("3- Nome do interprete a-z");
        System.out.println("4- Nome do interprete z-a");
        System.out.println("5- Quero decidir uma ordem especifica");
        int op = getOpcao(sc, 1, 5);

        int index;
        if (op == 5) {
            imprimePlaylist(nomeP, nMusicas);
            for (int i = 0; i < nMusicas; i++) {
                System.out.println("Qual a " + i + "ª música?");
                index = getOpcao(sc, 1, nMusicas);
                controller.trocaMusicas(nomeP, i, index - 1);
            }
        } else {
            controller.ordenarPlaylist(nomeP, op);
        }

    }

    // Imprime o nome das músicas que constituem uma playlist
    private void imprimePlaylist(String nomeP, int n) {
        System.out.println("Ordem das músicas atual:");
        int i = 1;
        for (String s : this.controller.getNomeMusicas(nomeP, n)) {
            System.out.println("\nNº " + i + ": " + s);
            i++;

        }
    }

    // Apresenta o menu do user que tem o plano premium top
    private void createuserPTMenu(Scanner sc, Controller controller, String username) {

        while (true) {
            opcoesPremiumMenu("T");
            int opcao = getOpcao(sc, 1, 6);

            switch (opcao) {
                case 1:
                    createOuvirMenu(sc, controller, username);
                    break;
                case 2:
                    adicionarPlaylistAlbum(username, "playlist", sc);
                    break;
                case 3:
                    adicionarPlaylistAlbum(username, "album", sc);
                    break;
                case 4:
                    criarPlaylistMenu(sc, username);
                    break;
                case 5:
                    recomendadorMenu(sc, controller, username);
                    break;
                case 6:
                    out();
                    break;
                default:
                    break;
            }

            if (opcao == 6)
                break;
        }

    }

    private void checkQueriesMenu(Scanner sc) {
        System.out.println("Sê bem-vindo/a às estatísticas do SpotifUM!");
        while (true) {
            System.out.println("\n Pressione 1 para ver qual é a música mais reproduzida.");
            System.out.println(" Pressione 2 para verificar qual é o intérprete mais ouvido.");
            System.out.println(" Pressione 3 para verificar qual é o utilizador que mais ouviu músicas desde sempre.");
            System.out.println(
                    " Pressione 4 para verificar qual é o utilizador que mais músicas ouviu por um determinado período de tempo.");
            System.out.println(" Pressione 5 para verificar qual é o utilizador com mais pontos.");
            System.out.println(" Pressione 6 para verificar qual é o género mais ouvido.");
            System.out.println(" Pressione 7 para verificar quantas playlists públicas existem.");
            System.out.println(" Pressione 8 para verificar qual é o utilizador com mais playlists.");
            System.out.println(" Pressione 9 para voltar para trás.");

            int opcao = getOpcao(sc, 1, 9);
            LocalDate dataInicio = null;
            LocalDate dataFim = null;
            String st;

            // Pedir as datas para a q3 onde se pode escolher o intervalo
            if (opcao == 4) {
                System.out.print("Introduza a data de início (YYYY-MM-DD): ");
                dataInicio = LocalDate.parse(sc.nextLine());
                System.out.print("Introduza a data de fim (YYYY-MM-DD): ");
                dataFim = LocalDate.parse(sc.nextLine());
            }
            switch (opcao) {
                case 1:
                    st = controller.query1();
                    System.out.println(st);
                    break;
                case 2:
                case 6:
                    st = controller.query2e6(opcao);
                    System.out.println(st);
                    break;
                case 3:
                case 5:
                case 8:
                    st = controller.query345e8(opcao, null, null);
                    System.out.println(st);
                    break;
                case 4:
                    st = controller.query345e8(opcao, dataInicio, dataFim);
                    System.out.println(st);
                    break;
                case 7:
                    int num = controller.query7();
                    System.out.println("\n Existem " + num + " playlists públicas");
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }

    }

    private void recomendadorMenu(Scanner sc, Controller controller, String username) {
        System.out.println("Sê bem vindo/a ao teu recomendador personalizado do SpotifUM!");
        System.out.println("\nPressiona 1 para obteres uma playlist personalizada com as tuas músicas favoritas!");
        System.out.println("\nPressiona 2 caso queiras apenas ouvir música explícita ;-)");

        int opcao = getOpcao(sc, 1, 2);
        int segundos = 0;
        String generos = "";
        int ngeneros = 0;

        if (opcao == 1 || opcao == 2) {
            System.out.println("\nCaso queiras que a tua playlist tenha um tempo máximo, insere-o (em segundos):");
            System.out.println("Caso contrário, insere 0 (zero).");

            // Ler segundos
            while (true) {
                try {
                    segundos = Integer.parseInt(sc.nextLine().trim());
                    if (segundos < 0) {
                        System.out.println("Por favor, insere um número positivo ou 0:");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Tenta novamente:");
                }
            }

            System.out.println(
                    "\nCaso queiras que a tua playlist inclua apenas certos géneros, insere-os separados por espaços:");
            System.out.println("Exemplo: rock pop jazz");
            System.out.println("Se não quiseres restringir géneros, pressiona Enter.");

            generos = sc.nextLine().trim();
            if (!generos.isEmpty()) {
                // Contar géneros com base nos espaços
                ngeneros = generos.split("\\s+").length;
            }

            // Gerar a playlist
            String playlistName = controller.gerarPlaylistRecomendada(username, opcao, ngeneros, generos, segundos);

            // Mostrar resultado
            System.out.println("\nPlaylist gerada com sucesso!");
            System.out.println("Nome: " + playlistName);

        } else {
            System.out.println("Opção inválida. A voltar ao menu principal...");
        }
    }

}
