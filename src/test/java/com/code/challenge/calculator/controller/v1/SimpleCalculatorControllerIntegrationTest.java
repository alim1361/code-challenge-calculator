package com.code.challenge.calculator.controller.v1;

import com.code.challenge.calculator.dto.request.SimpleOperation;
import com.code.challenge.calculator.dto.request.SimpleOperationCommandDTO;
import com.code.challenge.calculator.service.SimpleCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleCalculatorControllerIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/v1/simpleCalculator/7/add/8")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("15")));
    }

    @Test
    public void shouldReturnDefaultMessage1() throws Exception {
        SimpleOperationCommandDTO dto = new SimpleOperationCommandDTO();
        dto.setFirstOperator(10d);
        dto.setSecondOperator(15d);
        dto.setOperation(SimpleOperation.ADD);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);
        this.mockMvc.perform(put("/v1/simpleCalculator/generic").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("25")));
    }

    @Test
    public void shouldReturnDefaultMessage2() throws Exception {
        SimpleOperationCommandDTO dto = new SimpleOperationCommandDTO();
        dto.setFirstOperator(10d);
        dto.setSecondOperator(0d);
        dto.setOperation(SimpleOperation.DIV);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);
        this.mockMvc.perform(put("/v1/simpleCalculator/generic").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString("second argument must not be zero")));
    }
}