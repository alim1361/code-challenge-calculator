package com.code.challenge.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class ScientificCalculatorService {


    public long factorial(Double a) {
        if (a % 1 != 0 || a < 0)
            throw new IllegalArgumentException("value is invalid");
        long factorial = 1l;
        for (long i = 2L; i <= a; i++)
            factorial *= i;
        return factorial;

    }

    public Double square(Double a) {
        return Math.sqrt(a);
    }

    public boolean isPrime(Double a) {
        if (a % 1 != 0 || a < 0)
            throw new IllegalArgumentException("value is invalid");
        for (long l = 2; l < a / 2; l++) {
            if (a % l == 0)
                return false;
        }

        return true;
    }
}
