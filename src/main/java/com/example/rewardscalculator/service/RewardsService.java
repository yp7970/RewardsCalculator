package com.example.rewardscalculator.service;

import com.example.rewardscalculator.dto.Customer;
import com.example.rewardscalculator.dto.CustomerRewards;
import com.example.rewardscalculator.dto.MonthlyRewards;
import com.example.rewardscalculator.dto.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class RewardsService {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    public List<CustomerRewards> calculateMonthlyPointsForAllCustomers() {
        List<CustomerRewards> customerRewards = new ArrayList<>();
        customerService.getAllCustomers().forEach(
                customer -> {
                    customerRewards.add(calculateMonthlyPointsForCustomer(customer));
                }
        );
        return customerRewards;
    }

    public CustomerRewards calculateMonthlyPointsForCustomer(String customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return calculateMonthlyPointsForCustomer(customer);
    }

    public CustomerRewards calculateMonthlyPointsForCustomer(Customer customer) {
        Map<Integer, List<Transaction>> monthlyTransactions = calculateMonthlyPoints(customer);
        CustomerRewards customerRewards = new CustomerRewards();
        List<MonthlyRewards> monthlyRewardsList = new ArrayList<>();
        int totalPoints = 0;
        for (Map.Entry<Integer, List<Transaction>> entry : monthlyTransactions.entrySet()) {
            MonthlyRewards monthlyRewards = new MonthlyRewards();
            int points = entry.getValue().stream().map(Transaction::getPoints).reduce(0, Integer::sum);
            monthlyRewards.setMonth(Month.of(entry.getKey()).name());
            monthlyRewards.setPoints(points);
            monthlyRewardsList.add(monthlyRewards);
            totalPoints += points;
        }
        customerRewards.setMonthlyRewards(monthlyRewardsList);
        customerRewards.setTotalPoints(totalPoints);
        customerRewards.setName(customer.getLastName() + " " + customer.getFirstName());
        return customerRewards;
    }

    public Map<Integer, List<Transaction>> calculateMonthlyPoints(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();
        transactions.forEach(transaction -> transaction.setPoints(calculatePoints(transaction.getAmount())));
        return transactions.stream().collect(groupingBy(transaction -> transaction.getDate().getMonth().getValue()));
    }

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2 + 50;
        } else if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

}
