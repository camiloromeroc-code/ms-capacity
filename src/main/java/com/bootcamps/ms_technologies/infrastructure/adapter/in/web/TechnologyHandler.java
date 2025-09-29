package com.bootcamps.ms_technologies.infrastructure.adapter.in.web;


import com.bootcamps.ms_technologies.domain.model.Technology;
import com.bootcamps.ms_technologies.domain.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechnologyHandler {

    private final TechnologyService technologyService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(technologyService.listTechnologies(), Technology.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Technology.class)
                .flatMap(technologyService::createTechnology)
                .flatMap(tech -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(tech)));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return technologyService.getTechnologyById(Long.valueOf(id))
                .flatMap(tech -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(tech)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(Technology.class)
                .flatMap(tech -> technologyService.updateTechnology(Long.valueOf(id), tech))
                .flatMap(updated -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(updated)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return technologyService.deleteTechnology(Long.valueOf(id))
                .flatMap(deleted -> deleted
                        ? ServerResponse.noContent().build()
                        : ServerResponse.notFound().build());
    }
}
