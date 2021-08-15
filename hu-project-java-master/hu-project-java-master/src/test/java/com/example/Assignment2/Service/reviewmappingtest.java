package com.example.Assignment2.Service;


import com.example.Assignment2.Models.ReviewMapping;

import com.example.Assignment2.Repository.ReviewMappingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class reviewmappingtest {

    private ReviewMappingService reviewService;

    @Mock
    private ReviewMappingRepository reviewRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewMappingService(reviewRepository);
    }

    //Function to test adding a review is successful or not
    @Test
    void whenReviewCreationSuccessful() throws Exception{
        //Arrange
        ReviewMapping first=  new ReviewMapping(new Long(1),new Long(2),"good",5);
        ReviewMapping second=  new ReviewMapping(3L,new Long(1),new Long(1),"good",3);
        Mockito.when(reviewRepository.save(ArgumentMatchers.any(ReviewMapping.class))).thenReturn(second);

        ReviewMapping e=  reviewService.addNewReviewMapping(first);
        assertThat(e.getId(), equalTo(3L));
    }


    //Function to test fetching review details is successful or not
    @Test
    void test_getALlreviews() throws Exception{

        List<ReviewMapping> reviewList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            reviewList.add( new ReviewMapping(i,new Long(1),new Long(1),"good",3));
        }

        Mockito.when(reviewRepository.findAll()).thenReturn(reviewList);
        List<ReviewMapping> e=reviewService.GetReviewMappings();
        assertThat(e.size(),equalTo(5));
    }


    //Function to test exception occurs when id is not found
    @Test
    void test_getALlReviewByIdnotfound() throws Exception{

        Long empCode = new Long("22332");

        Exception ex = assertThrows(Exception.class,
                () -> reviewService.getReviewMappingById(empCode),
                "Provide date in yyyy-MM-dd format");

        //Assert
        assertThat(ex.getMessage(), equalTo("review with id"+empCode+"does not exists"));
    }

    //Function to test fetching review mapping by course id
    @Test
    void test_getReviewMappingbycourseid() throws Exception{
        List<ReviewMapping> reviewList=new ArrayList<>();

        for(long i=1;i<6;i++)
        {
            reviewList.add( new ReviewMapping(i,new Long(1),new Long(1),"good",3));
        }
        Mockito.when(reviewRepository.findmapbycourseid(1L)).thenReturn(reviewList);
        List<ReviewMapping> ans=reviewService.getReviewByCourseId(1L);
        assertThat(ans.get(0).getId(),equalTo(1l));
    }

}
