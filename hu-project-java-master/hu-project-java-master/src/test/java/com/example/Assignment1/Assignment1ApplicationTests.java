package com.example.Assignment1;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Assignment1ApplicationTests {


    //Function to test data is fetched or not from csv file
    @Test
    public void test_GetCarDataBMW()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        int result=obj.bmw.size();
        int expected=10781;
        assertEquals(expected,result);

    }

    //Function to test newest car which has the lowest price, considering both the sets
    @Test
    public void test_NewCarLowPrice()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
       car c= obj.NewCarLowPrice();
        Long priceresult=c.getPrice();
       Long priceactual=new Long(11995);
        int yearresult=c.getYear();
        int yearactual=2020;
        assertEquals(priceactual,priceresult);
        assertEquals(yearactual,yearresult);

    }

    //Function to test petrol BMW with auto transmission that has the highest mileage
    @Test
    public void test_petrolBmwAutoHighMileage()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        car c= obj.petrolBmwAutoHighMile();
        String fuelresult=c.getFuelType();
        String fuelactual="Petrol";
        String transmissionresult=c.getTransmission();
        String transmissionactual="Automatic";

        Long milresult=c.getMileage();
        Long milactual=new Long(141000);
        assertEquals(fuelresult,fuelactual);
        assertEquals(transmissionresult,transmissionactual);
        assertEquals(milactual,milresult);

    }

    // Function to test diesel audi with manual transmission that has the highest mileage
    @Test
    public void test_dieselAudiManualHighMile()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        car c= obj.dieselAudiManualHighMile();
        String fuelresult=c.getFuelType();
        String fuelactual="Diesel";
        String transmissionresult=c.getTransmission();
        String transmissionactual="Manual";

        Long milresult=c.getMileage();
        Long milactual=new Long(323000);
        assertEquals(fuelresult,fuelactual);
        assertEquals(transmissionresult,transmissionactual);
        assertEquals(milactual,milresult);

    }


    //Function to test top 10 cheapest cars with 2 liter engine.
    @Test
    public void test_top10CheaptwolitreEngine()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        List<car> c= obj.top10CheaptwolitreEngine();
        for(int i=0;i<10;i++)
        {
            float engine_sizeresult=c.get(i).getEngineSize();
            float engine_sizeactual=2;
            assertEquals(engine_sizeactual,engine_sizeresult,1e-15);

        }


    }

    //Function to test 5 top models from bmw and audi which has mileage above 70000
    @Test
    public void test_top5ModelsMileAbove7000()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        List<car> c= obj.top5ModelsMileAbove70000();
        for(int i=0;i<5;i++)
        {
            Long milage_result=c.get(i).getMileage();
            Long milage_actual=new Long(70000);
            Assert.assertTrue(milage_result  > milage_actual );

        }


    }


    //Function to test cars with model year and transmission type of top 10 petrol cars with tax above 200(both audi and bmw sets)
    @Test
    public void test_top10WithTaxAbove200()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        List<car> c= obj.top10WithTaxAbove200();
        for(int i=0;i<10;i++)
        {
            int tax_result=c.get(i).getTax();
            int tax_actual=200;
            Assert.assertTrue(tax_result  > tax_actual );

        }


    }



    //Function to tesr the top 3 latest bmw model of type automatic with price between 80,000 and 1,00,000
    @Test
    public void test_top3BMWAuto80KTo100K()
    {
        mainassignment obj=new mainassignment();

        List<car> c= obj.top3BMWAuto80KTo100K();
        for(int i=0;i<1;i++)
        {
            Long price_result=c.get(i).getPrice();
            Long price_actual1=new Long(80000);
            Long price_actual2=new Long(100000);
            String trans_result=c.get(i).getTransmission();
            String trans_actual="Automatic";
            Assert.assertTrue(price_result  > price_actual1 );
            Assert.assertTrue(price_result  < price_actual2 );
            assertEquals(trans_actual,trans_result);

        }


    }


    //Function to test top 5 semi-auto audi models with mpg greater than 60.0.
    @Test
    public void test_top5semiautoMPGgreater60()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        List<car> c= obj.top5semiautoMPGgreater60();
        for(int i=0;i<5;i++)
        {
            String trans_result=c.get(i).getTransmission();
            String trans_actual="Semi-Auto";
            Double mpgresult=c.get(i).getMpg();
            Double mpgactual=new Double(60);
            Assert.assertTrue(mpgresult  > mpgactual );
            assertEquals(trans_actual,trans_result);

        }


    }


    //Function to test model and year of manual bmw and audi with the highest price
    @Test
    public void test_anualBMWAudiHighPrice()
    {
        mainassignment obj=new mainassignment();
        obj.GetCarData();
        car c=obj.ManualBMWAudiHighPrice();
       int yearresult=c.getYear();
       int yearactual=2013;
       String modelactual=" R8";
       String modelresult=c.getModel();
        assertEquals(yearactual,yearresult);
        assertEquals(modelresult,modelactual);

    }



}
