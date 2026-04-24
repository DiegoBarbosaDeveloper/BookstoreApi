package ustavillavicencio.edu.co.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ustavillavicencio.edu.co.bookstore.entity.OrderEntity;
import ustavillavicencio.edu.co.bookstore.entity.UserEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @EntityGraph(attributePaths = {"items", "items.book"})
    List<OrderEntity> findByUser(UserEntity user);

    @Override
    @EntityGraph(attributePaths = {"items", "items.book", "user"})
    List<OrderEntity> findAll();


}