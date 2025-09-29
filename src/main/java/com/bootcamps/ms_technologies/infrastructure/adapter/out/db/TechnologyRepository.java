package com.bootcamps.ms_technologies.infrastructure.adapter.out.db;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {
    Mono<TechnologyEntity> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
