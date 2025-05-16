package Application.exceptions;

public class zeroGenresListen extends RuntimeException {
    public zeroGenresListen() {
        super("Ainda nenhuma música foi reproduzida");
    }
}

