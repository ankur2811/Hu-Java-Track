package com.example.Assignment2.Service;

import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Repository.ReviewMappingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewMappingService {

    private final ReviewMappingRepository reviewMappingRepository;
    public ReviewMappingService(ReviewMappingRepository reviewMappingRepository) {
        this.reviewMappingRepository= reviewMappingRepository;
    }

    public List<ReviewMapping> GetReviewMappings(){
        return reviewMappingRepository.findAll();

    }

    public ReviewMapping addNewReviewMapping(ReviewMapping reviewMapping) {

        List<ReviewMapping> ls=reviewMappingRepository.findmapbycourseid(reviewMapping.getCourseId());
        for(ReviewMapping bean:ls)
        {
            if(bean.getUserId()==reviewMapping.getUserId())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"review already present");
            }
        }
        return reviewMappingRepository.save(reviewMapping);
    }

    public ReviewMapping getReviewMappingById(Long id)
    {
        boolean exists=reviewMappingRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("review with id"+id+"does not exists");

        }

        reviewMappingRepository.findById(id);
        return reviewMappingRepository.getOne(id);

    }

    public List<ReviewMapping> getReviewByCourseId(Long id) {
        return reviewMappingRepository.findmapbycourseid(id);
    }
}
