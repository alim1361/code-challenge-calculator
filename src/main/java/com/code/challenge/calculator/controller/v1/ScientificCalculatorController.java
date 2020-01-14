package com.code.challenge.calculator.controller.v1;

import com.code.challenge.calculator.dto.request.ScientificOperationCommandDTO;
import com.code.challenge.calculator.dto.response.ScientificOutputDTO;
import com.code.challenge.calculator.service.ScientificCalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/scientificCalculator")
public class ScientificCalculatorController {
    private final ScientificCalculatorService service;

    public ScientificCalculatorController(ScientificCalculatorService service) {
        this.service = service;
    }

    @PutMapping("/generic")
    public ScientificOutputDTO<?> scientificCalculator(@RequestBody ScientificOperationCommandDTO commandData) {
        switch (commandData.getOperation()) {
            case FAC:
                return new ScientificOutputDTO<Long>(service.factorial(commandData.getOperator()));
            case SQR:
                return new ScientificOutputDTO<Double>(service.square(commandData.getOperator()));
            case PRM:
                return new ScientificOutputDTO<Boolean>(service.isPrime(commandData.getOperator()));
        }
        throw new IllegalArgumentException("invalid action");

    }

    @GetMapping("/factorial/{number}")
    public long factorial(@PathVariable("number") int number) {
        return service.factorial(new Double(number));
    }

    @GetMapping("/is-prime/{number}")
    public boolean isPrime(@PathVariable("number") long number) {
        return service.isPrime(new Double(number));
    }

    @GetMapping("/square/{number}")
    public Double square(@PathVariable("number") double number) {
        return service.square(number);
    }
}
