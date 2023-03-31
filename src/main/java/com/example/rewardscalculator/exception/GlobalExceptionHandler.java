package com.example.rewardscalculator.exception;

import com.example.rewardscalculator.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorResponse> errorResponseList = ex.getFieldErrors().stream().map(error ->
                ErrorResponse.builder()
                        .reason(error.getField() + " " + error.getDefaultMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .recoverable(true)
                        .build()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorResponseList);
    }

    @ExceptionHandler(RewardsException.class)
    public ResponseEntity<ErrorResponse> handleRewardsException(RewardsException ex) {
        return ResponseEntity.status(ex.getErrorResponse().getStatus()).body(ex.getErrorResponse());
    }

}
