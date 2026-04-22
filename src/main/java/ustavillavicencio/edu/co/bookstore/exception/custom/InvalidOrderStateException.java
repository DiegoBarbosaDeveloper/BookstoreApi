package ustavillavicencio.edu.co.bookstore.exception.custom;

public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(String message) {
        super(message);
    }
}
