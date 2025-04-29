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
        User user= new User("Joao", "joao@gmail.com", "rua do Faial",plano);

        assertEquals("Joao", user.getNome());
        assertEquals("joao@gmail.com", user.getEmail());
        assertEquals("rua do Faial",user.getMorada() );
        assertEquals(plano, user.getPlano());

    }
}
