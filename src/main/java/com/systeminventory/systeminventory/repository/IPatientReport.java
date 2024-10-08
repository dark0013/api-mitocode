package com.systeminventory.systeminventory.repository;

import com.systeminventory.systeminventory.model.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientReport extends IGenericRepo<Patient, Integer> {
//    @Query("SELECT p.firstName, p.lastName FROM Patient p")
//    List<Object[]> nombres(); 
}
