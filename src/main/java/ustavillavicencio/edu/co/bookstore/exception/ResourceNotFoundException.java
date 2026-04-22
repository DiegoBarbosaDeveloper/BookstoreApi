package ustavillavicencio.edu.co.bookstore.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " con id " + id + " no encontrado");
    }
}
