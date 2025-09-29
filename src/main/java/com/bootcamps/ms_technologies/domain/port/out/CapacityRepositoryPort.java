package com.bootcamps.ms_technologies.domain.port.out;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacityRepositoryPort {

    Mono<Capacity> save(Capacity capacity);

    Flux<Capacity> findAll();

    Mono<Capacity> findById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<Boolean> existsByName(String name);
}

