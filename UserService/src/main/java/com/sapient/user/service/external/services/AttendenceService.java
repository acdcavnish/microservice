package com.sapient.user.service.external.services;

import com.sapient.user.service.entities.Attendance;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@EnableEurekaClient
public interface AttendenceService {

    @GetMapping("/attendance/{attendanceId}")
    Attendance getAttendance(@PathVariable("attendanceId") String attendanceId);

}
