package com.bootcamps.ms_technologies.domain.port.out;

import com.bootcamps.ms_technologies.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyRepositoryPort {

    Mono<Technology> save(Technology technology);

    Flux<Technology> findAll();

    Mono<Technology> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsByName(String name);
}

