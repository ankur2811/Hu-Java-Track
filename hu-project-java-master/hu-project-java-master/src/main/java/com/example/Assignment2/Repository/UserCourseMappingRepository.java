package com.example.Assignment2.Repository;

import com.example.Assignment2.Models.User;
import com.example.Assignment2.Models.UserCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserCourseMappingRepository extends JpaRepository<UserCourseMapping,Long> {
    @Query("SELECT s FROM UserCourseMapping s WHERE s.userId=?1")
    List<UserCourseMapping> findmapbyuserid(Long displayname);

    @Query("SELECT s FROM UserCourseMapping s WHERE s.courseId=?1")
    List<UserCourseMapping> findmapbycourseid(Long displayname);
}
