package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Application.model.PlanoSubscricao.PlanoFree;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.User.User;
import Application.model.User.Historico;


public class UserTest {
    
    @Test
    public void userTest()
    {
        List<Historico> historico = new ArrayList<>();
        Historico h= new Historico();
        // Preencher historico
        historico.add(h);
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
