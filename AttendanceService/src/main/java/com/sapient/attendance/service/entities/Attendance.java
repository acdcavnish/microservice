package com.sapient.attendance.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("user_attendance")
public class Attendance {
    @Id
    private String attendanceId;
    private String userId;
    private int attendance;
    private String remark;
}
