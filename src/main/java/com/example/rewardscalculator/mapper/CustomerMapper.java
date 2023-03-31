package com.example.rewardscalculator.mapper;

import com.example.rewardscalculator.dto.Customer;
import com.example.rewardscalculator.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity map(Customer customer);

    Customer map(CustomerEntity customerEntity);

}
