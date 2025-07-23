package com.zidio.connect.repository;

import com.zidio.connect.entity.Application;
import com.zidio.connect.entity.Job;
import com.zidio.connect.entity.StudentProfile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
        Optional<Application> findByJobAndStudent(Job job, StudentProfile student);
            List<Application> findByStudent(StudentProfile student);
            List<Application> findByJob(Job job);


}