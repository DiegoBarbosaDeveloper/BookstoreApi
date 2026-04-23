package ustavillavicencio.edu.co.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ustavillavicencio.edu.co.bookstore.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
