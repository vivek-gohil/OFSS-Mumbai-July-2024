package com.ofss.main.service.impl;

import com.ofss.main.domain.Customer;
import com.ofss.main.repository.CustomerRepository;
import com.ofss.main.repository.impl.CustomerRepositoryImpl;
import com.ofss.main.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Customer addNewCustomer(Customer customer) {
        int customerId = customerRepository.addNewCustomer(customer);
        if (customerId != 0) {
            customer.setCustomerId(customerId);
            return  customer;   
        }
        return null;
    }

}
