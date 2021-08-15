package com.example.Assignment2.Controller;

import com.example.Assignment2.Service.UserCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="api/v1/checkout")
public class checkoutController {

    private final UserCourseMappingService userCourseMappingService;

    @Autowired
    public checkoutController(UserCourseMappingService userCourseMappingService) {
        this.userCourseMappingService = userCourseMappingService;
    }


    //Controller To Checkout the cart of a given user
    @PutMapping(path="{userId}")
    public void updateUserCourseMapping( @PathVariable("userId") Long userId
    )
    {

try {
    userCourseMappingService.checkout(userId, "enrolled");
}

catch(Exception e)
{
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user with id"+userId+ "not present.");
}
    }

}
