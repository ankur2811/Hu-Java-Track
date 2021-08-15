package com.example.Assignment2.Controller;


import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.CourseRepository;
import com.example.Assignment2.Repository.UserRepository;
import com.example.Assignment2.Service.UserCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/cart")
public class UserCourseMappingController
{
    private final UserCourseMappingService userCourseMappingService;
    private final UserRepository userService;
    private final CourseRepository courseService;

    @Autowired
    public UserCourseMappingController(UserCourseMappingService userCourseMappingService,UserRepository userService,CourseRepository courseService) {
        this.userCourseMappingService = userCourseMappingService;
        this.courseService=courseService;
        this.userService=userService;
    }

    //Controller to get the details of user-course mapping details
    @GetMapping
    public List<UserCourseMapping> GeUserCourseMappings(){
        try {
            return userCourseMappingService.GetUserCourseMappings();
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mapping not present.");
        }
    }


    //Controller to add a new mapping
    @PostMapping
    public void registerNewUserCourseMapping(@RequestBody UserCourseMapping userCourseMapping)
    {
        if(userCourseMapping.getCourseId()==null || userCourseMapping.getUserId()==null)
        {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"mapping data insufficient");
        }
        long userid=userCourseMapping.getUserId();
        long courseid=userCourseMapping.getCourseId();
        if(!courseService.existsById(courseid) || !userService.existsById(userid))
        {
            System.out.println("error review");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"incorrect data");
        }
        userCourseMappingService.addNewUserCourseMapping(userCourseMapping);
    }


    //Controller to get the mapping by id
    @GetMapping(path="{usercourseId}")
    public UserCourseMapping getUsercourseMappingById(@PathVariable("usercourseId") Long id)
    {

        System.out.println("hii");
        try {
            return userCourseMappingService.getUserCourseMappingById(id);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mapping with id"+id+ "not present.");
        }
    }


    //Controller to change the status of particular course of a particular user
    @PutMapping(path="{usercourseId}")
    public void updateUserCourseMapping( @PathVariable("usercourseId") Long userId,
                            @RequestParam(required = false) String status
                           )
    {

        try {
            userCourseMappingService.updateCourseStatus(userId, status);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mapping with id"+userId+ "not present.");
        }
    }
}
