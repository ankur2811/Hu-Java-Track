package com.example.Assignment2.Models;

import javax.persistence.*;

@Entity
@Table
public class UserCourseMapping {

    @Id
    @SequenceGenerator(
            name="usercourse_sequence",
            sequenceName = "usercourse_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usercourse_sequence"
    )

    private Long id;

    @Column(name="userId")
    private Long userId;
    private Long courseId;
    private String status;

    public UserCourseMapping() {
    }

    public UserCourseMapping(Long id, Long userId, Long courseId, String status) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    public UserCourseMapping(Long userId, Long courseId, String status) {
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserCourseMapping{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", status='" + status + '\'' +
                '}';
    }
}
