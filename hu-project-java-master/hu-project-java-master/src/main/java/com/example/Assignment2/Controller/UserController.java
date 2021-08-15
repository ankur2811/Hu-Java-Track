package com.example.Assignment2.Controller;


import com.example.Assignment2.Models.User;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Service.UserService;
import com.example.Assignment2.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public UserController(UserService userService,CourseService courseService) {
        this.userService = userService;
        this.courseService=courseService;
    }

    //Controller to get the list of all users with all details
    @GetMapping
    public List<User> GetUsers(){

        try {
            return userService.GetUsers();
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"users not present.");
        }
    }

    //Controller to register a new user
    @PostMapping
    public void registerNewUser(@RequestBody User user)
    {
       if(user.getDisplayname()==null || user.getFirstname()==null || user.getLastname()==null)
       {
       throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"users data insufficient");

        }
        System.out.println("post");
        userService.addNewUser(user);
    }

    //Controller to delete a specific user
    @DeleteMapping(path="{userId}")
    public void deleteStudent(@PathVariable("userId") Long id)
    {
        try {
            userService.deleteUser(id);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with id"+id+ "not present.");
        }

    }

    //Controller to update the details of a specific user
    @PutMapping(path="{userId}")
    public void updateUser( @PathVariable("userId") Long userId,
                            @RequestParam(required = false) String displayname,
                            @RequestParam(required = false) String firstname,
                            @RequestParam(required = false) String lastname,
                            @RequestParam(required = false) String about,
                            @RequestParam(required = false) String areaofinterest,
                            @RequestParam(required = false) String usertype,
                            @RequestParam(required = false) String domainexpertise,
                            @RequestParam(required = false) String role,
                            @RequestParam(required = false) String profilepicture)
    {

        try {
            userService.updateUser(userId, displayname, firstname, lastname, about, areaofinterest, usertype, domainexpertise, role, profilepicture);
        }
        catch (Exception ex) {
            System.out.println("exception");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user not present");

        }
    }


    //Controller to get the details of a specific user
    @GetMapping(path="{userId}")
    public User getUserById(@PathVariable("userId") Long id)
    {
        try {
            return userService.getUserById(id);


        }
        catch (Exception ex) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with id"+id+ "not present.");

        }
    }


    //Controller to generate a receipt of cart for a particular user
    @GetMapping(path="file/{userId}")
    public void GenerateFile(@PathVariable("userId") Long id)
    {

        try {
            userService.GenerateRecipt(id);


        }
        catch (Exception ex) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with id"+id+ "not present.");

        }
    }
}
