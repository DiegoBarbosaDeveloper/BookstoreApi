package ustavillavicencio.edu.co.bookstore.exception.custom;

public class AuthorHasBooksException extends RuntimeException {
    public AuthorHasBooksException(String message) {
        super(message);
    }
}
