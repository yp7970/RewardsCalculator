package com.example.rewardscalculator.controller;

import com.example.rewardscalculator.dto.Customer;
import com.example.rewardscalculator.dto.CustomerRewards;
import com.example.rewardscalculator.service.CustomerService;
import com.example.rewardscalculator.service.RewardsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final RewardsService rewardsService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @GetMapping("/{customerId}/rewards")
    public ResponseEntity<CustomerRewards> getCustomerRewards(@PathVariable String customerId) {
        return ResponseEntity.ok(rewardsService.calculateMonthlyPointsForCustomer(customerId));
    }

    @GetMapping("/rewards")
    public ResponseEntity<List<CustomerRewards>> getAllCustomersRewards() {
        return ResponseEntity.ok(rewardsService.calculateMonthlyPointsForAllCustomers());
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

}
