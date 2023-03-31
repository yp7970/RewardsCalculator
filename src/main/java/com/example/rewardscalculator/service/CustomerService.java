package com.example.rewardscalculator.service;

import com.example.rewardscalculator.dto.Customer;
import com.example.rewardscalculator.dto.ErrorResponse;
import com.example.rewardscalculator.entity.CustomerEntity;
import com.example.rewardscalculator.exception.RewardsException;
import com.example.rewardscalculator.mapper.CustomerMapper;
import com.example.rewardscalculator.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer getCustomer(String id) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customerMapper::map)
                .orElseThrow(() -> {
                    throw new RewardsException(
                            ErrorResponse.builder()
                                    .status(HttpStatus.NOT_FOUND)
                                    .reason("CustomerId is not available in the system.")
                                    .recoverable(true)
                                    .build());
                });
    }

    public String createCustomer(Customer customer) {
        CustomerEntity savedEntity = customerRepository.save(customerMapper.map(customer));
        return savedEntity.getId();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::map)
                .collect(Collectors.toList());
    }
}
