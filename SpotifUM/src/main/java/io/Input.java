package io;
import java.util.Scanner;

import Songs.Song;

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
        System.out.println("1. Iniciar Sessão");
        System.out.println("2. Criar conta");
        System.out.println("3. Criar Álbum");
        System.out.println("4. Fechar o programa");
        System.out.println("Prima o número correspondente à opção que deseja executar:");
    }
    
    public void createUserMenu (){

        Scanner sc = new Scanner(System.in);


        System.out.println("Digite o seu Nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite o seu Email: ");
        String email = sc.nextLine(); 
        System.out.println("Digite a sua Morada: ");
        String morada = sc.nextLine(); 

        
        //Criar user


        System.out.println("A sua conta foi criada com sucesso "+ nome);

        System.out.println("Nome: " + nome + "  Email: " + email + "  Morada: " + morada);




    }

  
}