package ustavillavicencio.edu.co.bookstore.exception.custom;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException() {
        super("No tienes permiso para acceder a este recurso");
    }

}
