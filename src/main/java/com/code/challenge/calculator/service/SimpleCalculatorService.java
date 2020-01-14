package com.code.challenge.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleCalculatorService {

    public Double addCommand(Double a, Double b) {

        return a + b;
    }

    public Double subCommand(Double a, Double b) {

        return a - b;
    }

    public Double mulCommand(Double a, Double b) {

        return a * b;
    }

    public Double divCommand(Double a, Double b) {
        if (b == 0f)
            throw new IllegalArgumentException("second argument must not be zero");
        return a / b;
    }
}
