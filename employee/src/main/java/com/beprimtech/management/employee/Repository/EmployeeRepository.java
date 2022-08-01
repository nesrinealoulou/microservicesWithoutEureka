package com.beprimtech.management.employee.Repository;

import com.beprimtech.management.employee.dto.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findEmployeById(String id);

    List<Employee> findEmployeeByInformation_Email(String email);

    List<Employee> findEmployeesByIsArchived(boolean isArchived);

}
