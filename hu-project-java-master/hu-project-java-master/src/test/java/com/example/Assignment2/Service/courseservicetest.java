package com.example.Assignment2.Service;

import com.example.Assignment2.Models.Course;
import com.example.Assignment2.Models.ReviewMapping;

import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.CourseRepository;
import com.example.Assignment2.Repository.ReviewMappingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class courseservicetest {

    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ReviewMappingRepository reviewMappingRepository;




    @Mock
    private ReviewMappingService reviewMappingService;

    @Mock
    private UserCourseMappingService mappingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        courseService = new CourseService(courseRepository,reviewMappingService,mappingService);
    }


    //Function to test addition of new course is suucesfull or not
    @Test
    void whenCourseCreationSuccessful() throws Exception{
        //Arrange
        Course first=  new Course("Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
        Course second=  new Course(12L,"ASP.net",".netcore","ASP.net full course","Kudvenkat",6788,"2 year","full asp.net content",0,0,0);
        Mockito.when(courseRepository.save(ArgumentMatchers.any(Course.class))).thenReturn(second);

        //Act
        Course e=  courseService.addNewCourse(first);


        //Assert
        assertThat(e.getId(), equalTo(12L));
    }

    //Function to test fetching all course details from database
    @Test
    void test_getALlCourses() throws Exception{

        List<Course> courseList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            courseList.add(new Course(i,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0));
        }

        Mockito.when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> e=courseService.GetCourses();
        assertThat(e.size(),equalTo(5));
    }

    //Function to test exception throws when id not found
    @Test
    void test_getALlCourseByIdnotfound() throws Exception{

        Long empCode = new Long("22332");

        Exception ex = assertThrows(Exception.class,
                () -> courseService.getCourseById(empCode),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("course with id"+empCode+"does not exists"));
    }



    //Function to test filtered course function
    @Test
    void test_filtercourse() throws Exception{
        Course first=  new Course(1L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
    ReviewMapping one=new ReviewMapping(1L,1L,"good",4);
        ReviewMapping two=new ReviewMapping(1L,2L,"good",5);
        UserCourseMapping uc1=new UserCourseMapping(new Long(1),new Long(2),"cart");
        UserCourseMapping uc2=new UserCourseMapping(new Long(1),new Long(2),"enrolled");
        List<ReviewMapping> ls=new ArrayList<>();
        List<UserCourseMapping> ls1=new ArrayList<>();
        ls.add(one);
        ls.add(two);
        ls1.add(uc1);
        ls1.add(uc2);
        Mockito.when(reviewMappingService.getReviewByCourseId(1L)).thenReturn(ls);
        Mockito.when(mappingService.getMappingByCourseId(1L)).thenReturn(ls1);
  float rate= 4.5F;
  Course e=courseService.getFilterCourse(first);
  assertThat(e.getRating(),equalTo(rate));
        assertThat(e.getCartCount(),equalTo(1));
        assertThat(e.getWishlistCount(),equalTo(0));
        assertThat(e.getPurchaseCount(),equalTo(1));


    }


//Function to test filtere course list
    @Test
    void test_filteredcourselist() throws Exception{
        Course first=  new Course(1L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
        Course second=  new Course(2L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
        Course third=  new Course(3L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
        ReviewMapping one=new ReviewMapping(1L,1L,"good",4);
        ReviewMapping two=new ReviewMapping(1L,2L,"good",5);
        UserCourseMapping uc1=new UserCourseMapping(new Long(1),new Long(2),"cart");
        UserCourseMapping uc2=new UserCourseMapping(new Long(1),new Long(2),"enrolled");
        List<ReviewMapping> ls=new ArrayList<>();
        List<UserCourseMapping> ls1=new ArrayList<>();
        List<Course> ls3=new ArrayList<>();
        ls.add(one);
        ls.add(two);
        ls1.add(uc1);
        ls1.add(uc2);
        ls3.add(first);
        ls3.add(second);
        ls3.add(third);
        Mockito.when(reviewMappingService.getReviewByCourseId(1L)).thenReturn(ls);
        Mockito.when(reviewMappingService.getReviewByCourseId(2L)).thenReturn(ls);
        Mockito.when(reviewMappingService.getReviewByCourseId(3L)).thenReturn(ls);
        Mockito.when(mappingService.getMappingByCourseId(1L)).thenReturn(ls1);
        Mockito.when(mappingService.getMappingByCourseId(2L)).thenReturn(ls1);
        Mockito.when(mappingService.getMappingByCourseId(3L)).thenReturn(ls1);
        float rate= 4.5F;
        List<Course> cr=courseService.getFilteredCourse(ls3);
        Course e=cr.get(0);
        assertThat(e.getRating(),equalTo(rate));
        assertThat(e.getCartCount(),equalTo(1));
        assertThat(e.getWishlistCount(),equalTo(0));
        assertThat(e.getPurchaseCount(),equalTo(1));


    }
}
