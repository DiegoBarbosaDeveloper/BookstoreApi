package ustavillavicencio.edu.co.bookstore.mapper;

import java.util.Locale.Category;

import org.springframework.stereotype.Component;

import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;

@Component

public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

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
