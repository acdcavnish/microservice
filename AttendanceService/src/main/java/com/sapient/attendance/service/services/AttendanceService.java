package com.sapient.attendance.service.services;

import com.sapient.attendance.service.entities.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {

    //create attendance

    Attendance create(Attendance attendance);

    //get total attendance this week
    List<Attendance> getAttendance();


    //get all attendance by UserId

    List<Attendance> getAttendanceByUserId(String UserId);



}
