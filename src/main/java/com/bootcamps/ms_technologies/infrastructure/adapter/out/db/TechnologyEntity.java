package com.bootcamps.ms_technologies.infrastructure.adapter.out.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "capacity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyEntity {
    @Id
    private Long id;
    @Column("nombre")
    private String name;

    @Column("descripcion")
    private String description;
}