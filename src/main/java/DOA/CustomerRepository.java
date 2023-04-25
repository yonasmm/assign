package DOA;

import Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // find customer by id
    @Query
    Customer findCustomerById(Long id);

    // update customer points by id
    @Query
    void updateCustomerPointsById(Long id, int points);
}