package com.sapient.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    private String attendanceId;
    private String userId;
    private String employeeId;
    private int hours;
    private String remark;

}
