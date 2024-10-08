package com.systeminventory.systeminventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Role {
    @Id
    @EqualsAndHashCode.Include
    private Integer idRole;
    @Column(nullable = false,length = 100)
    private String Name;
    @Column(nullable = false,length = 150)
    private String desceiption;
}
