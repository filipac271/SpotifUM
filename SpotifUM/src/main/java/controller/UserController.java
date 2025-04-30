package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import view.Input;
import Song.Song;
import User.User;
import PlanoSubscricao.PlanoFree;
import PlanoSubscricao.PlanoPremiumBase;
import PlanoSubscricao.PlanoPremiumTop;
import PlanoSubscricao.PlanoSubscricao;
public class UserController {
    private Map<String, User> userTable;
   
    public UserController() {
        this.userTable = new HashMap<>();
    }

    // // Adiciona um novo utilizador
    // public void addUser(String username, User user) {
    // userTable.put(username, user.clone());
    // }

    // Adiciona um novo utilizador
    public void addUser(String nome, String username,String password, String email, String morada,PlanoSubscricao plano ) {
        plano= new PlanoFree();
        User user= new User(nome,username,password,email,morada,plano);
        this.userTable.put(username, user);
    }
    
    // Remove um utilizador pelo username
    public boolean removeUser(String username) {
        if (this.userTable.containsKey(username)) {
            this.userTable.remove(username);
            return true;
        }
        return false;
    }

    public boolean authenticUser(String username,String password)
    {
        User user=userTable.get(username) ;

         if ( userTable.get(username) ==null ) return false;

        return username.equals(user.getPassword()) ;

    }

    

    // // Retorna um utilizador pelo username
    // public User getUser(String username) {
    // return userTable.get(username).clone();
    // }

    // Retorna um utilizador pelo username
    public User getUser(String username) {
        return userTable.get(username);
    }

    // Verifica se um utilizador existe
    public boolean userExists(String username) {
        return userTable.containsKey(username);
    }

    public String userMaisPontos()
    {
        String userMaisPontos = null;
        double maiorNumPontos = -1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getPontos() > maiorNumPontos) {
                maiorNumPontos = user.getPontos();
                userMaisPontos = user.getNome();
            }
        }

        return userMaisPontos;
    }

    public User userMaisPlaylists()
    {
        User userMaisPlaylists = null;
        int maisPlaylists=-1;

        for (Map.Entry<String, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (user.getNumPlaylists() > maisPlaylists) {
                maisPlaylists = user.getNumPlaylists();
                userMaisPlaylists = user;
            }
        }

        return userMaisPlaylists;
    }

    public PlanoSubscricao createPlano(Scanner sc)
    {
        Input io=new Input();
        int op=io.createPlanoMenu(sc);
        PlanoSubscricao plano=null;

        switch (op) {
            case 1:
                plano=new PlanoFree();
                break;
            case 2:
                plano=new PlanoPremiumBase();
                break;
            case 3:
                plano=new PlanoPremiumTop();
                break;    
            default:
                break;
        }
        return plano;
        }
}

