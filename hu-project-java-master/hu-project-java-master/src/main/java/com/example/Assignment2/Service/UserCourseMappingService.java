package com.example.Assignment2.Service;

import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.UserCourseMappingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserCourseMappingService {

    private final UserCourseMappingRepository userCourseMappingRepository;
    public UserCourseMappingService(UserCourseMappingRepository userCourseMappingRepository) {
        this.userCourseMappingRepository= userCourseMappingRepository;
    }

    public List<UserCourseMapping> GetUserCourseMappings(){
        return userCourseMappingRepository.findAll();

    }

    public UserCourseMapping addNewUserCourseMapping(UserCourseMapping userCourseMapping) {

        List<UserCourseMapping> ls=userCourseMappingRepository.findmapbyuserid(userCourseMapping.getUserId());
        for(UserCourseMapping bean:ls)
        {
            if(bean.getCourseId()==userCourseMapping.getCourseId())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"review already present");
            }
        }
         return userCourseMappingRepository.save(userCourseMapping);
    }

    public UserCourseMapping getUserCourseMappingById(Long id)
    {
        boolean exists=userCourseMappingRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("usercoursemapping with id"+id+"does not exists");

        }

        userCourseMappingRepository.findById(id);
        return userCourseMappingRepository.getOne(id);

    }



    @Transactional
    public  UserCourseMapping updateCourseStatus(Long userId, String status) {
        UserCourseMapping userCourseMapping=userCourseMappingRepository.findById(userId).orElseThrow(()->new IllegalStateException("mapping with id"+userId+"does not exist"));

        if(status!=null && status.length()>0 && !Objects.equals(userCourseMapping.getStatus(),status))
        {
            userCourseMapping.setStatus(status);
        }
 return userCourseMapping;


    }

    @Transactional
    public List<UserCourseMapping> checkout(Long userId,String enrolled) {
        List<UserCourseMapping> ls=userCourseMappingRepository.findmapbyuserid(userId);
        for(UserCourseMapping bean:ls)
        {
            if(bean.getStatus().equals("cart"))
            {
                bean.setStatus("enrolled");
            }
        }
return ls;
    }

    public List<UserCourseMapping> getMappingByCourseId(Long id) {
        return userCourseMappingRepository.findmapbycourseid(id);
    }
}
