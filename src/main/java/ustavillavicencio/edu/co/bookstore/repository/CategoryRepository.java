package ustavillavicencio.edu.co.bookstore.repository;

import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"books"})
    Optional<Category> findWithBooksById(Long id);

import org.springframework.data.jpa.repository.JpaRepository;

import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
