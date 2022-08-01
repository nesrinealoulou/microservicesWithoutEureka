package com.beprimetech.management.leave.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beprimetech.management.leave.dto.Leave;
import com.beprimetech.management.leave.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @PostMapping(value = "/add/{id}")
    public Leave addLeave(@RequestBody Leave leave, @PathVariable String id) {
        return leaveService.addLeave(leave, id);
    }

    @GetMapping("/leaves/{id}")
    public List<Leave> getLeavesByEmployeeId(@PathVariable("id") String id) {
        Map<String, List<Leave>> orderMap = new HashMap<>();
        List<Leave> leavesList = this.findLeaves();
        leavesList.forEach(leave ->
                orderMap.put(leave.getEmployeeId(), leavesList));
        System.out.println(orderMap);
        return orderMap.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLeave(@PathVariable String id, @RequestParam String leaveId) {
        leaveService.deleteLeave(id, leaveId);
    }

    @GetMapping(value = "/leave/{id}")
    public Leave findLeaveByEmployeeId(@PathVariable String id) {
        return leaveService.findLeaveById(id);
    }

    @GetMapping(value = "/leaves")
    public List<Leave> findLeaves() {
        return leaveService.findLeaves();
    }


}
