/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.systeminventory.systeminventory.model.Consult;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Alain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDetailDto {

    @EqualsAndHashCode.Include
    private Integer idConsultDetail;
    @JsonBackReference
    private ConsultDto consult;
    @NotNull
    private String diagnosis;
    @NotNull
    private String treatment;
    
}
