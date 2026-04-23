package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.request.BookRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.entity.AuthorEntity;
import ustavillavicencio.edu.co.bookstore.entity.BookEntity;
import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

@Component
public class BookMapper {

    // Convierte el request en entidad.
    // Recibe AuthorEntity y CategoryEntity ya resueltos (por id) desde el servicio/controlador.
    public BookEntity toEntity(BookRequest request, AuthorEntity author, CategoryEntity category) {
        if (request == null) {
            return null;
        }

        return new BookEntity(
            null,
            request.getTitle(),
            request.getState(),
            request.getStock(),
            request.getPrice(),
            request.getIsbn(),
            author,
            category
        );
    }

    // Convierte la entidad en DTO de respuesta.
    // Se exponen authorId y categoryId en lugar de entidades completas para evitar respuestas acopladas.
    public BookResponse toResponse(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        Long authorId = entity.getAuthor() != null ? entity.getAuthor().getId() : null;
        Long categoryId = entity.getCategory() != null ? entity.getCategory().getId() : null;

        return new BookResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getState(),
            entity.getStock(),
            entity.getPrice(),
            entity.getIsbn(),
            authorId,
            categoryId
        );
    }
}