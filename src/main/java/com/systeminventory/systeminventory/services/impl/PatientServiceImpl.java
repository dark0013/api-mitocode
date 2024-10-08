/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;

import com.systeminventory.systeminventory.model.Patient;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.repository.IPatientReport;
import com.systeminventory.systeminventory.services.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alain
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService {

    private final IPatientReport repor;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repor;
    }

}
