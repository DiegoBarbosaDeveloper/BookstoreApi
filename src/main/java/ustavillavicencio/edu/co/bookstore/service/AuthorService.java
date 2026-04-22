package ustavillavicencio.edu.co.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ustavillavicencio.edu.co.bookstore.dto.request.AuthorRequest;
import ustavillavicencio.edu.co.bookstore.dto.response.AuthorResponse;
import ustavillavicencio.edu.co.bookstore.dto.response.BookResponse;
import ustavillavicencio.edu.co.bookstore.entity.AuthorEntity;

import ustavillavicencio.edu.co.bookstore.mapper.AuthorMapper;
import ustavillavicencio.edu.co.bookstore.mapper.BookMapper;
import ustavillavicencio.edu.co.bookstore.repository.AuthorRepository;
import ustavillavicencio.edu.co.bookstore.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final AuthorMapper authorMapper;
	private final BookMapper bookMapper;

	public AuthorResponse create(AuthorRequest request) {
		AuthorEntity saved = authorRepository.save(authorMapper.toEntity(request));
		return authorMapper.toResponse(saved);
	}

	public List<AuthorResponse> findAll() {
		return authorRepository.findAll().stream()
			.map(authorMapper::toResponse)
			.toList();
	}

	public AuthorResponse findById(Long id) {
		AuthorEntity author = getAuthorOrThrow(id);
		return authorMapper.toResponse(author);
	}

	public AuthorResponse update(Long id, AuthorRequest request) {
		AuthorEntity author = getAuthorOrThrow(id);
		author.setName(request.getName());
		author.setBiography(request.getBiography());
		author.setEmail(request.getEmail());
		author.setPhoneNumber(request.getPhoneNumber());
		author.setAge(request.getAge());

		AuthorEntity updated = authorRepository.save(author);
		return authorMapper.toResponse(updated);
	}

	public void delete(Long id) {
		getAuthorOrThrow(id);
		if (bookRepository.existsByAuthorId(id)) {
			throw new AuthorHasBooksException(id);
		}
		authorRepository.deleteById(id);
	}

	public List<BookResponse> findBooksByAuthorId(Long id) {
		getAuthorOrThrow(id);
		return bookRepository.findByAuthorId(id).stream()
			.map(bookMapper::toResponse)
			.toList();
	}

	private AuthorEntity getAuthorOrThrow(Long id) {
		return authorRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Autor", id));
	}
}
