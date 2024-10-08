package com.systeminventory.systeminventory.services;

import com.systeminventory.systeminventory.model.Patient;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

/*
* este codigo es sin la refactorización
**/
public interface IPatientServices {

    Patient save(Patient patient);

    Patient update(Patient patient);

    void delete(Integer id);

    List<Patient> findAll();

    Patient findbyId(Integer id);

//    List<Object[]> nombrePacientes();;
}
