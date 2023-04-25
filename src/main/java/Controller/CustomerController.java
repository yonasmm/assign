package Controller;

import Model.Customer;
import Model.Transaction;
//import Services.CustomerService;
import Services.RewardCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private RewardCalculator rewardCalculator;
    private final Map< Long, Customer> customers = new HashMap<>();
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long id) {
        Customer user = rewardCalculator.getCustomer(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{id}")
    public int getRewardPoints(@PathVariable Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        int rewardPoints = 0;
        for (Transaction transaction : customer.getTransactions()) {
            rewardPoints += rewardCalculator.calculateRewardPoints( id,transaction.getAmount());
        }
        return rewardPoints;
    }

    @PostMapping("/{id}/transactions")
    public void addTransaction(@PathVariable  Long id, @RequestBody Transaction transaction) {
        Customer customer = customers.get(id);
        if (customer == null) {
            customer = new Customer();
            customer.setId(id);
            customer.setTransactions(new ArrayList<>());
            customers.put(id, customer);
        }
        customer.getTransactions().add(transaction);
    }
}
