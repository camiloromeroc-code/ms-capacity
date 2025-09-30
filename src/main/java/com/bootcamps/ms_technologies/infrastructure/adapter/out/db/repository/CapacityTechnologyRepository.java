package com.bootcamps.ms_technologies.infrastructure.adapter.out.db.repository;

import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity.CapacityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CapacityTechnologyRepository
        extends ReactiveCrudRepository<CapacityTechnologyEntity, Void> {

    Flux<CapacityTechnologyEntity> findByCapacityId(Long capacityId);

    Flux<CapacityTechnologyEntity> findByTechnologyId(Long technologyId);

    Mono<Void> deleteAllByCapacityId(Long capacityId);

}