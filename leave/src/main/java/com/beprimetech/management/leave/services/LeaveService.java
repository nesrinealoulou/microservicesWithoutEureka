package com.beprimetech.management.leave.services;

import com.beprimetech.management.leave.Repository.LeaveRepository;
import com.beprimetech.management.leave.dto.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveService {

    @Autowired
    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public Leave addLeave(Leave leave, String id) {
        leave.setEmployeeId(id);
        leaveRepository.save(leave);
        return leave;
    }

    public void deleteLeave(String id, String leaveId) {

        leaveRepository.deleteLeaveByLeaveId(leaveId);
    }


    public Leave findLeaveById(String id) {
        return leaveRepository.findLeaveByLeaveId(id).get();
    }

    public List<Leave> findLeaves() {

        return leaveRepository.findAll();
    }
}







