package com.bootcamps.ms_technologies.infrastructure.adapter.out.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "capacity_technology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapacityTechnologyEntity {
    @Column("capacity_id")
    private Long capacityId;

    @Column("technology_id")
    private Long technologyId;
}

