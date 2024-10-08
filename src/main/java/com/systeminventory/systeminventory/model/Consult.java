package com.systeminventory.systeminventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;

    @ManyToOne//-> relacion foranea de muchos a uno
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))//-> es el nombre que se le da a la relacion de la columna
    private Patient patient;//->permite JPQL Java Persistence Query Lenguaje / from patient p where p.dni = ?

    @ManyToOne
    @JoinColumn(name = "id_medic",nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_MEDIC"))
    private Medic medic;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false,foreignKey = @ForeignKey(name = "FK_CONSULT_SPECIALTY"))
    private Specialty specialty;

    @Column(length = 3,nullable = false)
    private String numConsult;

    @Column(nullable = false) //ISODATE yyyy-mm-ddTHH:mm:ss
    private LocalDateTime consultDate;



    /**
     * REFERENCIA MAESTRO DETALLE
     * NOTA
     * mappedBy = "consult" -> nombre de la fefrenci que esta en ConsultDetail
     * cascade = {CascadeType.ALL} -> lo que le pasa al padre le pasa al detalle
     * orphanRemoval = true -> para remover ciertos elementos del detalle
     * orphanRemoval = false -> para que no quede sin padre los hijos,
     *        no permite eliminar los detalles porque no puede dejar huerfanos los registros
     * por defecto es  orphanRemoval = false
     *
     * fetch = FetchType.LAZY -> por defecto
     */
    @OneToMany(mappedBy = "consult", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)//
     private List<ConsultDetail> details;


}

