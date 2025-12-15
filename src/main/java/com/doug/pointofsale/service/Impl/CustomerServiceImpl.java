package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.models.Customer;
import com.doug.pointofsale.repository.CustomerRepository;
import com.doug.pointofsale.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {

    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return List.of();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return List.of();
    }
}
