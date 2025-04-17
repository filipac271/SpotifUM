package controller;

import java.util.HashMap;
import java.util.Map;
import User.User;

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
    public void addUser(String username, User user) {
        userTable.put(username, user);
    }

    // Remove um utilizador pelo username
    public boolean removeUser(String username) {
        if (userTable.containsKey(username)) {
            userTable.remove(username);
            return true;
        }
        return false;
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
}
