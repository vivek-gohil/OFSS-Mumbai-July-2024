package com.ofss.main.repository;

import com.ofss.main.domain.Customer;

public interface CustomerRepository {
    int addNewCustomer(Customer customer);
    Customer getCustomerByCustomerId(int customerId);
}
