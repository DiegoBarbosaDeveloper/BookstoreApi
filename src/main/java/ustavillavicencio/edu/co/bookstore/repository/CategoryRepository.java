package ustavillavicencio.edu.co.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ustavillavicencio.edu.co.bookstore.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
