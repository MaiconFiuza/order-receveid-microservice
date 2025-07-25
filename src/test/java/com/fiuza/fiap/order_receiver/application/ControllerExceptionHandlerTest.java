package com.fiuza.fiap.order_receiver.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fiuza.fiap.order_receiver.application.handler.ControllerExceptionHandler;
import com.fiuza.fiap.order_receiver.core.exceptions.InternalServerError;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

@WebMvcTest
public class ControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @RestController
    static class TestController {
        @GetMapping("/test-error")
        public void throwError() {
            throw new InternalServerError("Erro interno de teste");
        }
    }

    @Configuration
    static class Config {
        @Bean
        public ControllerExceptionHandler controllerExceptionHandler() {
            return new ControllerExceptionHandler();
        }
    }

    @Test
    void handlerInternalServerErrorException_returnsProperResponse() throws Exception {
        mockMvc.perform(get("/test-error")
                        .accept(MediaType.APPLICATION_JSON));
    }
}
