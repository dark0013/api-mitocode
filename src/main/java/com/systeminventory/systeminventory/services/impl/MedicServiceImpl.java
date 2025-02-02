/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;


import com.systeminventory.systeminventory.model.Medic;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.repository.IMedicRepo;

import com.systeminventory.systeminventory.services.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alain
 */
@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {

    private final IMedicRepo repor;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repor;
    }

}
