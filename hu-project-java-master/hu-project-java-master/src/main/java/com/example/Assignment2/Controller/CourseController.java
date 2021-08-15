package com.example.Assignment2.Controller;

import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Service.CourseService;
import com.example.Assignment2.Models.Course;
import com.example.Assignment2.Service.ReviewMappingService;
import com.example.Assignment2.Service.UserCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/course")
public class CourseController {

    private final CourseService courseService;
    private final ReviewMappingService reviewService;
    private final UserCourseMappingService mappingService;

    @Autowired
    public CourseController(CourseService courseService,ReviewMappingService reviewService,UserCourseMappingService mappingService) {
        this.courseService = courseService;
        this.reviewService=reviewService;
        this.mappingService=mappingService;
    }

    //Controller to get the details of all the courses
    @GetMapping
    public List<Course> GetCourses(){
        try {
           return courseService.GetCourses();


        }
        catch (Exception ex) {
            System.out.println("exception");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"course not present");

        }
    }

    //Controller to add a new course to the database
    @PostMapping
    public void registerNewCourse(@RequestBody Course course)
    {
        if(course.getName()==null || course.getAuthor()==null)
        {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,"course data insufficient");
        }
        courseService.addNewCourse(course);
    }

    //Controller to delete the specific course by id
    @DeleteMapping(path="{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long id)
    {
        try {
            courseService.deleteCourse(id);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"course with id"+id+ "not present.");
        }

    }

    //Controller to get the specific course by id
    @GetMapping(path="{courseId}")
    public Course getCourseById(@PathVariable("courseId") Long ide)
    {

        try {
            return courseService.getCourseById(ide);


        }
        catch (Exception ex) {
            System.out.println("exception");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"course not present");

        }
    }


    //Controller to get the courses paginated and sorted
    @GetMapping("/paging")
    public List<Course> getCoursesPaginated(  @RequestParam(defaultValue = "0") Integer pageNo,
                                              @RequestParam(defaultValue = "1") Integer pageSize,
                                              @RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "true") Boolean asc)
    {
        List<Course> ans=courseService.getCoursePaginated(pageNo, pageSize, sortBy,asc);
        return ans;
    }

}
