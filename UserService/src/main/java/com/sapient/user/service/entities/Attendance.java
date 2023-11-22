package com.sapient.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    private String attendanceId;
    private String userId;
    private LocalDateTime swipeInTime;
    private LocalDateTime swipeOutTime;
    private int attendance;
    private String remark;

}
