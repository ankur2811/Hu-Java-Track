package com.example.Assignment2.Config;

import com.example.Assignment2.Models.User;
import com.example.Assignment2.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            ArrayList<String > ar=new ArrayList<>();

            User first=  new User("AnkurGarg","Ankur","Garg","Full stack web developer","backend","fulltime",1,"java","role-developer","url1");
            User second=  new User("ankitkhandelwal","ankit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url2");
            User third=  new User("chanpreetsingh","Chanpreet","Singh","Full stack web developer","backend","fulltime",1,"java","role-developer","url3");
            User fourth=  new User("anshumansingh","Anshuman","Singh","engineer","c++","fulltime",0,"android","role-tester","url4");
            User fifth=  new User("binitsingh","Binit","Singh","Game developer","backend","intern",1,"c#","role-developer","url5");
            User sixth=  new User("guneeshsingh","Guneesh","Singh","data analyst","data science","fulltime",1,"R","role-analyst","url6");
            User seventh=  new User("harshjain","Harsh","Jain","Full stack web developer","backend","fulltime",1,"java","role-developer","url7");
            User eighth=  new User("karansingh","Karan","Singh","cloud engineer","devops","fulltime",1,"aws","role-tester","url8");
            User ninth=  new User("guneetsingh","guneet","singh","Full stack web developer","backend","fulltime",1,"java","role-developer","url9");
            User tenth=  new User("arpitkhandelwal","arpit","khandelwal","engineer","frontend","fulltime",1,"selenium","role-tester","url10");
            repository.saveAll(
                    List.of(first,second,third,fourth,fifth,sixth,seventh,eighth,ninth,tenth)
            );
        };

    }
}
