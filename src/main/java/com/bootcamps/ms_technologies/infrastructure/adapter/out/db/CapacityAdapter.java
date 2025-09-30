package com.bootcamps.ms_technologies.infrastructure.adapter.out.db;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import com.bootcamps.ms_technologies.domain.port.out.CapacityRepositoryPort;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity.CapacityEntity;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity.CapacityTechnologyEntity;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.repository.CapacityRepository;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.repository.CapacityTechnologyRepository;
import com.bootcamps.ms_technologies.infrastructure.mapper.CapacityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CapacityAdapter implements CapacityRepositoryPort {

    private final CapacityRepository capacityRepository;
    private final CapacityMapper mapper;
    private final CapacityTechnologyRepository capacityTechnologyRepository;

    @Override
    public Mono<Capacity> save(Capacity capacity) {
        CapacityEntity entity = mapper.toEntity(capacity);

        return capacityRepository.save(entity)
                .flatMap(saved -> {
                    List<CapacityTechnologyEntity> relations = capacity.technologyIds().stream()
                            .map(techId -> new CapacityTechnologyEntity(saved.getId(), techId))
                            .toList();

                    return capacityTechnologyRepository.saveAll(relations)
                            .then(Mono.just(
                                    new Capacity(
                                            saved.getId(),
                                            saved.getName(),
                                            saved.getDescription(),
                                            capacity.technologyIds()
                                    )
                            ));
                });
    }

    @Override
    public Flux<Capacity> findAll() {
        return capacityRepository.findAll()
                .flatMap(entity ->
                        capacityTechnologyRepository.findByCapacityId(entity.getId())
                                .map(CapacityTechnologyEntity::getTechnologyId)
                                .collectList()
                                .map(techIds -> new Capacity(
                                        entity.getId(),
                                        entity.getName(),
                                        entity.getDescription(),
                                        techIds
                                ))
                );
    }


    @Override
    public Mono<Capacity> findById(Long id) {
        return capacityRepository.findById(id)
                .flatMap(entity ->
                        capacityTechnologyRepository.findByCapacityId(entity.getId())
                                .map(CapacityTechnologyEntity::getTechnologyId)
                                .collectList()
                                .map(techIds -> new Capacity(
                                        entity.getId(),
                                        entity.getName(),
                                        entity.getDescription(),
                                        techIds
                                ))
                );
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return capacityTechnologyRepository.deleteAllByCapacityId(id)
                .then(capacityRepository.deleteById(id));
    }


    @Override
    public Mono<Boolean> existsByName(String name) {
        return capacityRepository.existsByName(name);
    }
}
