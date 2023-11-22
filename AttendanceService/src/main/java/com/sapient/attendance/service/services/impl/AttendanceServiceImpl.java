package com.sapient.attendance.service.services.impl;

import com.sapient.attendance.service.entities.Attendance;
import com.sapient.attendance.service.repository.AttendanceRepository;
import com.sapient.attendance.service.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository repository;

    // Method to create a new attendance record
    @Override
    public Attendance create(Attendance attendance) {
        return repository.save(attendance);
    }

    // Method to get all attendance records
    @Override
    public List<Attendance> getAttendance() {
        return repository.findAll();
    }

    // Method to get all attendance records for a specific user
    @Override
    public List<Attendance> getAttendanceByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    // Method to create a swipe-in record for a user
    @Override
    public Attendance createSwipeIn(String userId, LocalDateTime swipeInTime) {
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setSwipeInTime(swipeInTime);
        return repository.save(attendance);
    }

    // Method to create a swipe-out record for a user
    @Override
    public Attendance createSwipeOut(String userId, LocalDateTime swipeOutTime) {
        // Find the latest attendance record for the user and update swipe-out time
        Attendance latestAttendance = repository.findTopByUserIdOrderBySwipeInTimeDesc(userId);
        if (latestAttendance != null) {
            latestAttendance.setSwipeOutTime(swipeOutTime);
            return repository.save(latestAttendance);
        } else {
            // Handle the case where no swipe-in record is found
            throw new RuntimeException("No corresponding swipe-in record found for user: " + userId);
        }
    }

    // Method to calculate total hours for a user on a specific date
    @Override
    public int calculateTotalHours(String userId, LocalDate date) {
        List<Attendance> attendances = repository.findByUserIdAndSwipeInTimeBetween(
                userId,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );

        int totalHours = 0;

        for (Attendance attendance : attendances) {
            LocalDateTime swipeInTime = attendance.getSwipeInTime();
            LocalDateTime swipeOutTime = attendance.getSwipeOutTime();

            if (swipeInTime != null && swipeOutTime != null) {
                totalHours += calculateHoursBetween(swipeInTime, swipeOutTime);
            }
        }

        return totalHours;
    }

    // Method to get all attendance records between two date-times
    @Override
    public List<Attendance> getAttendanceBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return repository.findBySwipeInTimeBetween(startDateTime, endDateTime);
    }

    // Method to update an existing attendance record
    @Override
    public void updateAttendance(Attendance attendance) {
        repository.save(attendance);
    }

    // Utility method to calculate hours between two date-times
    private int calculateHoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return (int) java.time.Duration.between(startDateTime, endDateTime).toHours();
    }
}
