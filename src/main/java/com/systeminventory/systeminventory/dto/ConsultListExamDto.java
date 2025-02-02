/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Alain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDto {

    @NotNull
    private ConsultDto consult;
    @NotNull
    private List<ExamDto> lstExam;
}
