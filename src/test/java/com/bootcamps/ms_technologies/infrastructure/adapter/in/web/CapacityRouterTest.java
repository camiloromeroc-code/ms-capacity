package com.bootcamps.ms_technologies.infrastructure.adapter.in.web;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.server.RouterFunctions.toWebHandler;

class CapacityRouterTest {

    private WebTestClient webTestClient;
    private CapacityHandler handler;

    @BeforeEach
    void setUp() {
        handler = Mockito.mock(CapacityHandler.class);
        RouterFunction<ServerResponse> routerFunction = new CapacityRouter().capacityRoutes(handler);
        webTestClient = WebTestClient.bindToWebHandler(toWebHandler(routerFunction)).build();
    }

    @Nested
    @DisplayName("GET /api/v1/capacities")
    class GetAllTechnologies {

        @Test
        @DisplayName("should return 200 and list of capacities when data exists")
        void shouldReturn200AndListOfTechnologies() {
            when(handler.getAll(any())).thenReturn(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build());

            webTestClient.get()
                    .uri("/api/v1/capacities")
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON);
        }
    }

    @Nested
    @DisplayName("GET /api/v1/capacities/{id}")
    class GetCapacityById {

        @Test
        @DisplayName("should return 200 and technology when ID exists")
        void shouldReturn200AndTechnology() {
            when(handler.getById(any())).thenReturn(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build());

            webTestClient.get()
                    .uri("/api/v1/capacities/1")
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON);
        }

        @Test
        @DisplayName("should return 404 when technology not found")
        void shouldReturn404WhenTechnologyNotFound() {
            when(handler.getById(any())).thenReturn(ServerResponse.notFound().build());

            webTestClient.get()
                    .uri("/api/v1/capacities/999")
                    .exchange()
                    .expectStatus().isNotFound();
        }
    }

    @Nested
    @DisplayName("POST /api/v1/capacities")
    class CreateCapacity {

        @Test
        @DisplayName("should return 200 and created technology when input is valid")
        void shouldReturn200AndCreatedTechnology() {
            when(handler.create(any())).thenReturn(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build());

            webTestClient.post()
                    .uri("/api/v1/capacities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new Capacity(1L, "", "",new ArrayList<>()))
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON);
        }

        @Test
        @DisplayName("should return 400 when input is invalid")
        void shouldReturn400WhenInputIsInvalid() {
            when(handler.create(any())).thenReturn(ServerResponse.badRequest().build());

            webTestClient.post()
                    .uri("/api/v1/capacities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("{}")
                    .exchange()
                    .expectStatus().isBadRequest();
        }
    }

    @Nested
    @DisplayName("PUT /api/v1/capacities/{id}")
    class UpdateCapacity {

        @Test
        @DisplayName("should return 200 and updated technology when input is valid")
        void shouldReturn200AndUpdatedTechnology() {
            when(handler.update(any())).thenReturn(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build());

            webTestClient.put()
                    .uri("/api/v1/capacities/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new Capacity(1L, "", "",new ArrayList<>()))
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON);
        }

        @Test
        @DisplayName("should return 404 when technology not found")
        void shouldReturn404WhenTechnologyNotFound() {
            when(handler.update(any())).thenReturn(ServerResponse.notFound().build());

            webTestClient.put()
                    .uri("/api/v1/capacities/999")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new Capacity(1L, "", "",new ArrayList<>()))
                    .exchange()
                    .expectStatus().isNotFound();
        }

        @Test
        @DisplayName("should return 400 when input is invalid")
        void shouldReturn400WhenInputIsInvalid() {
            when(handler.update(any())).thenReturn(ServerResponse.badRequest().build());

            webTestClient.put()
                    .uri("/api/v1/capacities/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("{}")
                    .exchange()
                    .expectStatus().isBadRequest();
        }
    }

    @Nested
    @DisplayName("DELETE /api/v1/capacities/{id}")
    class DeleteCapacity {

        @Test
        @DisplayName("should return 204 when technology is deleted successfully")
        void shouldReturn204WhenTechnologyDeletedSuccessfully() {
            when(handler.delete(any())).thenReturn(ServerResponse.noContent().build());

            webTestClient.delete()
                    .uri("/api/v1/capacities/1")
                    .exchange()
                    .expectStatus().isNoContent();
        }

        @Test
        @DisplayName("should return 404 when technology not found")
        void shouldReturn404WhenTechnologyNotFound() {
            when(handler.delete(any())).thenReturn(ServerResponse.notFound().build());

            webTestClient.delete()
                    .uri("/api/v1/capacities/999")
                    .exchange()
                    .expectStatus().isNotFound();
        }
    }
}
