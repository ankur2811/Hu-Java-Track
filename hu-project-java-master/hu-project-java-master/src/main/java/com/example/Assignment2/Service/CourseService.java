package com.example.Assignment2.Service;

import com.example.Assignment2.Models.Course;
import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ReviewMappingService reviewService;
    private final UserCourseMappingService mappingService;
    public CourseService(CourseRepository courseRepository,ReviewMappingService reviewService,UserCourseMappingService mappingService) {
        this.courseRepository = courseRepository;
        this.reviewService=reviewService;
        this.mappingService=mappingService;
    }


    //Function to fetch the course table from database ]
    public List<Course> GetCourses(){
        return getFilteredCourse(courseRepository.findAll());

    }

    //function to add new course to the table course of database assignment
    public Course addNewCourse(Course course) {

       return courseRepository.save(course);
    }


    //Function to delete the specific course from a database
    public void deleteCourse(Long id) {
        boolean exists=courseRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("course with id"+id+"does not exists");

        }
        courseRepository.deleteById(id);
    }


    //Function to fetch the detail of a single course from database
    public Course getCourseById(Long id)
    {
        boolean exists=courseRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("course with id"+id+"does not exists");

        }

        courseRepository.findById(id);
        return getFilterCourse(courseRepository.getOne(id));

    }


    //Function to get the average rating and how many user purchase,wishlist ,cart of  all the courses
    public List<Course> getFilteredCourse(List<Course> ls)
    {
        for (Course bean : ls) {
            long id = bean.id;
            List<ReviewMapping> lr = reviewService.getReviewByCourseId(id);
            int n = lr.size();
            float rate = 0;
            for (ReviewMapping map : lr) {
                rate += map.getRating();
            }
            rate /= n;
            bean.setRating(rate);
        }
        for (Course bean : ls) {
            long id = bean.id;
            List<UserCourseMapping> lr = mappingService.getMappingByCourseId(id);
            int cart=0;
            int wishlist=0;
            int enrolled=0;
            for(UserCourseMapping map:lr)
            {
                if(map.getStatus().equals("wishlist"))
                    wishlist++;
                else if(map.getStatus().equals("cart"))
                    cart++;
                else if(map.getStatus().equals("enrolled"))
                    enrolled++;
            }
            bean.setWishlistCount(wishlist);
            bean.setCartCount(cart);
            bean.setPurchaseCount(enrolled);
        }
        return ls;
    }

    //Function to get the average rating and how many user purchase,wishlist ,cart of a single course
    public Course getFilterCourse(Course bean)
    {
        long id = bean.id;
        List<ReviewMapping> lr = reviewService.getReviewByCourseId(id);
        int n = lr.size();
        float rate = 0;
        for (ReviewMapping map : lr) {
            rate += map.getRating();
        }
        rate /= n;
        bean.setRating(rate);


        List<UserCourseMapping> lru = mappingService.getMappingByCourseId(id);
        int cart=0;
        int wishlist=0;
        int enrolled=0;
        for(UserCourseMapping map:lru)
        {
            if(map.getStatus().equals("wishlist"))
                wishlist++;
            else if(map.getStatus().equals("cart"))
                cart++;
            else if(map.getStatus().equals("enrolled"))
                enrolled++;
        }
        bean.setWishlistCount(wishlist);
        bean.setCartCount(cart);
        bean.setPurchaseCount(enrolled);
        return bean;
    }


    //Function to implement pagination and sorting
    public List<Course> getCoursePaginated(Integer pageNo, Integer pageSize, String sortBy, Boolean asc) {
        Pageable paging = PageRequest.of(pageNo, pageSize, asc ? Sort.by(sortBy) : Sort.by(sortBy).descending());

        Page<Course> pagedResult = courseRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Course>();
        }
    }
}
