package ustavillavicencio.edu.co.bookstore.mapper;

import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;
import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(CategoryRequest request) {
        if (request == null) {
            return null;
        }

        return new CategoryEntity(
            null,
            request.getName(),
            request.getDescription(),
            null
        );
    }

    public CategoryResponse toResponse(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CategoryResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription()
        );
    }
}
