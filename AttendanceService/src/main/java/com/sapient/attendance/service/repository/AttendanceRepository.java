package com.sapient.attendance.service.repository;

import com.sapient.attendance.service.entities.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    List<Attendance> findByUserId(String userId);

    Attendance findTopByUserIdOrderBySwipeInTimeDesc(String userId);

    List<Attendance> findByUserIdAndSwipeInTimeBetween(String userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Attendance> findBySwipeInTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
