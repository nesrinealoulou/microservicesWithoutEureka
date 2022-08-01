package com.beprimtech.management.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "LeaveDb")
@Builder(toBuilder = true)
public class Leave {
    @Id
    private String leaveId;
    private String employeeId;
    @Transient
    private int numeroLeave;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate creationDate;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate departureDate;
    @JsonFormat(locale = "MM/DD/YYYY")
    private LocalDate returnDate;
    @Transient
    private long nbDaysLeave = 0;
    private String reasonLeave;
    private String leaveReasonArea;
    //private State stateLeave;
//        private Boolean isEnAttente;

}
