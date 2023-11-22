package com.sapient.attendance.service.controllers;

import com.sapient.attendance.service.entities.Attendance;
import com.sapient.attendance.service.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/swipe-in")
    public ResponseEntity<Attendance> createSwipeIn(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.createSwipeIn(userId, LocalDateTime.now()));
    }

    @PostMapping("/swipe-out")
    public ResponseEntity<Attendance> createSwipeOut(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.createSwipeOut(userId, LocalDateTime.now()));
    }

    @GetMapping("/total-hours/{userId}")
    public ResponseEntity<Integer> calculateTotalHours(@PathVariable String userId) {
        return ResponseEntity.ok(attendanceService.calculateTotalHours(userId, LocalDateTime.now().toLocalDate()));
    }

    @PostMapping
    public ResponseEntity<Attendance> create(@RequestBody Attendance attendance){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.create(attendance));
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> getAttendance(){
        return ResponseEntity.ok(attendanceService.getAttendance());
    }
    @RequestMapping("/users/{userId}")
    public ResponseEntity<List<Attendance>> getAttendanceByUserId(@PathVariable String userId){
        return ResponseEntity.ok(attendanceService.getAttendanceByUserId(userId));
    }

}
