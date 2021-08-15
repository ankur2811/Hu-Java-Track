package com.example.Assignment2.Controller;


import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Repository.CourseRepository;
import com.example.Assignment2.Repository.UserRepository;
import com.example.Assignment2.Service.ReviewMappingService;
import com.example.Assignment2.Service.UserService;
import com.example.Assignment2.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/review")
public class ReviewMappingController {

    private final ReviewMappingService reviewMappingService;
    private final UserRepository userService;
    private final CourseRepository courseService;

    @Autowired
    public ReviewMappingController(ReviewMappingService reviewMappingService,UserRepository userService,CourseRepository courseService) {
        this.reviewMappingService = reviewMappingService;
        this.courseService=courseService;
        this.userService=userService;
    }

    //Controller to the all the review mapping details
    @GetMapping
    public List<ReviewMapping> GetReviewMappings(){
        try {
            return reviewMappingService.GetReviewMappings();
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"reviews not present.");
        }
    }

    //Controller to add a new review to a specific course by a specific user
    @PostMapping
    public void registerNewReviewMapping(@RequestBody ReviewMapping reviewMapping)
    {

        if(reviewMapping.getCourseId()==null || reviewMapping.getUserId()==null || reviewMapping.getRating()==0)
        {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"review data insufficient");
        }
        long userid=reviewMapping.getUserId();
        long courseid=reviewMapping.getCourseId();
        if(!courseService.existsById(courseid) || !userService.existsById(userid))
        {
            System.out.println("error review");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"incorrect data");
        }
        reviewMappingService.addNewReviewMapping(reviewMapping);
    }

    //Controller to get the specific review detail by id
    @GetMapping(path="{reviewId}")
    public ReviewMapping getReviewMappingById(@PathVariable("reviewId") Long id)
    {

        try {
            return reviewMappingService.getReviewMappingById(id);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"review with id"+id+ "not present.");
        }
    }

}
