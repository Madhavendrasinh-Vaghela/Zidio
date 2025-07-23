package com.zidio.connect.repository;

import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {


    StudentProfile save(User profile);
     Optional<StudentProfile> findByUserId(Long userId);

     User findByUser(String email);

     StudentProfile findByUser(User user);

     StudentProfile findByUserEmail(String email);

     StudentProfile findByFullName(String fullName);

     StudentProfile findByResumeUrl(String resumeUrl);

     StudentProfile findByEducation(String education);

     StudentProfile findBySkills(String skills);

     StudentProfile findByLocation(String location);

        
}