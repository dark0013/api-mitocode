/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;

import com.systeminventory.systeminventory.model.Patient;
import com.systeminventory.systeminventory.model.Specialty;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.repository.IPatientReport;
import com.systeminventory.systeminventory.repository.ISpecialtyRepo;
import com.systeminventory.systeminventory.services.IPatientService;
import com.systeminventory.systeminventory.services.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alain
 */
@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {

    private final ISpecialtyRepo repor;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repor;
    }

}
