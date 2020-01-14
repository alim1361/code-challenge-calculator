package com.code.challenge.calculator.controller.v1;

import com.code.challenge.calculator.dto.request.SimpleOperationCommandDTO;
import com.code.challenge.calculator.service.SimpleCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/simpleCalculator")
public class SimpleCalculatorController {
    private static Logger logger = LoggerFactory.getLogger(SimpleCalculatorController.class);
    private final SimpleCalculatorService service;

    public SimpleCalculatorController(SimpleCalculatorService service) {
        this.service = service;
    }

    @PutMapping("/generic")
    public Double simpleCalculate(@RequestBody SimpleOperationCommandDTO commandData) {
        logger.debug("sth is called" + commandData.toString());
        switch (commandData.getOperation()) {
            case ADD:
                return service.addCommand(commandData.getFirstOperator(), commandData.getSecondOperator());
            case SUB:
                return service.subCommand(commandData.getFirstOperator(), commandData.getSecondOperator());
            case MUL:
                return service.mulCommand(commandData.getFirstOperator(), commandData.getSecondOperator());
            case DIV:
                return service.divCommand(commandData.getFirstOperator(), commandData.getSecondOperator());

        }

        throw new IllegalArgumentException("invalid action");
    }

    @GetMapping("/{firstOperator}/add/{secondOperator}")
    public double add(@PathVariable double firstOperator, @PathVariable double secondOperator) {
        return service.addCommand(firstOperator, secondOperator);
    }
}
