package com.beprimtech.management.employee.services;


import com.beprimtech.management.employee.Repository.EmployeeRepository;
import com.beprimtech.management.employee.dto.Employee;
import com.beprimtech.management.employee.dto.Leave;
import com.beprimtech.management.employee.feignclients.LeaveFeignClients;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepository;

    @Autowired
    private final LeaveFeignClients leaveFeignClients;


    public EmployeeService(EmployeeRepository empRepository, LeaveFeignClients leaveFeignClients) {
        this.empRepository = empRepository;
        this.leaveFeignClients = leaveFeignClients;
    }

    // employee crud
    public Employee addEmployee(Employee employee) {
        return empRepository.save(employee);


    }

    public List<Employee> findAllEmployees() {
        List<Employee> employees = Lists.newArrayList();
        employees.addAll(empRepository.findEmployeesByIsArchived(false));

        return employees;
    }

    public List<Employee> findAllArchivedEmployees() {
        List<Employee> employees = Lists.newArrayList();
        employees.addAll(empRepository.findEmployeesByIsArchived(true));
        return employees;
    }

    public Employee updateEmployee(Employee employee) {
        Employee employee1 = this.findEmployeeById(employee.getId());
        employee1 = employee;
        if (empRepository.findEmployeeByInformation_Email(employee1.getInformation().getEmail()).size() == 1) {
            return empRepository.save(employee1);
        } else {
            return null;
        }
    }

    public Employee findEmployeeById(String id) {
        return empRepository.findEmployeById(id).orElseThrow(
                () -> new UserNotFoundException("user by id" + id + "not found"));
    }

    public void deleteEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        empRepository.delete(employee);
    }

    public void archiveEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        employee.setIsArchived(true);
        empRepository.save(employee);
    }

    public void restoreEmployee(String id) {
        Employee employee = this.findEmployeeById(id);
        employee.setIsArchived(false);
        empRepository.save(employee);
    }

    public Employee addLeaveToEmployeeLeaveList(Leave leave, String id) {
        Leave nvLeave = leaveFeignClients.addLeaveToEmployeeLeaveList(leave, id);
        Employee employee = this.findEmployeeById(id);
        List<Leave> LeaveList = employee.getLeavesList();
        LeaveList.add(nvLeave);
        employee.setLeavesList(LeaveList);
        return empRepository.save(employee);
    }


}
