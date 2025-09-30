package com.bootcamps.ms_technologies.domain.model;

import java.util.List;

public record Capacity(
        Long id,
        String name,
        String description,
        List<Long> technologyIds
) {
}


