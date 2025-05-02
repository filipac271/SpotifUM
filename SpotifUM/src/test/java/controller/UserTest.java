package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PlanoSubscricao.PlanoFree;
import PlanoSubscricao.PlanoSubscricao;
import User.User;

public class UserTest {
    
    @Test
    public void userTest()
    {
        PlanoSubscricao plano= new PlanoFree();
        //nome, username, password, email, morada, pontos, plano
        User user= new User("Joao","JotaJota", "password123", "joao@gmail.com", "rua do Faial",plano);

        assertEquals("Joao", user.getNome());
        assertEquals("JotaJota", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("joao@gmail.com", user.getEmail());
        assertEquals("rua do Faial", user.getMorada());
        assertEquals(plano, user.getPlano());
    }
}
