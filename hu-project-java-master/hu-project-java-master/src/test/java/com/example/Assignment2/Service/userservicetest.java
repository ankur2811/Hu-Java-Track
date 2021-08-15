package com.example.Assignment2.Service;

import com.example.Assignment2.Models.Course;
import com.example.Assignment2.Models.User;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
//import static org.junit.jupiter.api.AssertTrue.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class userservicetest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository,courseService);
    }

    //Function to test adding a new user is successful or not
    @Test
    void whenUserCreationSuccessful() throws Exception{
        //Arrange
        User userRequest = new User("ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");

        User mockUser = new User(12L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);

        //Act
      User e=  userService.addNewUser(userRequest);


        //Assert
        assertThat(e.getId(), equalTo(12L));
    }


    //Function to test fetching details of all users
    @Test
    void test_getALlUsers() throws Exception{

        List<User> userList=new ArrayList<>();
        UserCourseMapping ucm=new UserCourseMapping(1L,2L,"cart");
        List<UserCourseMapping> l=new ArrayList<>();
        l.add(ucm);
        Course c=  new Course(1L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);

        for(long i=1;i<6;i++)
        {
            User u=new User(i,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");
            u.setUsercoursemap(l);
            userList.add(u);

        }
        Mockito.when(courseService.getCourseById(2L)).thenReturn(c);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> e=userService.GetUsers();
        assertThat(e.size(),equalTo(5));
    }


    //Function to test exception occurs when user id not found
    @Test
    void test_getALlUsersByIdnotfound() throws Exception{

        Long empCode = new Long("22332");

        Exception ex = assertThrows(Exception.class,
                () -> userService.getUserById(empCode),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("user with id"+empCode+"does not exists"));
    }

    //Function to test exception occurs when deleting a user which is not present
    @Test
    void whenUserDeletionUnSuccessful() throws Exception{
        //Arrange
        Long empCode = new Long("22332");

        Exception ex = assertThrows(Exception.class,
                () -> userService.deleteUser(empCode),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("user with id"+empCode+"does not exists"));
    }

    //Function to test updating user detail is successful or not
    @Test
    void whenUserUpdationSuccessful() throws Exception{
        //Arrange

        User mockUser = new User(12L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");

        Mockito.when(userRepository.findById(12L)).thenReturn(java.util.Optional.of(mockUser));

        //Act
        User e=  userService.updateUser(12L,"sjdj","jhbsjdh","hsj","","","","","","");


        //Assert
        assertThat(e.getId(), equalTo(12L));
    }

    //Function to test validation in updating user detail
    @Test
    void whenUserUpdationUnSuccessful() throws Exception{
        //Arrange
        User mockUser = new User(12L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");

        Mockito.when(userRepository.findById(12L)).thenReturn(java.util.Optional.of(mockUser));
        Exception ex = assertThrows(Exception.class,
                () -> userService.updateUser(12L,null,"jhbsjdh","hsj","","","","","",""),
                "Provide provide display name");
        assertThat(ex.getMessage(), equalTo("insufficient data"));
    }


    //Function to test exception occurs in updation when user not present
    @Test
    void whenUserUpdationUnSuccessfulwrongid() throws Exception{
        //Arrange


        Exception ex = assertThrows(Exception.class,
                () -> userService.updateUser(12L,"jshjh","jhbsjdh","hsj","","","","","",""),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("user with id"+12+"does not exist"));
    }



    //Function to test user is filtered or not
    @Test
    void getFilteredUser() throws Exception{

        User first= new User(1L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");
        User second = new User(2L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");
        Course c=  new Course(1L,"Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
        User third = new User(3L,"ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");
        UserCourseMapping ucm=new UserCourseMapping(1L,2L,"cart");
        List<UserCourseMapping> l=new ArrayList<>();
        l.add(ucm);
        first.setUsercoursemap(l);
        second.setUsercoursemap(l);
        third.setUsercoursemap(l);
        List<User> ls=new ArrayList<>();
        ls.add(first);
        ls.add(second);
        ls.add(third);
        Mockito.when(courseService.getCourseById(2L)).thenReturn(c);
        List<User> ans=userService.getFilteredUser(ls);
        assertThat(ans.get(0).getCourse(),equalTo("Java,"));

    }





}
