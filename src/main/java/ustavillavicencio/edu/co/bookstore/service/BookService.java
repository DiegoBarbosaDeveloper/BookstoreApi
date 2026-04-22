package ustavillavicencio.edu.co.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.BookRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.entity.AuthorEntity;
import ustavillavicencio.edu.co.bookstore.entity.BookEntity;
import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

import ustavillavicencio.edu.co.bookstore.mapper.BookMapper;
import ustavillavicencio.edu.co.bookstore.repository.AuthorRepository;
import ustavillavicencio.edu.co.bookstore.repository.BookRepository;
import ustavillavicencio.edu.co.bookstore.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final CategoryRepository categoryRepository;
	private final BookMapper bookMapper;

	public BookResponse create(BookRequest request) {
		AuthorEntity author = getAuthorOrThrow(request.getAuthorId());
		CategoryEntity category = getCategoryOrThrow(request.getCategoryId());
		BookEntity saved = bookRepository.save(bookMapper.toEntity(request, author, category));
		return bookMapper.toResponse(saved);
	}

	public List<BookResponse> findAll() {
		return bookRepository.findAll().stream()
			.map(bookMapper::toResponse)
			.toList();
	}

	public BookResponse findById(Long id) {
		BookEntity book = getBookOrThrow(id);
		return bookMapper.toResponse(book);
	}

	public BookResponse update(Long id, BookRequest request) {
		BookEntity book = getBookOrThrow(id);
		AuthorEntity author = getAuthorOrThrow(request.getAuthorId());
		CategoryEntity category = getCategoryOrThrow(request.getCategoryId());

		book.setTitle(request.getTitle());
		book.setState(request.getState());
		book.setStock(request.getStock());
		book.setPrice(request.getPrice());
		book.setIsbn(request.getIsbn());
		book.setAuthor(author);
		book.setCategory(category);

		BookEntity updated = bookRepository.save(book);
		return bookMapper.toResponse(updated);
	}

	public void delete(Long id) {
		getBookOrThrow(id);
		bookRepository.deleteById(id);
	}

	public List<BookResponse> findByAuthorId(Long authorId) {
		return bookRepository.findByAuthorId(authorId).stream()
			.map(bookMapper::toResponse)
			.toList();
	}

	public List<BookResponse> findByCategoryId(Long categoryId) {
		return bookRepository.findByCategoryId(categoryId).stream()
			.map(bookMapper::toResponse)
			.toList();
	}

	private BookEntity getBookOrThrow(Long id) {
		return bookRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Libro", id));
	}

	private AuthorEntity getAuthorOrThrow(Long id) {
		return authorRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Autor", id));
	}

	private CategoryEntity getCategoryOrThrow(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
	}
}
