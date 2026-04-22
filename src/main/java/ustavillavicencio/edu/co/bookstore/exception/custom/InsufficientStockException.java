package ustavillavicencio.edu.co.bookstore.exception.custom;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
