package com.example.Assignment2.Config;

import com.example.Assignment2.Models.ReviewMapping;
import com.example.Assignment2.Repository.ReviewMappingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReviewMappingConfig {
    @Bean
    CommandLineRunner commandLineRunner2(ReviewMappingRepository repository){
        return args -> {
            ReviewMapping first=  new ReviewMapping(new Long(1),new Long(2),"good",5);
            ReviewMapping second=  new ReviewMapping(new Long(1),new Long(1),"good",3);
            ReviewMapping third=  new ReviewMapping(new Long(2),new Long(1),"better",4);
            ReviewMapping fourth=  new ReviewMapping(new Long(2),new Long(2),"fine",3);
            ReviewMapping fifth=  new ReviewMapping(new Long(3),new Long(5),"average",2);
            ReviewMapping sixth=  new ReviewMapping(new Long(3),new Long(1),"good",3);
            ReviewMapping seventh=  new ReviewMapping(new Long(6),new Long(3),"good",5);
            ReviewMapping eighth=  new ReviewMapping(new Long(7),new Long(6),"good",3);
            ReviewMapping ninth=  new ReviewMapping(new Long(8),new Long(7),"good",5);
            ReviewMapping tenth=  new ReviewMapping(new Long(9),new Long(4),"good",3);
            repository.saveAll(
                    List.of(first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth)
            );
        };

    }
}
