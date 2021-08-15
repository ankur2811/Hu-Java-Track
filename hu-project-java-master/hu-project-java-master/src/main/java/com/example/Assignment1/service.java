package com.example.Assignment1;

import com.example.Assignment2.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class service {

//Service Method to get the single car details
    //Id is passed as a path variable to run a specific query
    public  static car getUserById(int id)
    {
       mainassignment obj=new mainassignment();
       obj.GetCarData();
       if(id==2)
       {
           return obj.NewCarLowPrice();
       }
       if(id==3)
       {
           return obj.petrolBmwAutoHighMile();
       }
       if(id==4)
       {
           return obj.dieselAudiManualHighMile();
       }
        if(id==10)
        {
            return obj.ManualBMWAudiHighPrice();
        }
       return null;

    }

    //Service Method to get the List of  car details
    //Id is passed as a path variable to run a specific query
    public static List<car> getlistUserById(int id)
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        if(id==5)
        {
            return obj.top10CheaptwolitreEngine();
        }
        if(id==6)
        {
            return obj.top5ModelsMileAbove70000();
        }

        if(id==7)
        {
            return obj.top10WithTaxAbove200();
        }

        if(id==8)
        {
            return obj.top3BMWAuto80KTo100K();
        }
        if(id==9)
        {
            return obj.top5semiautoMPGgreater60();
        }
        return null;
    }

}
