package com.example.Assignment2.Config;

import com.example.Assignment2.Repository.CourseRepository;
import com.example.Assignment2.Models.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunner1(CourseRepository repository){
        return args -> {
            Course first=  new Course("Java","Spring","java spring boot","James Gosling",7899,"1 year","full java content",0,0,0);
            Course second=  new Course("ASP.net",".netcore","ASP.net full course","Kudvenkat",6788,"2 year","full asp.net content",0,0,0);
            Course third=  new Course("PHP","Backend","PHP backend course","James ",8999,"1 year","full php content",0,0,0);
            Course fourth=  new Course("Angular","Angular core frontend","Angular full course","Maxmilian",5898,"2 year","full angular content",0,0,0);
            Course fifth=  new Course("C++","basic coding skills","lets started with c","venugopal",3667,"1 year","full c++ content",0,0,0);
            Course sixth=  new Course("Dbms","database","database management system","oracle",8988,"2 year","full dbms content",0,0,0);
            Course seventh=  new Course("Data Structure","Competitive coding","data structures using java","Schaum Series",7339,"1 year","full ds content",0,0,0);
            Course eighth=  new Course("React","frontend","React full course","facebook",5679,"2 year","full react content",0,0,0);
            Course ninth=  new Course("Flutter","Mobile","full flutter boot","Google",3999,"1 year","full flutter content",0,0,0);
            Course tenth=  new Course("Unity 3d","Game development","Unity full course","W3 school",8799,"2 year","full unity content",0,0,0);
            repository.saveAll(
                    List.of(first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth)
            );
        };

    }
}
