package com.systeminventory.systeminventory.services.impl;

import com.systeminventory.systeminventory.exception.NewModelNotFoundException;
import com.systeminventory.systeminventory.model.Patient;
import com.systeminventory.systeminventory.repository.IPatientReport;
import com.systeminventory.systeminventory.services.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServicesImpl implements IPatientService {

    @Autowired
    private final IPatientReport repo;

    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient, Integer id) {
        return repo.save(patient);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findbyId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND" + id));
    }

//    @Override
//    public List<Object[]> nombrePacientes() {
//        return repo.nombres(); // Cambia a List<Object[]>
//    }

}
