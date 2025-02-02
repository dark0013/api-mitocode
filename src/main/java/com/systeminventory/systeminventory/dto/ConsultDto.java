/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.systeminventory.systeminventory.model.Medic;
import com.systeminventory.systeminventory.model.Patient;
import com.systeminventory.systeminventory.model.Specialty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Alain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDto {
@EqualsAndHashCode.Include
    private Integer idConsult;

    private Patient patient;

    private Medic medic;

    private Specialty specialty;

    private String numConsult;

    private LocalDateTime consultDate;
    @JsonManagedReference
    @NotNull
    private List<ConsultDetailDto> details;
}
