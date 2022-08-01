package com.beprimtech.management.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EmployeeDb")
@Builder(toBuilder = true)
public class Employee {
    @Id
    private String id;
    Information information;
    private Boolean isArchived;
    @Builder.Default
    private List<Leave> leavesList = Lists.newArrayList();

}
