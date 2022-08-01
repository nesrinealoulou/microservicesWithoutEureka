package com.beprimtech.management.employee.feignclients;



import com.beprimtech.management.employee.dto.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "leave-service", url = "http://localhost:8002/")
public interface LeaveFeignClients {

    @GetMapping(value = "/leave/leaves/{id}")
    List<Leave> getLeavesByEmployeeId(@PathVariable("id") String id);

    @PostMapping(value = "/leave/add/{id}")
    Leave addLeaveToEmployeeLeaveList(@RequestBody Leave leave, @PathVariable("id") String id);

    @DeleteMapping(value = "/leave/delete/{id}")
    Leave deleteLeaveFromLeaveList(@PathVariable("id") String id, @RequestParam String leaveId);

}
