package com.example.rewardscalculator.exception;

import com.example.rewardscalculator.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardsException extends RuntimeException {
    private ErrorResponse errorResponse;
}
