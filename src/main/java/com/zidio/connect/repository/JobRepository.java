package com.zidio.connect.repository;

import com.zidio.connect.entity.Job;
import com.zidio.connect.entity.RecruiterProfile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

     @Query("SELECT j FROM Job j WHERE j.title LIKE %:keyword% OR j.description LIKE %:keyword%")
    List<Job> searchByKeyword(@Param("keyword") String keyword);

     @Query("SELECT j FROM Job j WHERE j.company.name = :companyName")
    List<Job> findByCompanyName(@Param("companyName") String companyName);
        @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> findByLocation(@Param("location") String location);
        @Query("SELECT j FROM Job j WHERE j.type = :type")
    List<Job> findByType(@Param("type") String type);   
    
     @Query("SELECT j FROM Job j WHERE j.salary >= :minSalary AND j.salary <= :maxSalary")  
    List<Job> findBySalaryRange(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);

        @Query("SELECT j FROM Job j WHERE j.postedDate >= :startDate AND j.postedDate <= :endDate") 
    List<Job> findByPostedDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT j FROM Job j WHERE j.skillsRequired LIKE %:skill%")
    List<Job> findBySkillsRequired(@Param("skill") String skill);

    @Query("SELECT j FROM Job j WHERE j.educationRequired LIKE %:education%")
    List<Job> findByEducationRequired(@Param("education") String education);
    
    @Query("SELECT j FROM Job j WHERE j.experienceRequired LIKE %:experience%")
    List<Job> findByExperienceRequired(@Param("experience") String experience);

    @Query("SELECT j FROM Job j WHERE j.jobCategory.name = :categoryName")
    List<Job> findByJobCategory(@Param("categoryName") String categoryName);

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId")
    List<Job> findByUserId(@Param("userId") Long userId);

    Job findByTitle(@Param("title") String title);
    @Query("SELECT j FROM Job j WHERE j.description = :description")
    Job findByDescription(@Param("description") String description);

    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    List<Job> findByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT j FROM Job j WHERE j.user.email = :email")
    List<Job> findByUserEmail(@Param("email") String email);

    @Query("SELECT j FROM Job j WHERE j.user.fullName = :fullName")
    List<Job> findByUserFullName(@Param("fullName") String fullName);

    @Query("SELECT j FROM Job j WHERE j.user.resumeUrl = :resumeUrl")
    List<Job> findByUserResumeUrl(@Param("resumeUrl") String resumeUrl);

    @Query("SELECT j FROM Job j WHERE j.user.education = :education")
    List<Job> findByUserEducation(@Param("education") String education);

    @Query("SELECT j FROM Job j WHERE j.user.skills = :skills")
    List<Job> findByUserSkills(@Param("skills") String skills);

    @Query("SELECT j FROM Job j WHERE j.user.location = :location")
    List<Job> findByUserLocation(@Param("location") String location);  

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId AND j.title LIKE %:keyword%")
    List<Job> searchByUserIdAndKeyword(@Param("userId") Long userId, @Param("keyword") String keyword);

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId AND j.location = :location")
    List<Job> findByUserIdAndLocation(@Param("userId    ") Long userId, @Param("location") String location);

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId AND j.type = :type")
    List<Job> findByUserIdAndType(@Param("userId    ") Long userId, @Param("type") String type);    

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId AND j.salary >= :minSalary AND j.salary <= :maxSalary")
    List<Job> findByUserIdAndSalaryRange(@Param("userId") Long userId, @Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);

    List<Job> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String keyword2);

    List<Job> findByTypeIgnoreCase(String type);

    List<Job> findByLocationContainingIgnoreCase(String location);

    List<Job> findBySalaryBetween(Double minSalary, Double maxSalary);

    List<Job> findByPostedDateBetween(String startDate, String endDate);

    List<Job> findBySkillsContainingIgnoreCase(String skill);

    List<Job> findByEducationRequiredContainingIgnoreCase(String education);

    List<Job> findByExperienceRequiredContainingIgnoreCase(String experience);

    List<Job> findByCategoryNameIgnoreCase(String categoryName);

    List<Job> findByDescriptionContainingIgnoreCase(String description);

    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findByResumeUrl(String resumeUrl);

    List<Job> findByRecruiter(RecruiterProfile recruiterProfile);
    
}