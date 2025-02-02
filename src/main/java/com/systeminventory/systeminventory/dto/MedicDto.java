/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class MedicDto {

    private Integer idMedic;
    @NotNull
    @NotEmpty
    private String primaryname;
    @NotNull
    @NotEmpty
    private String surname;
//    @NotNull
//    @NotEmpty
    private String cmpmedic;
    @NotNull
    @NotEmpty
    private String photo;
}
