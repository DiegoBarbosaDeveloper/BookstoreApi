package ustavillavicencio.edu.co.bookstore.service;

import java.util.List;

import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookSummaryResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse             create(CategoryRequest request);
    CategoryResponse             findById(Long id);
    List<CategoryResponse>       findAll();
    CategoryResponse             update(Long id, CategoryRequest request);
    void                         delete(Long id);
    List<BookSummaryResponse>    getBooksByCategory(Long categoryId);

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;
import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;
import ustavillavicencio.edu.co.bookstore.exception.custom.ResourceNotFoundException;
import ustavillavicencio.edu.co.bookstore.mapper.BookMapper;
import ustavillavicencio.edu.co.bookstore.mapper.CategoryMapper;
import ustavillavicencio.edu.co.bookstore.repository.BookRepository;
import ustavillavicencio.edu.co.bookstore.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    public CategoryResponse create(CategoryRequest request) {
        CategoryEntity saved = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(saved);
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
            .map(categoryMapper::toResponse)
            .toList();
    }

    public CategoryResponse findById(Long id) {
        CategoryEntity category = getCategoryOrThrow(id);
        return categoryMapper.toResponse(category);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        CategoryEntity category = getCategoryOrThrow(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        CategoryEntity updated = categoryRepository.save(category);
        return categoryMapper.toResponse(updated);
    }

    public void delete(Long id) {
        getCategoryOrThrow(id);
        categoryRepository.deleteById(id);
    }

    public List<BookResponse> findBooksByCategoryId(Long id) {
        getCategoryOrThrow(id);
        return bookRepository.findByCategoryId(id).stream()
            .map(bookMapper::toResponse)
            .toList();
    }

    private CategoryEntity getCategoryOrThrow(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria con id " + id + " no encontrada"));
    }
}
