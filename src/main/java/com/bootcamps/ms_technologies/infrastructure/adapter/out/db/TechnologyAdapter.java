package com.bootcamps.ms_technologies.infrastructure.adapter.out.db;

import com.bootcamps.ms_technologies.domain.model.Technology;
import com.bootcamps.ms_technologies.domain.port.out.TechnologyRepositoryPort;
import com.bootcamps.ms_technologies.infrastructure.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TechnologyAdapter implements TechnologyRepositoryPort {

    private final TechnologyRepository technologyRepository;
    private final TechnologyMapper mapper;

    @Override
    public Mono<Technology> save(Technology technology) {
        return technologyRepository.save(mapper.toEntity(technology))
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Technology> findAll() {
        return technologyRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Technology> findById(Long id) {
        return technologyRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return technologyRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name);
    }
}
