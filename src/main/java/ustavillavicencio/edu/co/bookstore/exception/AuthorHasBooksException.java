package ustavillavicencio.edu.co.bookstore.exception;

public class AuthorHasBooksException extends RuntimeException {

    public AuthorHasBooksException(Long authorId) {
        super("No se puede eliminar el autor con id " + authorId + " porque tiene libros asociados");
    }
}
