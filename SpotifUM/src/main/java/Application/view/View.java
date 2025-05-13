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


    public void print(String s)
    {
        System.out.println(s);
    }


    public int getOpcao(Scanner sc, int min, int max) {
        int option = -1;
        while (option == -1) {
            try {
                option = sc.nextInt();
                sc.nextLine();
                if(option<min || option>max)
                {
                    option=-1;
                    System.out.println("Insira um número entre "+min+" e "+max+"!");
                }
            } catch (Exception e) {
                optionError();
                sc.nextLine();
            }
        }
        
        return option;
    }

    public String getOpcaoString(Scanner sc) {
        String option ="erro";
        while (option.equals("erro")) {
            try {
                option = sc.nextLine();
            } catch (Exception e) {
                optionError();
                option =sc.nextLine();
            }
        }
        return option;
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            int option;
            mainMenu();
            option = getOpcao(sc,1,4);

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

        System.out.println("Nome: " + nome + "\nUsername: " + username + "\nEmail: " + email + "\nMorada: " + morada);

    }

    public String criarMusica(Scanner sc)
    {
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

            controller.addSong(nomeMusica, interprete, editora, letra, pauta, genero, duracao);

            return nomeMusica;
    }

    public void createAlbumMenu(Scanner sc) {

        System.out.println("\n-------Criar um album-------");

        System.out.println("Digite o número de musicas do album: ");
        int numMusicas = getOpcao(sc,1,Integer.MAX_VALUE);
        System.out.println("Digite o nome do Album: ");
        String nome = getOpcaoString(sc);
        System.out.println("Digite o artista do Album: ");
        String artista = getOpcaoString(sc);
        controller.addAlbum(nome, artista, null);
        String nomeMusica;
        for (int i = 0; i < numMusicas; i++) {

            System.out.println("Música " + (i + 1) + ":");
            nomeMusica=criarMusica(sc);
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

    // Apresenta as opções de ações que um user Premium tem acesso
    public void opcoesPremiumMenu(String tipo)
    {
        System.out.println("\nPressione 1 para ouvir");
        System.out.println("\nPressione 2 para adicionar uma playlist à sua biblioteca");
        System.out.println("\nPressione 3 para adicionar um album à sua biblioteca");
        System.out.println("\nPressione 4 para criar uma playlist");
        
        if(tipo.equals("B"))
        {   
            System.out.println("\nPressione 5 para sair");
        }
        else if (tipo.equals("T"))
        {
            System.out.println("\nPressione 5 para gerar uma playlist");
            System.out.println("\nPressione 6 para sair");
        }
    }

    // Menu para adicionar uma playlist ou álbum à biblioteca de um user
    public void adicionarPlaylistAlbum(String username, String tipo, Scanner sc)
    {
        System.out.println("\nQual "+ tipo +" deseja adicionar?");
        String nome = getOpcaoString(sc);

        if(tipo.equals("album"))
        {
            controller.guardarAlbum(username, nome);
        }
        else if(tipo.equals("playlist"))
        {
            controller.guardarPlaylist(username, nome);
        }
        
    }

    // Apresenta as opções que um user com o plamo Premium Base tem acesso
    public void createUserPremiumBaseMenu(Scanner sc, Controller controller, String username) {
     
        while (true) {

            opcoesPremiumMenu("B");
            int opcao = getOpcao(sc,1,5);

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

    // Apresenta o menu de audição de uma música/playlist/álbum 
    public void createOuvirMenu(Scanner sc, Controller controller, String username) {
        System.out.println("\nPressione 1 para ouvir música");
        System.out.println("\nPressione 2 para ouvir uma playlist");
        System.out.println("\nPressione 3 para ouvir um album");

        while (true) {
            int opcao = getOpcao(sc,1,4);
            String nome;
            int aleatorio;
            switch (opcao) {
                case 1:
                    nome = menuOuvir(sc, "musica",username);
                    break;
                case 2:
                    nome = menuOuvir(sc, "playlist",username);
                    System.out.println("\n Reproduzir aleatoriamente? \n   1-Sim  2-Não)");
                    aleatorio=getOpcao(sc,1,2);
                    if(aleatorio==1)
                    {
                        controller.reproduzirPlaylistAleatoriamente(username,nome, this,sc);
                    }
                    else if(aleatorio==2)
                    {
                        controller.reproduzirPlaylistSequencial(username,nome,this,sc);
                       
                    }
                    
                    break;
                case 3:
                    nome = menuOuvir(sc, "album",username);
                    System.out.println("\nReproduzir aleatoriamente? \n 1-Sim  2-Não");
                    aleatorio=getOpcao(sc,1,2);
                    if(aleatorio==1)
                    {
                        controller.reproduzirAlbumAleatoriamente(username,nome, this,sc);
                    }
                    else if(aleatorio==2)
                    {
                        controller.reproduzirAlbumSequencial(username,nome,this,sc);
                       
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

    //  Pergunta ao user que música/playlist/album quer ouvir e valida o nome dado
    public String menuOuvir(Scanner sc, String tipo,String username) {
         System.out.println("\nQual " + tipo + " deseja ouvir?");
         String nome = sc.nextLine();
         while (!this.controller.validaAudicao(username, tipo, nome))
         {
            System.out.println( tipo + "\n não existe");
            System.out.println("\nQual " + tipo + " deseja ouvir?");
            nome = sc.nextLine();
         }
         
        return nome;
    }

    // Apresenta ao user (Premium) as opções de escolha de uma música ao ouvir uma playlist
    public int perguntarContinuar(Scanner sc) {
        System.out.println("\n1 - Avançar Música ");
        System.out.println("\n2 - Próxima Música");
        System.out.println("\n3 - Retroceder ");
        System.out.println("\n4 - Sair");

        int opcao = getOpcao(sc,1,4);
        return opcao;
    }
    
    // Apresenta o menu da opcao criar playlist
    public void criarPlaylistMenu(Scanner sc, String username) {
        System.out.println("\n-------Criar uma playlist-------\n");
        System.out.println("Nome da Playlist:");
        String nomeP = getOpcaoString(sc);
        System.out.println("Tornar playlist pública? (s/n)");
        String publica = getOpcaoString(sc);
        System.out.println("Quantas músicas pretende adicionar:");
        int nMusicas = getOpcao(sc,1,Integer.MAX_VALUE);
        controller.createPlaylist(username, nomeP, publica);
        String nomeM;
        int i,v;
        for ( i = 0; i < nMusicas; i++) {
            System.out.println("Nome da Música Nº: " + (i + 1));
            nomeM = getOpcaoString(sc);
            v=controller.addToPlaylist(nomeP, nomeM);
            if(v==0){
                    System.out.println("Música não existe!");
                    i--;
                   }
        }
        ordenarPlaylist(nomeP,nMusicas,sc);
        controller.addPlaylistToUser(nomeP, username);
    }

    // Pergunta ao user como quer ordenar a sua Playlist
    public void ordenarPlaylist(String nomeP, int nMusicas, Scanner sc)
    {
        System.out.println("De que forma quer ordenar a playlist ?");
        System.out.println("1- Nome da música  a-z");
        System.out.println("2- Nome da música z-a");
        System.out.println("3- Nome do interprete a-z");
        System.out.println("4- Nome do interprete z-a");
        System.out.println("5- Quero decidir uma ordem especifica");
        int op=getOpcao(sc,1,5);
      
        int index;
        if(op==5)
        {
            imprimePlaylist(nomeP,nMusicas);
            for( int i=0;i<nMusicas;i++)
            {
                System.out.println("Qual a "+i+"ª música?");
                index=getOpcao(sc,1,nMusicas);
                controller.trocaMusicas(nomeP,i,index-1);
            }
        }
        else
        {
           controller.ordenarPlaylist(nomeP,op); 
        }

    }

    // Imprime o nome das músicas que constituem uma playlist
    public void imprimePlaylist(String nomeP, int n)
    {
        System.out.println("Ordem das músicas atual:");
        int i=1;
       for(String s : this.controller.getNomeMusicas(nomeP, n) )
       {
            System.out.println("\nNº "+i+": "+s);
            i++;
        
       }
    }

    // Apresenta o menu do user que tem o plano premium top
    public void createuserPTMenu(Scanner sc, Controller controller, String username) {

        while (true) {
            opcoesPremiumMenu("T");
            int opcao = getOpcao(sc,1,6);

            switch (opcao) {
                case 1:
                    createOuvirMenu(sc, controller, username);
                    break;
                case 2:
                    adicionarPlaylistAlbum(username,"playlist",sc);
                    break;
                case 3:
                    adicionarPlaylistAlbum(username, "album", sc);
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

            if (opcao == 6)
                break;
        }

    }

}
