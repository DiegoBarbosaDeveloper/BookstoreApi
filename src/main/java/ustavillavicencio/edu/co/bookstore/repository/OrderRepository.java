package ustavillavicencio.edu.co.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"items", "items.book"})
    List<Order> findByUser(User user);

    @Override
    @EntityGraph(attributePaths = {"items", "items.book", "user"})
    List<Order> findAll();


}
