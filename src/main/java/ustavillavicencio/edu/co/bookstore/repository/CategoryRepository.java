package ustavillavicencio.edu.co.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"books"})
    Optional<CategoryEntity> findWithBooksById(Long id);

}