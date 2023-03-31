package com.example.rewardscalculator.mapper;

import com.example.rewardscalculator.dto.Transaction;
import com.example.rewardscalculator.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "date", source = "date", dateFormat = "YYYY-mm-dd")
    TransactionEntity map(Transaction transaction);

    @Mapping(target = "date", source = "date", dateFormat = "YYYY-mm-dd")
    @Mapping(target = "customerId", source = "customer.id")
    Transaction map(TransactionEntity transactionEntity);
}
