package com.example.rewardscalculator.service;

import com.example.rewardscalculator.dto.ErrorResponse;
import com.example.rewardscalculator.dto.Transaction;
import com.example.rewardscalculator.entity.CustomerEntity;
import com.example.rewardscalculator.entity.TransactionEntity;
import com.example.rewardscalculator.exception.RewardsException;
import com.example.rewardscalculator.mapper.TransactionMapper;
import com.example.rewardscalculator.repository.CustomerRepository;
import com.example.rewardscalculator.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CustomerRepository customerRepository;

    public Transaction getTransaction(String id) {
        Optional<TransactionEntity> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.map(transactionMapper::map).orElseThrow(() -> {
            throw new RewardsException(
                    ErrorResponse.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .reason("TransactionId is not available in the system.")
                            .recoverable(true)
                            .build());
        });
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::map)
                .collect(Collectors.toList());
    }

    public String createTransaction(Transaction transaction) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(transaction.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException();
        }
        TransactionEntity transactionEntity = transactionMapper.map(transaction);
        transactionEntity.setCustomer(optionalCustomer.get());
        TransactionEntity savedEntity = transactionRepository.save(transactionEntity);
        return savedEntity.getId();
    }
}
