package com.systeminventory.systeminventory.repository;

import com.systeminventory.systeminventory.model.Specialty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISpecialtyRepo extends IGenericRepo<Specialty, Integer> {

}
