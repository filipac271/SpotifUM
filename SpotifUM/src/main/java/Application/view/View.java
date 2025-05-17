package Application.view;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

import Application.controller.Controller;

/**
 * Classe responsável pela interface de interação com o utilizador (CLI),
 * apresentando menus e recebendo inputs para acionar funcionalidades da aplicação.
 */
public class View {

    private Controller controller;

    /**
     * Construtor da View, inicializa com a instância do Controller.
     * 
     * @param controller Instância do Controller que controla a lógica da aplicação.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Exibe o menu principal da aplicação com as opções disponíveis.
     */
    private void mainMenu() {
        System.out.println("\n   BEM VINDO/A AO SPOTIFUM ! ");
        System.out.println(" _____________________________");
        System.out.println();
        System.out.println(" 1. Iniciar Sessão");
        System.out.println(" 2. Criar conta");
        System.out.println(" 3. Criar ou Modificar Álbum");
        System.out.println(" 4. Estatísticas da App");
        System.out.println(" 5. Mudar de plano");
        System.out.println(" 6. Fechar o programa");
        System.out.println(" Prima o número correspondente à opção que deseja executar:");
    }

    /**
     * Lê e valida a opção numérica introduzida pelo utilizador dentro do intervalo permitido.
     * Continua a pedir até receber um número válido.
     * 
     * @param sc Scanner para leitura da entrada.
     * @param min Valor mínimo válido.
     * @param max Valor máximo válido.
     * @return A opção escolhida pelo utilizador.
     */
    private int getOpcao(Scanner sc, int min, int max) {
        int option = -1;
        while (option == -1) {
            try {
                option = sc.nextInt();
                sc.nextLine(); // Consome o newline
                if (option < min || option > max) {
                    option = -1;
                    System.out.println(" Insira um número entre " + min + " e " + max + "!");
                }
            } catch (Exception e) {
                optionError();
                sc.nextLine(); // Limpa buffer em caso de erro
            }
        }
        return option;
    }

    /**
     * Lê uma linha de texto introduzida pelo utilizador.
     * Repete a leitura em caso de erro.
     * 
     * @param sc Scanner para leitura da entrada.
     * @return A string introduzida pelo utilizador.
     */
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

    /**
     * Executa o loop principal da aplicação, apresentando o menu principal e
     * tratando as opções escolhidas pelo utilizador até que decida sair.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int option;
            mainMenu();
            option = getOpcao(sc, 1, 6);

            switch (option) {
                case 1:
                    logInUserMenu(sc);
                    break;
                case 2:
                    createUserMenu(sc);
                    break;
                case 3:
                    createModifyAlbumMenu(sc);
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

    /**
     * Apresenta o menu para o utilizador iniciar sessão, solicitando username e password,
     * validando as credenciais e encaminhando para o menu do utilizador se autenticação for bem sucedida.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     */
    private void logInUserMenu(Scanner sc) {
        System.out.println(" A iniciar sessao... ");
        System.out.println(" Digite o seu Username: ");
        String username = getOpcaoString(sc);

        while (!(controller.userExists(username)) && !(username.isEmpty())) {
            System.out.println(" Utilizador não existe!");
            System.out.println(" Insira um nome de utilizador existente, se quiser sair prima Enter");
            username=getOpcaoString(sc);
        }
        if(! (username.isEmpty()) ) {

            System.out.println(" Digite a sua Password: ");
            String password = getOpcaoString(sc);
            while(!controller.authenticUser(username, password) && !password.isEmpty()) {
                System.out.println(" Password está errada! Insira novamente ou prima Enter para sair");
                password=getOpcaoString(sc);
            }
           if(!password.isEmpty()) {
                System.out.println(" Bem vindo de volta " + username);
                userMenu(sc, username);
            }
        }
    }

    /**
     * Apresenta o menu para o utilizador alterar o seu plano de subscrição.
     * Solicita o username, verifica existência, e permite a escolha do novo plano.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     */
    private void changePlanMenu(Scanner sc) {
        System.out.print(" Introduz o teu username: ");
        String username = getOpcaoString(sc);

        if (!(controller.userExists(username))) {
            System.out.println(" Utilizador não encontrado.");
            return;
        }

        System.out.print(" Introduz a password: ");
        String password=getOpcaoString(sc);
        while (!(controller.authenticUser(username, password)) && !(password.isEmpty()))
        {
            System.out.println(" Password está errada! Tente novamente ou prima Enter para sair");
            password=getOpcaoString(sc);
        }

        if(!password.isEmpty())
        {
            int option = createPlanoMenu(sc);

        String novoPlano = controller.changeUserPlan(username, option);
        if (!novoPlano.equals("")) {
            System.out.println(" Plano alterado com sucesso para " + novoPlano + ".");
        } else {
            System.out.println(" O plano escolhido é o mesmo que já está assinado.");
        }
    }
    }

    /**
     * Exibe um menu para o utilizador escolher um plano de subscrição.
     * 
     * @param sc Scanner para leitura da opção escolhida.
     * @return Número correspondente ao plano selecionado.
     */
    private int createPlanoMenu(Scanner sc) {
        System.out.println("\n Escolha o seu plano:");
        System.out.println(" 1.Plano Free");
        System.out.println(" 2.Plano Premium Base");
        System.out.println(" 3.Plano Premium Top");
        int option = getOpcao(sc, 1, 3);
        return option;
    }

    /**
     * Apresenta o menu para criar uma nova conta de utilizador, pedindo dados necessários,
     * validando unicidade do username e enviando os dados para o controller.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     */
    private void createUserMenu(Scanner sc) {
        System.out.println(" Digite o seu Nome: ");
        String nome = getOpcaoString(sc);
        System.out.println(" Crie um username único: ");
        String username = getOpcaoString(sc);

        while (controller.userExists(username) && !username.isEmpty()) {
            System.out.println(" Este username já existe! Escolha outro:  (Se quiser sair prima Enter) ");
            username = getOpcaoString(sc);
        }
        if (username.isEmpty()) return;
         
        System.out.println(" Crie uma password: ");
        String password = getOpcaoString(sc);
        System.out.println(" Digite o seu Email: ");
        String email = getOpcaoString(sc);
        System.out.println(" Digite a sua Morada: ");
        String morada = getOpcaoString(sc);
        System.out.println(" Digite a sua Idade: ");
        int age = getOpcao(sc, 0, Integer.MAX_VALUE);
        

        int planoOption = createPlanoMenu(sc);
        controller.addUser(nome, username, password, email, morada, age, planoOption);

        System.out.println(" A sua conta foi criada com sucesso " + nome);
        System.out.println(" Nome: " + nome + 
                         "\n Username: " + username + 
                         "\n Email: " + email +
                         "\n Morada: " + morada + 
                         "\n Idade: " + 
                         "\n Plano: "+controller.getPlanoByUser(username));
    }


        /**
     * Lê os dados necessários para criar uma nova música, perguntando ao utilizador os detalhes
     * e depois adiciona a música através do controller.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     * @return O nome da música criada.
     */
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
        int duracao = getOpcao(sc, 1, Integer.MAX_VALUE);

        System.out.print(" A musica é explicita?  1- SIM  2- NÃO ");
        int isExplicit = getOpcao(sc,1,2);

        System.out.print(" A musica têm video?  1-SIM  2-NÃO ");
        int isMedia = getOpcao(sc,1,2);
        String url = "";
        if (isMedia==1) {
            System.out.print(" Digite o url do video da musica: ");
            url = getOpcaoString(sc);
        }

        controller.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao, isExplicit, isMedia, url);

        return nomeMusica;
    }

    private int AlbumMenu(Scanner sc)
    {
        System.out.println("\n-------Album-------");
        System.out.println("Prima 1 para criar um Album");
        System.out.println("Prima 2 para modificar um Album já existente");
        System.out.println("Prima 3 para sair");
        int opcao=getOpcao(sc, 1, 3);
        return opcao;
    }
    /**
     * Apresenta o menu para criação de um álbum, perguntando ao utilizador o nome, artista e número de músicas.
     * Para cada música, invoca o método criarMusica para recolher os dados e adiciona a música ao álbum.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     */
    private void createModifyAlbumMenu(Scanner sc) {
        int opcao=AlbumMenu(sc);
    
        if(opcao==1)
        {
            createAlbumMenu(sc);
        
        }
        else if (opcao==2)
        {
            System.out.println(" Prima 1 para remover uma música.");
            System.out.println(" Prima 2 para adicionar uma música.");
            System.out.println(" Prima 3 para sair.");
            int acao=getOpcao(sc, 1, 3);
            while(acao!=3)
            {
                modifyAlbum(sc, acao);
            }
        }
        
    }

    private void createAlbumMenu(Scanner sc)
    {
        System.out.println(" Digite o número de musicas do album: ");
        int numMusicas = getOpcao(sc, 1, Integer.MAX_VALUE);
        System.out.println(" Digite o nome do Album: ");
        String nome = getOpcaoString(sc);
        System.out.println(" Digite o artista do Album: ");
        String artista = getOpcaoString(sc);
        controller.addAlbum(nome, artista);
        String nomeMusica;

        for (int i = 0; i < numMusicas; i++) {
            System.out.println(" Música " + (i + 1) + ":");
            nomeMusica = criarMusica(sc);
            boolean v = controller.addToAlbum(nome, nomeMusica);
            if (!v ) {
                System.out.println(" Não foi possivel adicionar ao album!");
                i--;
            }
         }

         System.out.println(" O album " + nome + " de " + artista + " foi criado tendo um total de " + numMusicas + " músicas.");

    }

    public void modifyAlbum(Scanner sc, int acao)
    {
            System.out.println(" Digite o nome do Album: ");
            String nomeAlbum = getOpcaoString(sc);
            if(!controller.albumExists(nomeAlbum))
            {
                System.out.println("Album Não Existe! "); 
                return;
            }
            if(acao==1)
            {
                imprimePlaylistAlbum(nomeAlbum,"album");
                System.out.println(" Qual o número da música que deseja remover : ");
                int nMusicas=controller.nMusicas(nomeAlbum);
                int opcao=getOpcao(sc, 1,nMusicas );
                controller.removeMusicaAlbum(nomeAlbum, opcao);
            }
            else if (acao==2)
            {
               String nomeMusica=criarMusica(sc);
               boolean v = controller.addToAlbum(nomeAlbum, nomeMusica);
               if(!v)
               {
                    System.out.println("Não foi possível adicionar a música!" );
               }
            }
    }

    /**
     * Método simples que exibe mensagem de saída da aplicação.
     */
    private void out() {
        System.out.println(" A sair...");
    }

    /**
     * Método para mostrar mensagem de erro quando a opção inserida for inválida.
     */
    private void optionError() {
        System.out.println(" Entrada inválida. Tente novamente.");
    }

    /**
     * Apresenta o menu do utilizador baseado no tipo de plano subscrito.
     * Encaminha para os menus específicos conforme o plano.
     * 
     * @param sc Scanner para leitura de inputs do utilizador.
     * @param username Username do utilizador autenticado.
     */
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


    /**
     * Menu para utilizadores com plano Free.
     * Apresenta opções limitadas para ouvir música ou sair.
     *
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param controller Controlador da aplicação.
     * @param username Nome do utilizador autenticado.
     */
    private void createUserFreeMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\n Temos as melhores músicas para ouvir!");
        System.out.println("\n Pressione 1 para ouvir música");
        System.out.println("\n Pressione 2 para sair");
        System.out.println(" Para mais opções dê upgrade do plano!");

        int opcao = getOpcao(sc, 1, 2);

        if (opcao==1) {
            String nomePlaylist = controller.createPlaylistRandom();
            reproduzirSequencial(sc, username, nomePlaylist, true);
        } 
        else if (opcao==2) {
            out();
        } 
    }

    /**
     * Exibe as opções do menu para utilizadores Premium.
     *
     * @param tipo Tipo do plano premium ("B" para Base, "T" para Top).
     */
    private void opcoesPremiumMenu(String tipo) {
        System.out.println("\n||||||||||||||||||||||||||||||||||");
        System.out.println("\n Pressione 1 para ouvir");
        System.out.println("\n Pressione 2 para adicionar uma playlist à sua biblioteca");
        System.out.println("\n Pressione 3 para adicionar um album à sua biblioteca");
        System.out.println("\n Pressione 4 para criar uma playlist");

        if (tipo.equals("B")) {
            System.out.println("\n Pressione 5 para sair");
        } else if (tipo.equals("T")) {
            System.out.println("\n Pressione 5 para gerar uma playlist");
            System.out.println("\n Pressione 6 para sair");
        }
    }

    /**
     * Permite ao utilizador adicionar uma playlist ou álbum à sua biblioteca.
     *
     * @param username Nome do utilizador.
     * @param tipo Tipo de item a adicionar ("album" ou "playlist").
     * @param sc Scanner para leitura da entrada do utilizador.
     */
    private void adicionarPlaylistAlbum(String username, String tipo, Scanner sc) {
        System.out.println("\n Qual " + tipo + " deseja adicionar? (Pressione Enter caso queira voltar atrás)");
        String nome = getOpcaoString(sc);
        if (nome.isEmpty())
            return;
        if (tipo.equals("album")) {
            controller.guardarAlbum(username, nome);
        } else if (tipo.equals("playlist")) {
            controller.guardarPlaylist(username, nome);
        }
    }

    /**
     * Menu principal para utilizadores com plano Premium Base.
     * Apresenta várias opções incluindo ouvir música, adicionar playlists/albums e criar playlists.
     *
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param controller Controlador da aplicação.
     * @param username Nome do utilizador autenticado.
     */
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

    /**
     * Reproduz músicas de uma playlist ou álbum em sequência.
     *
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param username Nome do utilizador.
     * @param nome Nome da playlist ou álbum.
     * @param serPlaylist Indica se o nome corresponde a uma playlist (true) ou álbum (false).
     */
    private void reproduzirSequencial(Scanner sc, String username, String nome, boolean serPlaylist) {
        int[] index = new int[]{0};
        while (index[0] != -2) {
            String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome, index, serPlaylist);
            System.out.println(letraIndex);
            int select = perguntarContinuar(sc);
            index[0] = controller.proximaMusica(sc, index[0], select);
        }
    }

    /**
     * Reproduz músicas de uma playlist ou álbum numa ordem aleatória.
     *
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param username Nome do utilizador.
     * @param nome Nome da playlist ou álbum.
     * @param serPlaylist Indica se o nome corresponde a uma playlist (true) ou álbum (false).
     */
    private void reproduzirAleatorio(Scanner sc, String username, String nome, boolean serPlaylist) {
        List<Integer> ordem = controller.shufflePlaylist_album(nome, serPlaylist);
        int[] index = new int[]{0};
        int[] ordemIndex = new int[]{ordem.get(0)};
        while (index[0] != -2) {
            if (index[0] < 0 || index[0] >= ordem.size()) {
                String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome, index, serPlaylist);
                System.out.println(letraIndex);
            } else {
                ordemIndex[0] = ordem.get(index[0]);
                String letraIndex = controller.letraDaMusicaNa_Playlist_Album(username, nome, ordemIndex, serPlaylist);
                System.out.println(letraIndex);
            }
            int select = perguntarContinuar(sc);
            index[0] = controller.proximaMusica(sc, index[0], select);
        }
    }

    /**
     * Apresenta o menu para ouvir música, playlist ou álbum, com opção de reprodução aleatória ou sequencial.
     *
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param controller Controlador da aplicação.
     * @param username Nome do utilizador autenticado.
     */
    private void createOuvirMenu(Scanner sc, Controller controller, String username) {
        while (true) {
            System.out.println("\n||||||||||||||||||||||||||||||||||");
            System.out.println("\n Pressione 1 para ouvir música");
            System.out.println("\n Pressione 2 para ouvir uma playlist");
            System.out.println("\n Pressione 3 para ouvir um album");
            System.out.println("\n Pressione 4 para voltar atrás");
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
                    System.out.println("\n Reproduzir aleatoriamente? \n   1-Sim  2-Não");
                    aleatorio = getOpcao(sc, 1, 2);
                    if (aleatorio == 1) {
                        reproduzirAleatorio(sc, username, nome, true);
                    } else if (aleatorio == 2) {
                        reproduzirSequencial(sc, username, nome, true);
                    }
                    break;
                case 3:
                    nome = menuOuvir(sc, "album", username);
                    if (nome.equals(""))
                        break;
                    System.out.println("\n Reproduzir aleatoriamente? \n 1-Sim  2-Não");
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
    
    
        /**
     * Pergunta ao utilizador qual música, playlist ou álbum deseja ouvir e valida o nome dado.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param tipo Tipo do conteúdo a ouvir ("musica", "playlist" ou "album").
     * @param username Nome do utilizador autenticado.
     * @return Nome válido do conteúdo ou string vazia caso o utilizador queira voltar atrás.
     */
    private String menuOuvir(Scanner sc, String tipo, String username) {
        System.out.println("\n Qual " + tipo + " deseja ouvir? (Pressione Enter caso queira voltar atrás)");
        String nome = getOpcaoString(sc);
        if (nome.isEmpty())
            return "";
        while (!this.controller.validaAudicao(username, tipo, nome)) {
            System.out.println(tipo + " não existe");
            System.out.println(" Qual " + tipo + " deseja ouvir? (Pressione Enter caso queira voltar atrás)");
            nome = getOpcaoString(sc);
            if (nome.isEmpty())
                return "";
        }
        return nome;
    }
    
    /**
     * Apresenta as opções para o utilizador Premium controlar a reprodução da música.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     * @return Inteiro correspondente à opção escolhida.
     */
    private int perguntarContinuar(Scanner sc) {
        System.out.println("\n 1 - Avançar Música ");
        System.out.println(" 2 - Próxima Música");
        System.out.println(" 3 - Retroceder ");
        System.out.println(" 4 - Recomeçar ");
        System.out.println(" 5 - Sair");
        int opcao = getOpcao(sc, 1, 5);
        return opcao;
    }
    
    /**
     * Menu para criar uma nova playlist, incluindo opções de nome, privacidade, número de músicas,
     * limite de tempo e género.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param username Nome do utilizador autenticado.
     */
    private void criarPlaylistMenu(Scanner sc, String username) {
        System.out.println("\n-------Criar uma playlist-------\n");
        System.out.println(" Nome da Playlist:");
        String nomeP = getOpcaoString(sc);
        System.out.println(" Tornar playlist pública? 1-SIM 2-NÃO");
        int publica = getOpcao(sc,1,2);
        System.out.println(" Quantas músicas pretende adicionar:");
        int nMusicas = getOpcao(sc, 1, Integer.MAX_VALUE);
        System.out.println(" Quer limite máximo de tempo e um género específico na playlist? 1-SIM 2-NÃO");
        int limiteTempoGenero = getOpcao(sc,1,2);
        int limiteTempo = 0;
        String genero = "";
        if (limiteTempoGenero==1) {
            System.out.println(" Limite máximo de tempo:");
            limiteTempo = getOpcao(sc, 1, Integer.MAX_VALUE);
            System.out.println(" Género da Playlist:");
            genero = getOpcaoString(sc);
        }
        controller.addPlaylist(nomeP, publica, limiteTempoGenero, genero, limiteTempo);
        String nomeM;
        int i, v;
        for (i = 0; i < nMusicas; i++) {
            System.out.println(" Nome da Música Nº: " + (i + 1));
            nomeM = getOpcaoString(sc);
            if(nomeM.isEmpty())
            {
                System.out.println("A sair ...");
                return;
            }
            v = controller.addToPlaylist(nomeP, nomeM);
            if (v == 0) {
                System.out.println(" Música não existe!");
                i--;
            }
        }
        ordenarPlaylist(nomeP, nMusicas, sc);
        controller.addPlaylistToUser(nomeP, username);
    }
    
    /**
     * Pergunta ao utilizador como deseja ordenar a sua playlist.
     * Permite ordenação por nome da música, nome do intérprete ou ordem específica.
     * 
     * @param nomeP Nome da playlist a ordenar.
     * @param nMusicas Número de músicas na playlist.
     * @param sc Scanner para leitura da entrada do utilizador.
     */
    private void ordenarPlaylist(String nomeP, int nMusicas, Scanner sc) {
        System.out.println(" De que forma quer ordenar a playlist ?");
        System.out.println(" 1- Nome da música  a-z");
        System.out.println(" 2- Nome da música z-a");
        System.out.println(" 3- Nome do interprete a-z");
        System.out.println(" 4- Nome do interprete z-a");
        System.out.println(" 5- Quero decidir uma ordem especifica");
        int op = getOpcao(sc, 1, 5);
    
        int index;
        if (op == 5) {
            imprimePlaylistAlbum(nomeP, "playlist");
            for (int i = 0; i < nMusicas; i++) {
                System.out.println(" Qual a " + i + "ª música?");
                index = getOpcao(sc, 1, nMusicas);
                controller.trocaMusicas(nomeP, i, index - 1);
            }
        } else {
            controller.ordenarPlaylist(nomeP, op);
        }
    }
    
    /**
     * Imprime a lista das músicas que constituem uma playlist.
     * 
     * @param nomeP Nome da playlist.
     * @param n Número de músicas na playlist.
     */
    private void imprimePlaylistAlbum(String nome, String tipo) {
        System.out.println(" Todas as músicas d"+(tipo.equals("album") ? "a " : "o ")+tipo+" : ");
        int i = 1;
        for (String s : this.controller.getNomeMusicas(nome, tipo)) {
            System.out.println(" Nº " + i + ": " + s);
            i++;
        }
    }
    
    /**
     * Menu para utilizadores com plano Premium Top.
     * Apresenta várias opções incluindo ouvir música, adicionar playlists/albums, criar playlists,
     * usar recomendador e sair.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param controller Controlador da aplicação.
     * @param username Nome do utilizador autenticado.
     */
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
    
    /**
     * Menu que apresenta várias queries e estatísticas sobre músicas, utilizadores e playlists.
     * Permite o utilizador consultar diferentes informações do sistema.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     */
    private void checkQueriesMenu(Scanner sc) {
        System.out.println("Sê bem-vindo/a às estatísticas do SpotifUM!");
        while (true) {
            System.out.println("\n Pressione 1 para ver qual é a música mais reproduzida.");
            System.out.println(" Pressione 2 para verificar qual é o intérprete mais ouvido.");
            System.out.println(" Pressione 3 para verificar qual é o utilizador que mais ouviu músicas desde sempre.");
            System.out.println(" Pressione 4 para verificar qual é o utilizador que mais músicas ouviu por um determinado período de tempo.");
            System.out.println(" Pressione 5 para verificar qual é o utilizador com mais pontos.");
            System.out.println(" Pressione 6 para verificar qual é o género mais ouvido.");
            System.out.println(" Pressione 7 para verificar quantas playlists públicas existem.");
            System.out.println(" Pressione 8 para verificar qual é o utilizador com mais playlists.");
            System.out.println(" Pressione 9 para voltar para trás.");
        
            int opcao = getOpcao(sc, 1, 9);
            LocalDate dataInicio = null;
            LocalDate dataFim = null;
            String st;
        
            if (opcao == 4) {
                System.out.print(" Introduza a data de início (YYYY-MM-DD): ");
                dataInicio = LocalDate.parse(getOpcaoString(sc));
                System.out.print(" Introduza a data de fim (YYYY-MM-DD): ");
                dataFim = LocalDate.parse(getOpcaoString(sc));
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
                    System.out.println(" Opção inválida.");
                    break;
            }
        }
    }
    
    /**
     * Menu do recomendador personalizado, que permite ao utilizador gerar uma playlist
     * baseada nas suas preferências ou ouvir música explícita.
     * 
     * @param sc Scanner para leitura da entrada do utilizador.
     * @param controller Controlador da aplicação.
     * @param username Nome do utilizador autenticado.
     */
    private void recomendadorMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\n Sê bem vindo/a ao teu recomendador personalizado do SpotifUM!");
        System.out.println(" Pressiona 1 para obteres uma playlist personalizada com as tuas músicas favoritas!");
        System.out.println(" Pressiona 2 caso queiras apenas ouvir música explícita ;-)");
        System.out.println(" Pressione 3 para sair");

        int opcao = getOpcao(sc, 1, 3);
        int segundos = 0;
        String generos = "";
        int ngeneros = 0;
    
        if (opcao == 1 || opcao == 2) {
            System.out.println(" Caso queiras que a tua playlist tenha um tempo máximo, insere-o (em segundos):");
            System.out.println(" Caso contrário, insere 0 (zero).");
        
            segundos =getOpcao(sc, 0, Integer.MAX_VALUE);
        
            System.out.println("\nCaso queiras que a tua playlist inclua apenas certos géneros, insere-os separados por espaços:");
            System.out.println(" Exemplo: rock pop jazz");
            System.out.println(" Se não quiseres restringir géneros, pressiona Enter.");
        
            generos = getOpcaoString(sc);
            if (!generos.isEmpty()) {
                ngeneros = generos.split("\\s+").length;
            }
        
            String playlistName = controller.gerarPlaylistRecomendada(username, opcao, ngeneros, generos, segundos);
        
            System.out.println("\nPlaylist gerada com sucesso!");
            System.out.println(" Nome: " + playlistName);
        
        } else {
            System.out.println(" A voltar ao menu principal...");
        }
    }
    

}
