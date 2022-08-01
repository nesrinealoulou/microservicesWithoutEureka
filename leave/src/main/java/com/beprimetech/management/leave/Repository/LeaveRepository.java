package com.beprimetech.management.leave.Repository;


import com.beprimetech.management.leave.dto.Leave;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface LeaveRepository extends MongoRepository<Leave, String> {
    Optional<Leave> findLeaveByLeaveId(String id);

    void deleteLeaveByLeaveId(String id);
}
