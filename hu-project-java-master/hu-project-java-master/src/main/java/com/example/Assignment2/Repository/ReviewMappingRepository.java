package com.example.Assignment2.Repository;

import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Models.UserCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewMappingRepository extends JpaRepository<ReviewMapping,Long> {
    @Query("SELECT s FROM ReviewMapping s WHERE s.courseId=?1")
    List<ReviewMapping> findmapbycourseid(Long displayname);

    @Query("SELECT s FROM ReviewMapping s WHERE s.userId=?1")
    List<ReviewMapping> findmapbyuserid(Long displayname);
}
