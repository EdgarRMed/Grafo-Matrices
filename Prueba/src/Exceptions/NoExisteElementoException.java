package Exceptions;

public class NoExisteElementoException extends Exception {
    public NoExisteElementoException(){super("No existe el elemento\n");}
    public NoExisteElementoException(String messagge){super(messagge+"\n");}
}
