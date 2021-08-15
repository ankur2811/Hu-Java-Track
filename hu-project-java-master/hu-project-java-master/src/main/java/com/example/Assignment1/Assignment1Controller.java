package com.example.Assignment1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path="api/v1/ass")
public class Assignment1Controller {

//Controller to get the query result as a single car detail
    @GetMapping(path="{userId}")
    public car Get(@PathVariable("userId") int id)
    {
        service ser=new service();
      return ser.getUserById(id);
    }


    //Controller to get the query result as a List of cars
    @GetMapping(path="list/{userId}")
    public List<car> get(@PathVariable("userId") int id)
    {
        service ser=new service();
        return ser.getlistUserById(id);
    }
}
