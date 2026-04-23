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

}
