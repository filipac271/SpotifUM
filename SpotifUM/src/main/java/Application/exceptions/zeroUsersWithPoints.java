package Application.exceptions;

public class zeroUsersWithPoints extends RuntimeException {
    public zeroUsersWithPoints() {
        super("Não existe Users com pontos associados");
    }
}
