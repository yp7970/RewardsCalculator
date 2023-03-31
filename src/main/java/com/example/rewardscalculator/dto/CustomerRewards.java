package com.example.rewardscalculator.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRewards {
    private String name;
    private int totalPoints;
    private List<MonthlyRewards> monthlyRewards;
}
