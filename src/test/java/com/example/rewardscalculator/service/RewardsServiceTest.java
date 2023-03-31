package com.example.rewardscalculator.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RewardsServiceTest {

    @Mock
    CustomerService customerService;

    @Mock
    TransactionService transactionService;

    @InjectMocks
    RewardsService rewardsService;


    @ParameterizedTest
    @MethodSource(value = "args")
    void calculatePoints(double amount, int points) {
        assertThat(rewardsService.calculatePoints(amount)).isEqualTo(points);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(120, 90),
                Arguments.of(0, 0),
                Arguments.of(10, 0),
                Arguments.of(52, 2),
                Arguments.of(250, 350),
                Arguments.of(100, 50),
                Arguments.of(50, 0)
        );
    }
}