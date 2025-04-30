package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PlanoSubscricao.PlanoSubscricao;
import Song.Song;
import controller.UserController;
public class Input {

   
    // Método para reproduzir a música (imprimir a letra no terminal)
    public void playSong(Song song) {
        System.out.println("Reproduzindo a música: " + song.getNome());
        System.out.println("Intérprete: " + song.getInterprete());
        System.out.println("Editora: " + song.getEditora());
        System.out.println("Gênero: " + song.getGenero());
        System.out.println("Duração: " + song.getDuracao() + " segundos");
        System.out.println("\nLetra da música:");
        System.out.println(song.getLetra()); 
    }



    public void mainMenu(){
        System.out.println("\n1. Iniciar Sessão");
        System.out.println("2. Criar conta");
        System.out.println("3. Criar Álbum");
        System.out.println("4. Fechar o programa");
        System.out.println("Prima o número correspondente à opção que deseja executar:");
    }
    public void logInUserMenu(Scanner sc, UserController userController)
    {
          
        System.out.println("A iniciar sessao");

        System.out.println("Digite o seu Username: ");
        String username = sc.nextLine();
        if( !(userController.userExists(username)))
        {
            System.out.println("Utilizador não existe!");
        }
        else
        {
            System.out.println("Digite a sua Password: ");
            String password=sc.nextLine();
            if(!userController.authenticUser(username, password))
            {
                System.out.println("Password ou nome de Utilizador estão errados!");
                //////////
            }
            else
            {
                System.out.println("Bem vindo de volta "+username);
                //O que se faz ao iniciar sessao
            }
        }

    }
    public int createPlanoMenu(Scanner sc)
    {
        System.out.println("\nEscolha o seu plano:");
        System.out.println("\n1.Plano Free");
        System.out.println("\n2.Plano Premium Base");
        System.out.println("\n3.Plano Premium Top");
        int option=0;
        try {
            option = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            optionError();
            sc.nextLine(); 
        }
        return option;
    }
    public void createUserMenu (Scanner sc, UserController userController){
       
        
        System.out.println("Digite o seu Nome: ");
        String nome = sc.nextLine();
        System.out.println("Crie um username único: ");
        String username= sc.nextLine();
        System.out.println("daksjdasp" + username);
        System.out.println(userController.userExists(username));
        while(userController.userExists(username))
        {
            System.out.println("Este username já existe! Escolha outro: ");
            username= sc.nextLine();
        }

        System.out.println("Crie uma password: ");
        String password= sc.nextLine();
        System.out.println("Digite o seu Email: ");
        String email = sc.nextLine(); 
        System.out.println("Digite a sua Morada: ");
        String morada = sc.nextLine(); 
        
        PlanoSubscricao plano= userController.createPlano(sc);
        userController.addUser(nome,username,password,email,morada,plano);
        
        System.out.println("A sua conta foi criada com sucesso "+ nome);

        System.out.println("Nome: " + nome +"\nUsername"+username+ "\nEmail: " + email + "\nMorada: " + morada+"\nPlano: "+ plano.getNome());

    }

   

    public void createAlbumMenu (Scanner sc){

        System.out.println("Criar um album");
        Persistencia ps = new Persistencia();

        System.out.println("Digite o número de musicas que queres no album: ");
        int numMusicas = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o nome do Album: ");
        String nome = sc.nextLine(); 
        System.out.println("Digite o artista do Album: ");
        String artista = sc.nextLine(); 
        List<Song> album = new ArrayList<>(); 


        for (int i = 0; i < numMusicas; i++) {
            System.out.println("Música " + (i + 1) + ":");
        
            System.out.print("  Digite o nome: ");
            String nomeMusica = sc.nextLine();
        
            System.out.print("  Digite o intérprete: ");
            String interprete = sc.nextLine();
        
            System.out.print("  Digite a editora: ");
            String editora = sc.nextLine();
        
            System.out.print("  Digite a letra: ");
            String letra = sc.nextLine();
        
            System.out.print("  Digite a pauta: ");
            String pauta = sc.nextLine();
        
            System.out.print("  Digite o gênero: ");
            String genero = sc.nextLine();
        
            System.out.print("  Digite a duração (em segundos): ");
            int duracao = sc.nextInt();
            sc.nextLine();
        
            Song musica = new Song(nomeMusica, interprete, editora, letra, pauta, genero, duracao);
            ps.writeLine(musica.toCSVString(),"../../Albuns.csv");
            album.add(musica);
        }
        System.out.println("O album " + nome +" do " + artista + " foi criado tendo um total de " + numMusicas + " músicas.");

    }

    public void out()
    {
        System.out.println("A sair...");
    }

    public void optionError()
    {
        System.out.println("Entrada inválida. Tente novamente.");
    }
  
}