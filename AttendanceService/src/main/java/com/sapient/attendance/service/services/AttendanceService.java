package com.sapient.attendance.service.services;

import com.sapient.attendance.service.entities.Attendance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AttendanceService {

    Attendance create(Attendance attendance);

    List<Attendance> getAttendance();

    List<Attendance> getAttendanceByUserId(String userId);

    Attendance createSwipeIn(String userId, LocalDateTime swipeInTime);

    Attendance createSwipeOut(String userId, LocalDateTime swipeOutTime);

    int calculateTotalHours(String userId, LocalDate date);

    List<Attendance> getAttendanceBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    void updateAttendance(Attendance attendance);
}
