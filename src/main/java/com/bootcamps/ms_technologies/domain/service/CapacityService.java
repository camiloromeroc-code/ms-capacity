package com.bootcamps.ms_technologies.domain.service;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import com.bootcamps.ms_technologies.domain.port.out.CapacityRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CapacityService {

    private final CapacityRepositoryPort repository;

    public Mono<Capacity> createCapacity(Capacity capacity) {

        return validateCapacity(capacity)
                .then(repository.existsByName(capacity.name()))
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("La capacidad ya existe"));
                    }
                    return repository.save(capacity);
                });
    }

    private static Mono<Void> validateCapacity(Capacity capacity) {
        if (capacity.technologyIds() == null || capacity.technologyIds().size() < 3) {
            return Mono.error(new IllegalArgumentException("Debe asociar al menos 3 tecnologías"));
        }
        if (capacity.technologyIds().size() > 20) {
            return Mono.error(new IllegalArgumentException("No puede asociar más de 20 tecnologías"));
        }
        if (capacity.technologyIds().stream().distinct().count() != capacity.technologyIds().size()) {
            return Mono.error(new IllegalArgumentException("No se permiten tecnologías repetidas"));
        }
        return Mono.empty();
    }


    public Flux<Capacity> listCapacities() {
        return repository.findAll();
    }

    public Mono<Capacity> getCapacityById(Long id) {
        return repository.findById(id);
    }


    public Mono<Capacity> updateCapacity(Long id, Capacity capacity) {
        return repository.findById(id)
                .flatMap(existing -> {
                    Capacity updated = new Capacity(
                            id,
                            capacity.name(),
                            capacity.description(),
                            capacity.technologyIds()
                    );
                    return repository.save(updated);
                });
    }

    public Mono<Boolean> deleteCapacity(Long id) {
        return repository.findById(id)
                .flatMap(existing -> repository.deleteById(id).thenReturn(true))
                .defaultIfEmpty(false);
    }
}
