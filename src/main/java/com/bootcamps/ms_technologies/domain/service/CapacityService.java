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
                        return Mono.error(new IllegalArgumentException("La tecnología ya existe"));
                    }
                    return repository.save(capacity);
                });
    }

    private static Mono<Void> validateCapacity(Capacity capacity) {
        if (capacity.name() == null || capacity.name().isBlank() || capacity.name().length() > 50) {
            return Mono.error(new IllegalArgumentException("Nombre obligatorio y máximo 50 caracteres"));
        }
        if (capacity.description() == null || capacity.description().isBlank() || capacity.description().length() > 90) {
            return Mono.error(new IllegalArgumentException("Descripción obligatoria y máximo 90 caracteres"));
        }
        return Mono.empty();
    }


    public Flux<Capacity> listTechnologies() {
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
                            capacity.description()
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
