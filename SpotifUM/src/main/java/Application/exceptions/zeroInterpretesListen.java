package Application.exceptions;

public class zeroInterpretesListen extends RuntimeException {
    public zeroInterpretesListen() {
        super("Ainda nenhum intérprete foi ouvido");
    }

}