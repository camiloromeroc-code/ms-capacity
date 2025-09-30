package com.bootcamps.ms_technologies.infrastructure.mapper;

import com.bootcamps.ms_technologies.domain.model.Capacity;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity.CapacityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CapacityMapper {

    Capacity toDomain(CapacityEntity entity);

    CapacityEntity toEntity(Capacity domain);
}


