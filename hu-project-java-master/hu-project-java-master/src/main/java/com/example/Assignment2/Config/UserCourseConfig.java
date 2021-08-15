package com.example.Assignment2.Config;

import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.UserCourseMappingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserCourseConfig {
    @Bean
    CommandLineRunner commandLineRunner3(UserCourseMappingRepository repository){
        return args -> {
            UserCourseMapping first=  new UserCourseMapping(new Long(1),new Long(2),"cart");
            UserCourseMapping second=  new UserCourseMapping(new Long(2),new Long(1),"wishlist");
            UserCourseMapping third=  new UserCourseMapping(new Long(1),new Long(3),"cart");
            UserCourseMapping fourth=  new UserCourseMapping(new Long(1),new Long(4),"enrolled");
            UserCourseMapping fifth=  new UserCourseMapping(new Long(2),new Long(5),"cart");
            UserCourseMapping sixth=  new UserCourseMapping(new Long(2),new Long(2),"wishlist");
            UserCourseMapping seventh=  new UserCourseMapping(new Long(3),new Long(1),"cart");
            UserCourseMapping eighth=  new UserCourseMapping(new Long(3),new Long(6),"wishlist");
            UserCourseMapping ninth=  new UserCourseMapping(new Long(1),new Long(9),"wishlist");
            UserCourseMapping tenth=  new UserCourseMapping(new Long(8),new Long(1),"enrolled");
            repository.saveAll(
                    List.of(first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth)
            );
        };

    }
}
