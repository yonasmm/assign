package Services;

import DOA.CustomerRepository;
import Model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardCalculator {
    @Autowired
    private CustomerRepository customerRepository;


    public int calculateRewardPoints(Long id,double transactionAmount) {
        int rewardPoints = 0;
        if (transactionAmount > 100) {
            rewardPoints += (int) ((transactionAmount - 100) );
        }
        if (transactionAmount > 50) {
            rewardPoints += (int) ((transactionAmount - 50) );
        }
        customerRepository.updateCustomerPointsById(id, rewardPoints);
        return rewardPoints;
    }
    public Customer getCustomer(Long id){
           return customerRepository.findCustomerById(id);

    }
}

