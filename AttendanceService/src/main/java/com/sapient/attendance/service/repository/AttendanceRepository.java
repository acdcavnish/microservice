package com.sapient.attendance.service.repository;

import com.sapient.attendance.service.entities.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttendanceRepository extends MongoRepository<Attendance,String> {


    //custom finder method

    List<Attendance> findByUserId(String userId);


}
