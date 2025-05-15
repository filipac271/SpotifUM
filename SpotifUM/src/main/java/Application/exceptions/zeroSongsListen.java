package Application.exceptions;

public class zeroSongsListen extends RuntimeException {
    public zeroSongsListen() {
        super("Não existe músicas que já tenham sido reproduzidas");
    }
}
