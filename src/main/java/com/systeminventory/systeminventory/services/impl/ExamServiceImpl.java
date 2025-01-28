/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;


import com.systeminventory.systeminventory.model.Exam;
import com.systeminventory.systeminventory.repository.IExamRepo;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.services.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alain
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    private final IExamRepo repor;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repor;
    }

}
