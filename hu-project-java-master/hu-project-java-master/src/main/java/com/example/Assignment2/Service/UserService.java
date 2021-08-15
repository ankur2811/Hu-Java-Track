package com.example.Assignment2.Service;

import com.example.Assignment2.Models.User;
import com.example.Assignment2.Models.UserCourseMapping;
import com.example.Assignment2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CourseService courseService;
    public UserService(UserRepository userRepository,CourseService courseService) {
        this.userRepository = userRepository;
        this.courseService=courseService;
    }

    public List<User> GetUsers(){
        return getFilteredUser(userRepository.findAll());

    }

    public User addNewUser(User User) {

       return userRepository.save(User);

    }

    public void deleteUser(Long id) {
        boolean exists=userRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("user with id"+id+"does not exists");

        }
       userRepository.deleteById(id);

    }

    @Transactional
    public  User updateUser(Long userId, String displayname, String firstname, String lastname, String about,String areaofinterest, String usertype, String domainexpertise, String role, String profilepicture) {
        User User=userRepository.findById(userId).orElseThrow(()->new IllegalStateException("user with id"+userId+"does not exist"));

        if(firstname==null || lastname==null || displayname==null)
        {
            throw new IllegalStateException("insufficient data");
        }
        if(displayname!=null && displayname.length()>0 && !Objects.equals(User.getDisplayname(),displayname))
        {
            User.setDisplayname(displayname);
        }
        if(firstname!=null && firstname.length()>0 && !Objects.equals(User.getFirstname(),firstname))
        {
            User.setFirstname(firstname);
        }
        if(lastname!=null && lastname.length()>0 && !Objects.equals(User.getLastname(),lastname))
        {
            User.setLastname(lastname);
        }
        if(about!=null && about.length()>0 && !Objects.equals(User.getAbout(),about))
        {
            User.setAbout(about);
        }
        if(areaofinterest!=null && areaofinterest.length()>0 && !Objects.equals(User.getAreaofinterest(),areaofinterest))
        {
            User.setAreaofinterest(areaofinterest);
        }
        if(usertype!=null && usertype.length()>0 && !Objects.equals(User.getUsertype(),usertype))
        {
            User.setUsertype(usertype);
        }
        //if(experience!=0 && !Objects.equals(User.getExperience(),experience))
        //{
         //   User.setExperience(experience);
        //}
        if(domainexpertise!=null && domainexpertise.length()>0 && !Objects.equals(User.getDomainexpertise(),domainexpertise))
        {
            User.setDomainexpertise(domainexpertise);
        }
        if(role!=null && role.length()>0 && !Objects.equals(User.getRole(),role))
        {
            User.setLastname(role);
        }
        if(profilepicture!=null && profilepicture.length()>0 && !Objects.equals(User.getProfilepicture(),profilepicture))
        {
            User.setProfilepicture(profilepicture);
        }

      return User;
    }


    public User getUserById(Long id)
    {
        boolean exists=userRepository.existsById(id);
        if(!exists)
        {
            throw new IllegalStateException("user with id"+id+"does not exists");

        }

         userRepository.findById(id);
        List<User> ls=new ArrayList<>();
        ls.add(userRepository.getOne(id));
        List<User> ans=getFilteredUser(ls);
        return ans.get(0);

    }


    public List<User> getFilteredUser(List<User> userCourses)
    {
        for (User userBean : userCourses) {
            List<UserCourseMapping> course = new ArrayList<>();
            List<String> coursename = new ArrayList<>();

            for (UserCourseMapping userCourseMapping : userBean.getUsercoursemap()) {
                if (userCourseMapping.getStatus().equals("cart")) {
                    course.add(userCourseMapping);
                    long i = userCourseMapping.getCourseId();
                    String crs = courseService.getCourseById(i).name;
                    coursename.add(crs);

                }
            }
            String str = "";
            for (String fruit : coursename) {
                str += fruit + ",";
            }
            userBean.setCourse(str);
            System.out.println(coursename);
            userBean.setUsercoursemap(course);
        }
        return userCourses;
    }

    public Long GenerateRecipt(Long id)
    {
        User u=getUserById(id);
        String str=u.getCourse();
        File file = new File("write"+id+".txt");
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {


            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
System.out.println(id);
      return id;
    }
}
