package com.example.Assignment2.Service;


import com.example.Assignment2.Models.User;
import com.example.Assignment2.Models.UserCourseMapping;

import com.example.Assignment2.Repository.UserCourseMappingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class UserCourseMappingtest {

    private UserCourseMappingService mappingService;

    @Mock
    private UserCourseMappingRepository mappingRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mappingService = new UserCourseMappingService(mappingRepository);
    }

    //Function to test mapping creation is successful or not
    @Test
    void whenMapCreationSuccessful() throws Exception{
        //Arrange
        UserCourseMapping first=  new UserCourseMapping(new Long(1),new Long(2),"cart");
        UserCourseMapping second=  new UserCourseMapping(5L,new Long(2),new Long(1),"wishlist");
        Mockito.when(mappingRepository.save(ArgumentMatchers.any(UserCourseMapping.class))).thenReturn(second);
        UserCourseMapping e=  mappingService.addNewUserCourseMapping(first);
       assertThat(e.getId(), equalTo(5L));
    }


    //Function to test fetching all mapping details
    @Test
    void test_getALlmapping() throws Exception{

        List<UserCourseMapping> mapList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            mapList.add( new  UserCourseMapping(i,new Long(2),new Long(1),"wishlist"));
        }

        Mockito.when(mappingRepository.findAll()).thenReturn(mapList);
        List<UserCourseMapping> e=mappingService.GetUserCourseMappings();
        assertThat(e.size(),equalTo(5));
    }


    //Function to test exception occurs when id not found
    @Test
    void test_getALlMappingByIdnotfound() throws Exception{

        Long empCode = new Long("22332");

        Exception ex = assertThrows(Exception.class,
                () -> mappingService.getUserCourseMappingById(empCode),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("usercoursemapping with id"+empCode+"does not exists"));
    }

    //Function to test fetching mapping by courseId
    @Test
    void test_getMappingbycourseid() throws Exception{
        List<UserCourseMapping> mapList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            mapList.add( new  UserCourseMapping(i,new Long(2),new Long(1),"wishlist"));
        }

        Mockito.when(mappingRepository.findmapbycourseid(1L)).thenReturn(mapList);
        List<UserCourseMapping> ans=mappingService.getMappingByCourseId(1L);
        assertThat(ans.get(0).getId(),equalTo(1l));
    }

    //Function to test updation in mapping is successful or not
    @Test
    void whenmappingUpdationSuccessful() throws Exception{
        //Arrange

        UserCourseMapping first=  new UserCourseMapping(12L,new Long(1),new Long(2),"cart");
        Mockito.when(mappingRepository.findById(12L)).thenReturn(java.util.Optional.of(first));

        //Act
        UserCourseMapping e=  mappingService.updateCourseStatus(12L,"wishlist");


        //Assert
        assertThat(e.getId(), equalTo(12L));
    }



    //Function to test checkout of a given user cart
    @Test
    void testcheckout() throws Exception{
        //Arrange

        UserCourseMapping first=  new UserCourseMapping(12L,new Long(1),new Long(2),"cart");
        List<UserCourseMapping> mapList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            mapList.add( new  UserCourseMapping(i,new Long(2),new Long(1),"cart"));
        }

        Mockito.when(mappingRepository.findmapbyuserid(1L)).thenReturn(mapList);
        List<UserCourseMapping> ans=mappingService.checkout(1L,"enrolled");
        assertThat(ans.get(0).getStatus(),equalTo("enrolled"));


    }
}
