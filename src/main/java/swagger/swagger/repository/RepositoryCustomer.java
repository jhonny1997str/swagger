package swagger.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swagger.swagger.model.Customers;
@Repository
public interface RepositoryCustomer extends JpaRepository <Customers, Long> {
}