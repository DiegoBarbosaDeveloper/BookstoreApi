package ustavillavicencio.edu.co.bookstore.impl;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookSummaryResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;
import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;
import ustavillavicencio.edu.co.bookstore.exception.custom.DuplicateResourceException;
import ustavillavicencio.edu.co.bookstore.exception.custom.ResourceNotFoundException;
import ustavillavicencio.edu.co.bookstore.mapper.CategoryMapper;
import ustavillavicencio.edu.co.bookstore.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException(
                    "Ya existe una categoría con el nombre '" + request.getName() + "'");
        }
        CategoryEntity category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return categoryMapper.toResponse(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        CategoryEntity category = getOrThrow(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.delete(getOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<BookSummaryResponse> getBooksByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findWithBooksById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría con id " + categoryId + " no encontrada"));

        return category.getBooks().stream()
            .map(book -> BookSummaryResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .price(BigDecimal.valueOf(book.getPrice()))
                .stock(book.getStock())
                .build())
                .toList();
    }

        private CategoryEntity getOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría con id " + id + " no encontrada"));
    }

}
