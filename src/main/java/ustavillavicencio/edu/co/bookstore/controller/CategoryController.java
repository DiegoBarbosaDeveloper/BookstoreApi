package ustavillavicencio.edu.co.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.CategoryRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookSummaryResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.CategoryResponse;
import ustavillavicencio.edu.co.bookstore.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor

@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse body = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, "Categoría creada", body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(200, "Categoría encontrada", categoryService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.success(200, "Lista de categorías", categoryService.findAll()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse body = categoryService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(200, "Categoría actualizada", body));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Categoría eliminada", null));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<ApiResponse<List<BookSummaryResponse>>> getBooks(
            @PathVariable Long id) {
        List<BookSummaryResponse> body = categoryService.getBooksByCategory(id);
        return ResponseEntity.ok(ApiResponse.success(200, "Libros de la categoría", body));
    }

    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/books")
    public List<BookResponse> findBooksByCategory(@PathVariable Long id) {
        return categoryService.findBooksByCategoryId(id);
    }
}
