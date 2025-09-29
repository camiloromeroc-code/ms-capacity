package com.bootcamps.ms_technologies.infrastructure.adapter.out.db;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import com.bootcamps.ms_technologies.domain.port.out.CapacityRepositoryPort;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.repository.CapacityRepository;
import com.bootcamps.ms_technologies.infrastructure.mapper.CapacityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CapacityAdapter implements CapacityRepositoryPort {

    private final CapacityRepository capacityRepository;
    private final CapacityMapper mapper;

    @Override
    public Mono<Capacity> save(Capacity capacity) {
        return capacityRepository.save(mapper.toEntity(capacity))
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Capacity> findAll() {
        return capacityRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Capacity> findById(Long id) {
        return capacityRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return capacityRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return capacityRepository.existsByName(name);
    }
}
