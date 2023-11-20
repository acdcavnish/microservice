package com.sapient.attendance.service.services.impl;

import com.sapient.attendance.service.entities.Attendance;
import com.sapient.attendance.service.repository.AttendanceRepository;
import com.sapient.attendance.service.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository repository;

    @Override
    public Attendance create(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendance() {
        return repository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
