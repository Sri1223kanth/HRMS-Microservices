package com.eazybytes.Repsoitory;

import com.eazybytes.Entity.SalaryStructure;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure,Long> {


     Optional<SalaryStructure> findByEmployeeId(Long EmployeeId) ;


     boolean existsByEmployeeId(Long employeeId);
}
