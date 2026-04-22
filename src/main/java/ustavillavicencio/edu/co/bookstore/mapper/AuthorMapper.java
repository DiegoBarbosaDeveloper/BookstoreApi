package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.request.AuthorRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.AuthorResponse;
import ustavillavicencio.edu.co.bookstore.entity.AuthorEntity;

@Component
public class AuthorMapper {

     // Convierte el DTO de entrada en una entidad lista para persistir.
    // El id y la lista de libros se dejan en null porque se gestionan en capa de negocio/JPA.
    public AuthorEntity toEntity(AuthorRequest request) {
        if (request == null) {
            return null;
        }

        return new AuthorEntity(
            null,
            request.getName(),
            request.getBiography(),
            request.getEmail(),
            request.getPhoneNumber(),
            request.getAge(),
            null
        );
    }

    // Convierte la entidad en un DTO de salida para exponer solo la informacion necesaria.
    public AuthorResponse toResponse(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }

        return new AuthorResponse(
            entity.getId(),
            entity.getName(),
            entity.getBiography(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getAge()
        );
    }
}