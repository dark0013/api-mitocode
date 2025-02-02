/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.repository;

import com.systeminventory.systeminventory.model.ConsultExam;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alain
 */
public interface IConsultExamRepo extends IGenericRepo<ConsultExam, Integer> {

    /*
    nativeQuery = true -> me permite usar un query tal cual lo usaria en la base de datos
    nativeQuery = false -> no me permite usar un query tal cual lo usaria en la base de datos
    @Query ->  solo retorna registro y no funciona en update, insert, delete porque da un error indicando "org.postgresql.util.PSQLException: La consulta no retornó ningún resultado." 
    @Modifying -> se utiliza para poder usar "@Query" sin que de un error al momento de realizar un insert, delete, update
    @Transactional -> convierte en transactional todo lo que esta abajo de el
    */
//    @Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult,id_exam) VALUES(:idConsult,:idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
}
