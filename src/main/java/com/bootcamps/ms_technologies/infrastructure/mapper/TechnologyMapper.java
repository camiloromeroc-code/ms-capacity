package com.bootcamps.ms_technologies.infrastructure.mapper;

import com.bootcamps.ms_technologies.domain.model.Technology;
import com.bootcamps.ms_technologies.infrastructure.adapter.out.db.TechnologyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {

    Technology toDomain(TechnologyEntity entity);

    TechnologyEntity toEntity(Technology domain);
}


