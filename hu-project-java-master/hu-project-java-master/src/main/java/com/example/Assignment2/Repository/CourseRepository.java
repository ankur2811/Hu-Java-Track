package com.example.Assignment2.Repository;

import com.example.Assignment2.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT s FROM Course s WHERE s.name=?1")
    Optional<Course> findCourseByName(String name);
}
