/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services;

import com.systeminventory.systeminventory.model.Consult;
import com.systeminventory.systeminventory.model.Exam;
import java.util.List;

/**
 *
 * @author Alain
 */
public interface IConsultService extends ICRUD<Consult, Integer> {
    //aqui para crear mis metodos personalizados  

    Consult saveTransactional(Consult consult, List<Exam> lstExam);
}
