package com.bfhl.bfhl_api;

import com.bfhl.bfhl_api.controller.BfhlController;
import com.bfhl.bfhl_api.service.BfhlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BfhlController.class)
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    @Test
    void getBfhlReturnsOperationCode() throws Exception {
        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }
}