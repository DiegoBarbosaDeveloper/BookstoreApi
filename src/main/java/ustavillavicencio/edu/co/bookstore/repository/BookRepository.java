package ustavillavicencio.edu.co.bookstore.repository;

public class BookRepository {
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ustavillavicencio.edu.co.bookstore.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

	List<BookEntity> findByAuthorId(Long authorId);

	List<BookEntity> findByCategoryId(Long categoryId);

	boolean existsByAuthorId(Long authorId);
}
