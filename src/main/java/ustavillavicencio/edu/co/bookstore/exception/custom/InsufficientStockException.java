package ustavillavicencio.edu.co.bookstore.exception.custom;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String title, int available, int requested) {
        super("Stock insuficiente para '" + title + "': " + "disponible=" + available + ", solicitado=" + requested);
    }
}
