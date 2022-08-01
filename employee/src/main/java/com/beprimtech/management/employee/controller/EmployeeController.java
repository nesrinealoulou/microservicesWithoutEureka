package com.beprimtech.management.employee.controller;

import com.beprimtech.management.employee.dto.Employee;
import com.beprimtech.management.employee.dto.Leave;
import com.beprimtech.management.employee.feignclients.LeaveFeignClients;
import com.beprimtech.management.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService empService;
    @Autowired
    private final LeaveFeignClients leaveFeignClients;


    public EmployeeController(EmployeeService empService, LeaveFeignClients leaveFeignClients) {
        this.empService = empService;
        this.leaveFeignClients = leaveFeignClients;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = empService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Employee>> getAllArchivedEmployees() {
        List<Employee> employees = empService.findAllArchivedEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
        Employee employee = empService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = empService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = empService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
        empService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/archive/{id}")
    public ResponseEntity<?> archiveEmployee(@PathVariable("id") String id) {
        empService.archiveEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/restore/{id}")
    public ResponseEntity<?> restoreEmployee(@PathVariable("id") String id) {
        empService.restoreEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // from leave-microService
    @GetMapping("/from-leave/leaves/{id}")
    public List<Leave> getLeavesByEmployeeId(@PathVariable("id") String id) {
        System.out.println(leaveFeignClients.getLeavesByEmployeeId(id));
        return leaveFeignClients.getLeavesByEmployeeId(id);
    }

    @PostMapping(value = "/from-leave/add/{id}")
    public Employee addLeaveToEmployeeLeaveList(@RequestBody Leave leave, @PathVariable("id") String id) {
        return empService.addLeaveToEmployeeLeaveList(leave, id);
    }
    /*@DeleteMapping( value = "/from-leave/delete/{id}")
    public Leave deleteLeaveFromLeaveList(@PathVariable("id") String id , @RequestParam String leaveId) {
        with mongo template (update)
    }*/
}
