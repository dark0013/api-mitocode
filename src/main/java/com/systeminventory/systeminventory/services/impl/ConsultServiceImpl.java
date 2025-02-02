/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;

import com.systeminventory.systeminventory.model.Consult;
import com.systeminventory.systeminventory.model.Exam;
import com.systeminventory.systeminventory.repository.IConsultExamRepo;
import com.systeminventory.systeminventory.repository.IConsultRepo;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.services.IConsultService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alain
 */
@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo consultRepo;
    private final IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    /*
     @Transactional -> se lo coloca en un nivel superior para que sea transaccional
     - se puede agregar en el metodo o en la clase
     */
    @Override
    @Transactional
    public Consult saveTransactional(Consult consult, List<Exam> lstExam) {
        consultRepo.save(consult);
        lstExam.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));
        return consult;
    }

}
