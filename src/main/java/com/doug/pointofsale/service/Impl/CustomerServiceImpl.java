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

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        customer.setFullName(customerToUpdate.getFullName());
        customer.setEmail(customerToUpdate.getEmail());
        customer.setPhoneNumber(customerToUpdate.getPhoneNumber());

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
        customerRepository.delete(customerToDelete);

    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
                return  customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
}
