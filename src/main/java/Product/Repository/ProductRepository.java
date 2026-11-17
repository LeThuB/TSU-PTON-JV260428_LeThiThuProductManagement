package Product.Repository;

import Product.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
List<Product> findByStatus(int status);
    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByName(String name);
}


