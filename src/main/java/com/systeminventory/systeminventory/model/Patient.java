package com.systeminventory.systeminventory.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Integer idPatient;
    @Column(length = 100,nullable = false)
    private String firstName;
    @Column(length = 100,nullable = false)
    private String lastName;
    @Column(length = 21,nullable = false)
    private String dni;
    @Column(length = 200,nullable = false)
    private String address;
    @Column(length = 10,nullable = false)
    private String phone;
    @Column(length = 100,nullable = false)
    private String email;
}
