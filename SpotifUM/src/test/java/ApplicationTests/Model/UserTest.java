package ApplicationTests.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Application.model.PlanoSubscricao.PlanoFree;
import Application.model.PlanoSubscricao.PlanoSubscricao;
import Application.model.User.User;
import Application.model.User.Historico;

public class UserTest {

    @Test
    public void userTest() {
        // Criar um histórico de exemplo
        List<Historico> historico = new ArrayList<>();
        Historico h = new Historico(); 
        historico.add(h);

        // Criar um plano e um user
        PlanoSubscricao plano = new PlanoFree();
        User user = new User("Joao", "JotaJota", "password123", "joao@gmail.com", "rua do Faial", 19, 1);

        // Verificações dos campos básicos
        assertEquals("Joao", user.getNome());
        assertEquals("JotaJota", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("joao@gmail.com", user.getEmail());
        assertEquals("rua do Faial", user.getMorada());

        // Verificação da idade
        assertEquals(19, user.getAge());

        // Verificação dos pontos atribuídos pelo plano (deve ser com delta para doubles)
        assertEquals(plano.calculaPontos(0), user.getPontos(), 0.001);

        // Verificação da instância do plano (referência igual, não clone)
        assertSame(plano, user.getPlano());

        // Verificação do histórico (deve estar inicializado como lista vazia)
        assertNotNull(user.getHistorico());
        assertEquals(0, user.getHistorico().size());
    }
}
