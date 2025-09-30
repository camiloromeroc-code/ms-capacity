package com.bootcamps.ms_technologies.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class GlobalErrorHandlerTest {

    private GlobalErrorHandler globalErrorHandler;
    private ObjectMapper objectMapper;
    private ServerWebExchange exchange;
    private ServerHttpResponse response;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        globalErrorHandler = new GlobalErrorHandler(objectMapper);
        exchange = mock(ServerWebExchange.class);
        response = mock(ServerHttpResponse.class);

        when(exchange.getResponse()).thenReturn(response);
        when(response.bufferFactory()).thenReturn(new DefaultDataBufferFactory());
    }

    @Nested
    @DisplayName("handle method")
    class HandleMethod {


        @Test
        @DisplayName("does not handle committed response")
        void doesNotHandleCommittedResponse() {
            when(response.isCommitted()).thenReturn(true);

            Mono<Void> result = globalErrorHandler.handle(exchange, new RuntimeException("Unexpected error"));

            StepVerifier.create(result)
                    .expectError(RuntimeException.class)
                    .verify();

            verify(response, never()).setStatusCode(any());
        }

    }
}
