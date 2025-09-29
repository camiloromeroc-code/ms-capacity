package com.bootcamps.ms_technologies.infrastructure.adapter.out.db.repository;

import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity.CapacityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface CapacityRepository extends ReactiveCrudRepository<CapacityEntity, Long> {
    Mono<CapacityEntity> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
