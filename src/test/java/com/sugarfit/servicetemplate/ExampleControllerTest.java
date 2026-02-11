package com.sugarfit.servicetemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sugarfit.servicetemplate.constants.ErrorConstants;
import com.sugarfit.servicetemplate.constants.GlobalConstants;
import com.sugarfit.servicetemplate.enums.ResponseStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnSuccessForValidRequest() throws Exception {
        String body = """
            {
              "userId": "123",
              "value": 10
            }
        """;

        mockMvc.perform(post("/example/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseStatusEnum.SUCCESS.name()))
                .andExpect(jsonPath("$.requestId").isNotEmpty());
    }
    @Test
    void shouldReturnBadRequestWhenBodyIsMissing() throws Exception {
        mockMvc.perform(post("/example/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(ResponseStatusEnum.FAILURE.name()))
                .andExpect(jsonPath("$.error.code").value(ErrorConstants.INVALID_REQUEST));
    }
    @Test
    void shouldReturnBadRequestForInvalidBody() throws Exception {
        String body = """
        {
          "value": 10
        }
    """;

        mockMvc.perform(post("/example/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(ResponseStatusEnum.FAILURE.name()))
                .andExpect(jsonPath("$.error.code").value(ErrorConstants.INVALID_REQUEST));
    }

}
