package com.bootcamps.ms_technologies.infrastructure.adapter.in.web;


import com.bootcamps.ms_technologies.domain.model.Capacity;
import com.bootcamps.ms_technologies.domain.service.CapacityService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class CapacityHandler {

    private final CapacityService capacityService;

    public CapacityHandler(CapacityService capacityService) {
        this.capacityService = capacityService;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(capacityService.listCapacities(), Capacity.class));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(capacityService.getCapacityById(id), Capacity.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Capacity.class)
                .flatMap(capacityService::createCapacity)
                .flatMap(saved -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(Capacity.class)
                .flatMap(capacity -> capacityService.updateCapacity(id, capacity))
                .flatMap(updated -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updated));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return capacityService.deleteCapacity(id)
                .then(ServerResponse.noContent().build());
    }
}
